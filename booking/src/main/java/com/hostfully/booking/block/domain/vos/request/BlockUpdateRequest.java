package com.hostfully.booking.block.domain.vos.request;

import java.time.LocalDate;

public class BlockUpdateRequest {

  private LocalDate startDate;
  private LocalDate endDate;
  private String reason;

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public String getReason() {
    return reason;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
