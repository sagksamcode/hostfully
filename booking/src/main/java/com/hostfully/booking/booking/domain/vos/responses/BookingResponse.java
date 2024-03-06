package com.hostfully.booking.booking.domain.vos.responses;

import com.hostfully.booking.booking.domain.vos.requests.BookingStatus;
import java.time.LocalDate;

public class BookingResponse {

  private String id;

  private LocalDate startDate;

  private LocalDate endDate;

  private BookingStatus status;

  private GuestResponse guest;

  private String propertyId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public BookingStatus getStatus() {
    return status;
  }

  public void setStatus(BookingStatus status) {
    this.status = status;
  }

  public GuestResponse getGuest() {
    return guest;
  }

  public void setGuest(GuestResponse guest) {
    this.guest = guest;
  }

  public String getPropertyId() {
    return propertyId;
  }

  public void setPropertyId(String propertyId) {
    this.propertyId = propertyId;
  }
}
