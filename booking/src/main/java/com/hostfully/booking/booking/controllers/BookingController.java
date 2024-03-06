package com.hostfully.booking.booking.controllers;

import com.hostfully.booking.booking.domain.vos.requests.BookingRebookRequest;
import com.hostfully.booking.booking.domain.vos.requests.BookingRequest;
import com.hostfully.booking.booking.domain.vos.requests.BookingUpdateRequest;
import com.hostfully.booking.booking.domain.vos.responses.BookingPostResponse;
import com.hostfully.booking.booking.domain.vos.responses.BookingResponse;
import com.hostfully.booking.booking.services.BookingService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

  @Autowired BookingService service;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookingPostResponse> createBooking(
      @Valid @RequestBody BookingRequest bookingRequest) {

    final BookingPostResponse bookingPostResponse = service.createBooking(bookingRequest);
    return new ResponseEntity<>(bookingPostResponse, HttpStatus.CREATED);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookingResponse>> retrieveBookings() {

    final List<BookingResponse> bookings = service.retrieveBookings();
    return new ResponseEntity<>(bookings, HttpStatus.OK);
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookingResponse> retrieveBookingById(@PathVariable final String id) {

    final BookingResponse booking = service.retrieveBookingById(id);
    return new ResponseEntity<>(booking, HttpStatus.OK);
  }

  @PatchMapping(path = "/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookingPostResponse> cancelBooking(@PathVariable final String id) {

    final BookingPostResponse bookingPostResponse = service.cancelBooking(id);
    return new ResponseEntity<>(bookingPostResponse, HttpStatus.OK);
  }

  @PatchMapping(value = "/{id}/reschedule", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookingPostResponse> rescheduleBooking(
      @PathVariable final String id,
      @Valid @RequestBody final BookingRebookRequest bookingRebookRequest) {

    final BookingPostResponse bookingPostResponse =
        service.rescheduleBooking(id, bookingRebookRequest);
    return new ResponseEntity<>(bookingPostResponse, HttpStatus.OK);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookingPostResponse> updateBooking(
      @PathVariable final String id, @Valid @RequestBody BookingUpdateRequest bookingRequest) {

    final BookingPostResponse bookingPostResponse = service.updateBooking(id, bookingRequest);
    return new ResponseEntity<>(bookingPostResponse, HttpStatus.OK);
  }

  @DeleteMapping(
      path = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookingPostResponse> deleteBooking(@PathVariable String id) {

    service.deleteBooking(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
