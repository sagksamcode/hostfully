package com.hostfully.booking.block.domain.vos.models;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Block {

  @Id private String id;
  private LocalDate startDate;
  private LocalDate endDate;
  private String propertyId;
  private String reason;

  public Block() {

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

  public String getPropertyId() {
    return propertyId;
  }

  public void setPropertyId(String propertyId) {
    this.propertyId = propertyId;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
