package com.hostfully.booking.booking.builders;

import com.hostfully.booking.booking.domain.vos.models.Booking;
import com.hostfully.booking.booking.domain.vos.models.Guest;
import com.hostfully.booking.booking.domain.vos.requests.BookingStatus;
import java.time.LocalDate;

public class BookingBuilder {

  private Booking booking = new Booking();

  public BookingBuilder withGuest(final Guest guest) {

    booking.setGuest(guest);
    return this;
  }

  public BookingBuilder withStartDate(final LocalDate starDate) {

    booking.setStartDate(starDate);
    return this;
  }

  public BookingBuilder withEndDate(final LocalDate endDate) {

    booking.setEndDate(endDate);
    return this;
  }

  public BookingBuilder withStatus(final BookingStatus status) {

    booking.setStatus(status);
    return this;
  }

  public BookingBuilder withPropertyId(final String propertyId) {

    booking.setPropertyId(propertyId);
    return this;
  }

  public Booking build() {
    return booking;
  }
}
