package com.hostfully.booking.booking.domain.vos.requests;

import javax.validation.constraints.NotEmpty;

public class GuestRequest {

  @NotEmpty private String firstName;

  @NotEmpty private String lastName;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
