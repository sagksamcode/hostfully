package com.hostfully.booking.booking.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hostfully.booking.booking.domain.vos.requests.BookingRebookRequest;
import com.hostfully.booking.booking.domain.vos.requests.BookingRequest;
import com.hostfully.booking.booking.domain.vos.requests.BookingUpdateRequest;
import com.hostfully.booking.booking.domain.vos.responses.BookingPostResponse;
import com.hostfully.booking.booking.domain.vos.responses.BookingResponse;
import com.hostfully.booking.booking.fixture.BookingFixture;
import com.hostfully.booking.booking.services.BookingService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

  @Mock private BookingService bookingService;

  @InjectMocks private BookingController bookingController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void createBooking() {
    BookingRequest bookingRequest = BookingFixture.createBookingRequest();
    BookingPostResponse bookingPostResponse = BookingFixture.createBookingPostResponse();

    when(bookingService.createBooking(bookingRequest)).thenReturn(bookingPostResponse);

    ResponseEntity<BookingPostResponse> responseEntity =
        bookingController.createBooking(bookingRequest);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(bookingPostResponse, responseEntity.getBody());
  }

  @Test
  void retrieveBookings() {
    List<BookingResponse> bookings =
        Collections.singletonList(BookingFixture.createBookingResponse());

    when(bookingService.retrieveBookings()).thenReturn(bookings);

    ResponseEntity<List<BookingResponse>> responseEntity = bookingController.retrieveBookings();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(bookings, responseEntity.getBody());
  }

  @Test
  void retrieveBookingById() {
    String bookingId = "bookingId";
    BookingResponse bookingResponse =
        BookingFixture.createBookingResponse(); // Create a simulated booking response

    when(bookingService.retrieveBookingById(bookingId)).thenReturn(bookingResponse);

    ResponseEntity<BookingResponse> responseEntity =
        bookingController.retrieveBookingById(bookingId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(bookingResponse, responseEntity.getBody());
  }

  @Test
  void cancelBooking() {
    String bookingId = "bookingId";
    BookingPostResponse bookingPostResponse =
        BookingFixture.createBookingPostResponse(); // Create a simulated booking post response

    when(bookingService.cancelBooking(bookingId)).thenReturn(bookingPostResponse);

    ResponseEntity<BookingPostResponse> responseEntity = bookingController.cancelBooking(bookingId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(bookingPostResponse, responseEntity.getBody());
  }

  @Test
  void rescheduleBooking() {
    String bookingId = "123";
    BookingRebookRequest bookingRebookRequest = BookingFixture.createBookingRebookRequest();
    BookingPostResponse bookingPostResponse = BookingFixture.createBookingPostResponse();

    when(bookingService.rescheduleBooking(bookingId, bookingRebookRequest))
        .thenReturn(bookingPostResponse);

    ResponseEntity<BookingPostResponse> responseEntity =
        bookingController.rescheduleBooking(bookingId, bookingRebookRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(bookingPostResponse, responseEntity.getBody());
  }

  @Test
  void updateBooking() {
    String bookingId = "123";
    BookingUpdateRequest bookingUpdateRequest = BookingFixture.createUpdateBookingRequest();
    BookingPostResponse bookingPostResponse = BookingFixture.createBookingPostResponse();

    when(bookingService.updateBooking(bookingId, bookingUpdateRequest))
        .thenReturn(bookingPostResponse);

    ResponseEntity<BookingPostResponse> responseEntity =
        bookingController.updateBooking(bookingId, bookingUpdateRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(bookingPostResponse, responseEntity.getBody());
  }

  @Test
  void deleteBooking() {
    String bookingId = "123";

    ResponseEntity<BookingPostResponse> responseEntity = bookingController.deleteBooking(bookingId);

    verify(bookingService).deleteBooking(bookingId);
    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }
}
