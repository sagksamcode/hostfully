package com.hostfully.booking.booking.builders;

import com.hostfully.booking.booking.domain.vos.requests.BookingStatus;
import com.hostfully.booking.booking.domain.vos.responses.BookingPostResponse;

public class BookingPostResponseBuilder {

  private BookingPostResponse bookingPostResponse = new BookingPostResponse();

  public BookingPostResponseBuilder withId(final String id) {

    bookingPostResponse.setId(id);
    return this;
  }

  public BookingPostResponseBuilder withStatus(final BookingStatus status) {

    bookingPostResponse.setStatus(status);
    return this;
  }

  public BookingPostResponse build() {
    return bookingPostResponse;
  }
}
