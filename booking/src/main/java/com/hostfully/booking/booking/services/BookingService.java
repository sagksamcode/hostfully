package com.hostfully.booking.booking.services;

import com.hostfully.booking.booking.builders.*;
import com.hostfully.booking.booking.domain.vos.models.Booking;
import com.hostfully.booking.booking.domain.vos.requests.BookingRebookRequest;
import com.hostfully.booking.booking.domain.vos.requests.BookingRequest;
import com.hostfully.booking.booking.domain.vos.requests.BookingStatus;
import com.hostfully.booking.booking.domain.vos.requests.BookingUpdateRequest;
import com.hostfully.booking.booking.domain.vos.responses.BookingPostResponse;
import com.hostfully.booking.booking.domain.vos.responses.BookingResponse;
import com.hostfully.booking.booking.repositories.BookingRepository;
import com.hostfully.booking.exceptions.NotFoundException;
import com.hostfully.booking.services.ValidationService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService extends ValidationService {

  @Autowired BookingRepository bookingRepository;

  public BookingPostResponse createBooking(final BookingRequest bookingRequest) {

    validateBookingsInRange(
        bookingRequest.getPropertyId(),
        bookingRequest.getStartDate(),
        bookingRequest.getEndDate(),
        null);
    validateBlocksInRange(
        bookingRequest.getPropertyId(),
        bookingRequest.getStartDate(),
        bookingRequest.getEndDate(),
        null);
    final Booking booking =
        new BookingBuilder()
            .withStartDate(bookingRequest.getStartDate())
            .withEndDate(bookingRequest.getEndDate())
            .withStatus(BookingStatus.SCHEDULED)
            .withPropertyId(bookingRequest.getPropertyId())
            .withGuest(
                new GuestBuilder()
                    .withFirstName(bookingRequest.getGuest().getFirstName())
                    .withLastName(bookingRequest.getGuest().getLastName())
                    .build())
            .build();

    bookingRepository.save(booking);
    return new BookingPostResponseBuilder()
        .withId(booking.getId())
        .withStatus(booking.getStatus())
        .build();
  }

  public List<BookingResponse> retrieveBookings() {

    final Iterable<Booking> bookings = bookingRepository.findAll();

    if (!bookings.iterator().hasNext()) throw NotFoundException.bookingsNotFound();

    final List<BookingResponse> bookingResponse =
        StreamSupport.stream(bookings.spliterator(), false)
            .map(this::buildBookingResponse)
            .collect(Collectors.toList());

    return bookingResponse;
  }

  public BookingResponse retrieveBookingById(final String id) {

    final Booking booking =
        bookingRepository.findById(id).orElseThrow(() -> NotFoundException.bookingsNotFoundById());
    return buildBookingResponse(booking);
  }

  public BookingPostResponse cancelBooking(final String id) {

    final Booking booking =
        bookingRepository.findById(id).orElseThrow(() -> NotFoundException.bookingsNotFoundById());
    booking.setStatus(BookingStatus.CANCELLED);
    bookingRepository.save(booking);
    return new BookingPostResponseBuilder()
        .withId(booking.getId())
        .withStatus(booking.getStatus())
        .build();
  }

  public BookingPostResponse rescheduleBooking(
      final String id, final BookingRebookRequest bookingRebookRequest) {

    final Booking booking =
        bookingRepository.findById(id).orElseThrow(() -> NotFoundException.bookingsNotFoundById());
    validateBookingsInRange(
        booking.getPropertyId(),
        bookingRebookRequest.getStartDate(),
        bookingRebookRequest.getEndDate(),
        booking.getId());
    validateBlocksInRange(
        booking.getPropertyId(),
        bookingRebookRequest.getStartDate(),
        bookingRebookRequest.getEndDate(),
        null);
    booking.setStartDate(bookingRebookRequest.getStartDate());
    booking.setEndDate(bookingRebookRequest.getEndDate());
    booking.setStatus(BookingStatus.SCHEDULED);
    bookingRepository.save(booking);
    return new BookingPostResponseBuilder()
        .withId(booking.getId())
        .withStatus(booking.getStatus())
        .build();
  }

  public BookingPostResponse updateBooking(
      final String id, final BookingUpdateRequest bookingUpdateRequest) {

    final Booking booking =
        bookingRepository.findById(id).orElseThrow(() -> NotFoundException.bookingsNotFoundById());
    validateBookingsInRange(
        booking.getPropertyId(),
        bookingUpdateRequest.getStartDate(),
        bookingUpdateRequest.getEndDate(),
        booking.getId());
    validateBlocksInRange(
        booking.getPropertyId(),
        bookingUpdateRequest.getStartDate(),
        bookingUpdateRequest.getEndDate(),
        null);
    booking.setStartDate(bookingUpdateRequest.getStartDate());
    booking.setEndDate(bookingUpdateRequest.getEndDate());
    booking.setGuest(
        new GuestBuilder()
            .withFirstName(bookingUpdateRequest.getGuest().getFirstName())
            .withLastName(bookingUpdateRequest.getGuest().getLastName())
            .build());
    bookingRepository.save(booking);
    return new BookingPostResponseBuilder()
        .withId(booking.getId())
        .withStatus(booking.getStatus())
        .build();
  }

  public void deleteBooking(final String id) {

    bookingRepository.findById(id).orElseThrow(() -> NotFoundException.bookingsNotFoundById());
    bookingRepository.deleteById(id);
  }

  private BookingResponse buildBookingResponse(final Booking booking) {

    return new BookingResponseBuilder()
        .withId(booking.getId())
        .withStartDate(booking.getStartDate())
        .withEndDate(booking.getEndDate())
        .withGuest(
            new GuestResponseBuilder()
                .withFirstName(booking.getGuest().getFirstName())
                .withLastName(booking.getGuest().getLastName())
                .build())
        .withStatus(booking.getStatus())
        .withPropertyId(booking.getPropertyId())
        .build();
  }
}
