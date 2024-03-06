package com.hostfully.booking.booking.domain.vos.requests;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

public class BookingRebookRequest {

  @NotNull private LocalDate startDate;

  @NotNull private LocalDate endDate;

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }
}
