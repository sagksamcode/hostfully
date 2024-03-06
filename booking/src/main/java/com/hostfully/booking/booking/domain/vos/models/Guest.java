package com.hostfully.booking.booking.domain.vos.models;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Guest {

  @Id private String id;
  private String firstName;
  private String lastName;

  public Guest() {

    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
