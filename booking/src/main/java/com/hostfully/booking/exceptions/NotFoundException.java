package com.hostfully.booking.exceptions;

public class NotFoundException extends GlobalException {

  private static final long serialVersionUID = 1L;

  protected NotFoundException(final Issue issue) {
    super(issue);
  }

  public static NotFoundException bookingsNotFound() {

    return new NotFoundException(new Issue(IssueEnum.NOT_FOUND_BOOKINGS));
  }

  public static NotFoundException bookingsNotFoundById() {

    return new NotFoundException(new Issue(IssueEnum.NOT_FOUND_BOOKING_ID));
  }

  public static NotFoundException blockNotFoundById() {

    return new NotFoundException(new Issue(IssueEnum.NOT_FOUND_BLOCK_ID));
  }
}
