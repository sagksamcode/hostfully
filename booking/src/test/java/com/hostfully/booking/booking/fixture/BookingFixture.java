package com.hostfully.booking.booking.fixture;

import com.hostfully.booking.booking.domain.vos.models.Booking;
import com.hostfully.booking.booking.domain.vos.models.Guest;
import com.hostfully.booking.booking.domain.vos.requests.*;
import com.hostfully.booking.booking.domain.vos.responses.BookingPostResponse;
import com.hostfully.booking.booking.domain.vos.responses.BookingResponse;
import com.hostfully.booking.booking.domain.vos.responses.GuestResponse;
import java.time.LocalDate;

public class BookingFixture {

  public static BookingRequest createBookingRequest() {

    BookingRequest bookingRequest = new BookingRequest();
    bookingRequest.setStartDate(LocalDate.of(2024, 1, 1));
    bookingRequest.setEndDate(LocalDate.of(2024, 1, 20));
    bookingRequest.setPropertyId("UUID");
    bookingRequest.setGuest(new GuestRequest());
    return bookingRequest;
  }

  public static BookingPostResponse createBookingPostResponse() {

    BookingPostResponse bookingPostResponse = new BookingPostResponse();
    bookingPostResponse.setStatus(BookingStatus.SCHEDULED);
    bookingPostResponse.setId("bookingId");
    return bookingPostResponse;
  }

  public static BookingResponse createBookingResponse() {

    BookingResponse bookingResponse = new BookingResponse();
    bookingResponse.setId("bookingId");
    bookingResponse.setStatus(BookingStatus.SCHEDULED);
    bookingResponse.setPropertyId("UUID");
    bookingResponse.setStartDate(LocalDate.of(2024, 1, 1));
    bookingResponse.setEndDate(LocalDate.of(2024, 1, 20));
    bookingResponse.setGuest(new GuestResponse());
    return bookingResponse;
  }

  public static BookingUpdateRequest createUpdateBookingRequest() {

    BookingUpdateRequest bookingUpdateRequest = new BookingUpdateRequest();
    bookingUpdateRequest.setGuest(new GuestRequest());
    bookingUpdateRequest.setStartDate(LocalDate.of(2024, 1, 1));
    bookingUpdateRequest.setEndDate(LocalDate.of(2024, 1, 30));
    return bookingUpdateRequest;
  }

  public static BookingRebookRequest createBookingRebookRequest() {

    BookingRebookRequest bookingRebookRequest = new BookingRebookRequest();
    bookingRebookRequest.setStartDate(LocalDate.of(2025, 1, 1));
    bookingRebookRequest.setEndDate(LocalDate.of(2025, 1, 30));
    return bookingRebookRequest;
  }

  public static Booking createBooking() {

    Booking booking = new Booking();
    Guest guest = new Guest();
    guest.setFirstName("Paulo");
    guest.setFirstName("Dutra");
    booking.setPropertyId("UUID");
    booking.setStartDate(LocalDate.of(2024, 1, 1));
    booking.setEndDate(LocalDate.of(2024, 1, 20));
    booking.setStatus(BookingStatus.SCHEDULED);
    booking.setGuest(guest);
    return booking;
  }
}
