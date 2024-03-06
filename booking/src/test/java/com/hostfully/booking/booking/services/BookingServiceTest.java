package com.hostfully.booking.booking.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.hostfully.booking.block.repositories.BlockRepository;
import com.hostfully.booking.booking.domain.vos.models.Booking;
import com.hostfully.booking.booking.domain.vos.requests.*;
import com.hostfully.booking.booking.domain.vos.responses.BookingPostResponse;
import com.hostfully.booking.booking.domain.vos.responses.BookingResponse;
import com.hostfully.booking.booking.fixture.BookingFixture;
import com.hostfully.booking.booking.repositories.BookingRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookingServiceTest {

  @Mock private BookingRepository bookingRepository;

  @Mock private BlockRepository blockRepository;

  @InjectMocks private BookingService bookingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void createBooking() {
    BookingRequest bookingRequest = BookingFixture.createBookingRequest();
    BookingPostResponse expectedResponse = new BookingPostResponse();

    assertDoesNotThrow(
        () -> {
          when(bookingRepository.save(any(Booking.class))).thenReturn(new Booking());
          BookingPostResponse response = bookingService.createBooking(bookingRequest);
          assertNotNull(response);
        });

    verify(bookingRepository, times(1)).save(any(Booking.class));
    verify(bookingRepository, times(1))
        .findOverlappingBookings(anyString(), any(LocalDate.class), any(LocalDate.class));
    verify(blockRepository, times(1))
        .findOverlappingBlocks(anyString(), any(LocalDate.class), any(LocalDate.class));
  }

  @Test
  void retrieveBookings() {
    List<Booking> bookings = new ArrayList<>();
    bookings.add(BookingFixture.createBooking());
    when(bookingRepository.findAll()).thenReturn(bookings);

    List<BookingResponse> response = bookingService.retrieveBookings();
    assertNotNull(response);
    assertFalse(response.isEmpty());
    assertEquals(bookings.size(), response.size());

    verify(bookingRepository, times(1)).findAll();
  }

  @Test
  void retrieveBookingById() {
    Booking booking = BookingFixture.createBooking();
    when(bookingRepository.findById(anyString())).thenReturn(Optional.of(booking));

    BookingResponse response = bookingService.retrieveBookingById("123");
    assertNotNull(response);

    verify(bookingRepository, times(1)).findById(anyString());
  }

  @Test
  void cancelBooking() {
    Booking booking = new Booking();
    when(bookingRepository.findById(anyString())).thenReturn(Optional.of(booking));

    BookingPostResponse response = bookingService.cancelBooking("123");
    assertNotNull(response);
    assertEquals(BookingStatus.CANCELLED, booking.getStatus());

    verify(bookingRepository, times(1)).findById(anyString());
    verify(bookingRepository, times(1)).save(any(Booking.class));
  }

  @Test
  void rescheduleBooking() {
    Booking booking = BookingFixture.createBooking();
    when(bookingRepository.findById(anyString())).thenReturn(Optional.of(booking));
    BookingRebookRequest rebookRequest = new BookingRebookRequest();
    rebookRequest.setStartDate(LocalDate.now());
    rebookRequest.setEndDate(LocalDate.now().plusDays(1));

    BookingPostResponse response = bookingService.rescheduleBooking("123", rebookRequest);
    assertNotNull(response);
    assertEquals(BookingStatus.SCHEDULED, booking.getStatus());

    verify(bookingRepository, times(1)).findById(anyString());
    verify(bookingRepository, times(1)).save(any(Booking.class));
    verify(bookingRepository, times(1))
        .findOverlappingBookingsWithExcludeId(
            anyString(), any(LocalDate.class), any(LocalDate.class), any(String.class));
    verify(blockRepository, times(1))
        .findOverlappingBlocks(anyString(), any(LocalDate.class), any(LocalDate.class));
  }

  @Test
  void updateBooking() {
    Booking booking = BookingFixture.createBooking();
    when(bookingRepository.findById(anyString())).thenReturn(Optional.of(booking));
    BookingUpdateRequest updateRequest = BookingFixture.createUpdateBookingRequest();
    updateRequest.setStartDate(LocalDate.now());
    updateRequest.setEndDate(LocalDate.now().plusDays(1));

    BookingPostResponse response = bookingService.updateBooking("123", updateRequest);
    assertNotNull(response);
    assertEquals(BookingStatus.SCHEDULED, booking.getStatus());

    verify(bookingRepository, times(1)).findById(anyString());
    verify(bookingRepository, times(1)).save(any(Booking.class));
    verify(bookingRepository, times(1))
        .findOverlappingBookingsWithExcludeId(
            anyString(), any(LocalDate.class), any(LocalDate.class), any(String.class));
    verify(blockRepository, times(1))
        .findOverlappingBlocks(anyString(), any(LocalDate.class), any(LocalDate.class));
  }

  @Test
  void deleteBooking() {
    Booking booking = BookingFixture.createBooking();
    when(bookingRepository.findById(anyString())).thenReturn(Optional.of(booking));
    assertDoesNotThrow(
        () -> {
          bookingService.deleteBooking("123");
        });

    verify(bookingRepository, times(1)).deleteById(anyString());
  }
}
