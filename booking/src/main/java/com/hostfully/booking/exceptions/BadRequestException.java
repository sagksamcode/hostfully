package com.hostfully.booking.exceptions;

public class BadRequestException extends GlobalException {

  private static final long serialVersionUID = 1L;

  private BadRequestException(final Issue issue) {
    super(issue);
  }

  public static BadRequestException bookingsOverlappingDate() {

    return new BadRequestException(new Issue(IssueEnum.BOOKING_OVERLAPPING_DATE));
  }

  public static BadRequestException blockOverlappingDate() {

    return new BadRequestException(new Issue(IssueEnum.BLOCK_OVERLAPPING_DATE));
  }

  public static BadRequestException isAfterException() {

    return new BadRequestException(new Issue(IssueEnum.START_DATE_AFTER_END_DATE));
  }
}
