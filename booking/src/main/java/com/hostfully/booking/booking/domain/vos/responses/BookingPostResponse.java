package com.hostfully.booking.booking.domain.vos.responses;

import com.hostfully.booking.booking.domain.vos.requests.BookingStatus;

public class BookingPostResponse {

  public String id;
  public BookingStatus status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BookingStatus getStatus() {
    return status;
  }

  public void setStatus(BookingStatus status) {
    this.status = status;
  }
}
