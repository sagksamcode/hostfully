package com.hostfully.booking.booking.builders;

import com.hostfully.booking.booking.domain.vos.responses.GuestResponse;

public class GuestResponseBuilder {

  public GuestResponse guest = new GuestResponse();

  public GuestResponseBuilder withFirstName(final String firstName) {

    guest.setFirstName(firstName);
    return this;
  }

  public GuestResponseBuilder withLastName(final String lastName) {

    guest.setLastName(lastName);
    return this;
  }

  public GuestResponse build() {
    return guest;
  }
}
