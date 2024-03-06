package com.hostfully.booking.block.domain.vos.request;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BlockRequest {

  @NotNull private LocalDate startDate;
  @NotNull private LocalDate endDate;
  @NotBlank private String propertyId;
  @NotEmpty private String reason;

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public String getPropertyId() {
    return propertyId;
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

  public void setPropertyId(String propertyId) {
    this.propertyId = propertyId;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
