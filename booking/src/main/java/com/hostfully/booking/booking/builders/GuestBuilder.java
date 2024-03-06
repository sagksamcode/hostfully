package com.hostfully.booking.booking.builders;

import com.hostfully.booking.booking.domain.vos.models.Guest;

public class GuestBuilder {

  public Guest guest = new Guest();

  public GuestBuilder withFirstName(final String firstName) {

    guest.setFirstName(firstName);
    return this;
  }

  public GuestBuilder withLastName(final String lastName) {

    guest.setLastName(lastName);
    return this;
  }

  public Guest build() {
    return guest;
  }
}
