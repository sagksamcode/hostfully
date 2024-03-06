package com.hostfully.booking.booking.domain.vos.models;

import com.hostfully.booking.booking.domain.vos.requests.BookingStatus;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Booking {

  @Id private String id;

  private LocalDate startDate;

  private LocalDate endDate;

  @OneToOne(cascade = CascadeType.ALL)
  private Guest guest;

  private BookingStatus status;

  private String propertyId;

  public Booking() {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Guest getGuest() {
    return guest;
  }

  public void setGuest(Guest guest) {
    this.guest = guest;
  }

  public BookingStatus getStatus() {
    return status;
  }

  public void setStatus(BookingStatus status) {
    this.status = status;
  }

  public String getPropertyId() {
    return propertyId;
  }

  public void setPropertyId(String propertyId) {
    this.propertyId = propertyId;
  }
}
