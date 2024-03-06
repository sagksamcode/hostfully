package com.hostfully.booking.booking.builders;

import com.hostfully.booking.booking.domain.vos.requests.BookingStatus;
import com.hostfully.booking.booking.domain.vos.responses.BookingResponse;
import com.hostfully.booking.booking.domain.vos.responses.GuestResponse;
import java.time.LocalDate;

public class BookingResponseBuilder {

  private BookingResponse bookingResponse = new BookingResponse();

  public BookingResponseBuilder withId(final String id) {

    bookingResponse.setId(id);
    return this;
  }

  public BookingResponseBuilder withStartDate(final LocalDate startDate) {
    bookingResponse.setStartDate(startDate);
    return this;
  }

  public BookingResponseBuilder withEndDate(final LocalDate endDate) {
    bookingResponse.setEndDate(endDate);
    return this;
  }

  public BookingResponseBuilder withStatus(final BookingStatus status) {
    bookingResponse.setStatus(status);
    return this;
  }

  public BookingResponseBuilder withGuest(final GuestResponse guest) {
    bookingResponse.setGuest(guest);
    return this;
  }

  public BookingResponseBuilder withPropertyId(final String propertyId) {
    bookingResponse.setPropertyId(propertyId);
    return this;
  }

  public BookingResponse build() {
    return bookingResponse;
  }
}
