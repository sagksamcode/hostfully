package com.hostfully.booking.exceptions;

import java.util.IllegalFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum IssueEnum {
  BAD_REQUEST_ERROR(1, "Bad Request."),

  MALFORMED_REQUEST_PAYLOAD(2, "Malformed request data."),

  NOT_FOUND_BOOKINGS(3, "Bookings not found"),

  NOT_FOUND_BOOKING_ID(4, "Booking not found for this id"),

  NOT_FOUND_BLOCK_ID(5, "Block not found for this id"),

  BOOKING_OVERLAPPING_DATE(6, "There is a booking overlapping with the specified date range"),

  BLOCK_OVERLAPPING_DATE(7, "There is a block overlapping with the specified date range"),

  START_DATE_AFTER_END_DATE(8, "The start date cannot be after the end date");

  private final Logger logger = LogManager.getLogger(IssueEnum.class);

  private int code;
  private String message;

  IssueEnum(final int code, final String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getFormattedMessage(final Object... args) {
    if (message == null) {
      return "";
    }

    try {
      return String.format(message, args);
    } catch (final IllegalFormatException e) {
      logger.warn(e.getMessage(), e);
      return message.replace("%s", "");
    }
  }
}
