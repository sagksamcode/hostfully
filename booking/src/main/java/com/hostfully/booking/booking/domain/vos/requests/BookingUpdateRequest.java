package com.hostfully.booking.booking.domain.vos.requests;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

public class BookingUpdateRequest {

  @NotNull private LocalDate startDate;

  @NotNull private LocalDate endDate;

  @NotNull private GuestRequest guest;

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public GuestRequest getGuest() {
    return guest;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public void setGuest(GuestRequest guest) {
    this.guest = guest;
  }
}
