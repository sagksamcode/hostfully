package com.hostfully.booking.services;

import com.hostfully.booking.block.domain.vos.models.Block;
import com.hostfully.booking.block.repositories.BlockRepository;
import com.hostfully.booking.booking.domain.vos.models.Booking;
import com.hostfully.booking.booking.repositories.BookingRepository;
import com.hostfully.booking.exceptions.BadRequestException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationService {

  @Autowired private BookingRepository bookingRepository;

  @Autowired private BlockRepository blockRepository;

  public void validateBookingsInRange(
      final String propertyId,
      final LocalDate startDate,
      final LocalDate endDate,
      final String excludeId) {

    validDateRange(startDate, endDate);
    if (Objects.isNull(excludeId)) {
      validateBookingInRangeWithoutExcludeId(propertyId, startDate, endDate);
    } else {
      validateBookingsInRangeWithExcludeId(propertyId, startDate, endDate, excludeId);
    }
  }

  public void validateBlocksInRange(
      final String propertyId,
      final LocalDate startDate,
      final LocalDate endDate,
      final String excludeId) {

    validDateRange(startDate, endDate);
    if (Objects.isNull(excludeId))
      validateBlocksInRangeWithoutExcludeId(propertyId, startDate, endDate);
    else {
      validateBlocksInRangeWithExcludeId(propertyId, startDate, endDate, excludeId);
    }
  }

  private void validDateRange(final LocalDate startDate, final LocalDate endDate) {

    if (startDate.isAfter(endDate)) {
      throw BadRequestException.isAfterException();
    }
  }

  private void validateBlocksInRangeWithExcludeId(
      final String propertyId,
      final LocalDate startDate,
      final LocalDate endDate,
      final String excludeId) {

    List<Block> overlappingBlocks =
        blockRepository.findOverlappingBlocksWithExcludeId(
            propertyId, startDate, endDate, excludeId);
    if (!overlappingBlocks.isEmpty()) throw BadRequestException.blockOverlappingDate();
  }

  private void validateBlocksInRangeWithoutExcludeId(
      final String propertyId, final LocalDate startDate, final LocalDate endDate) {

    List<Block> overlappingBlocks =
        blockRepository.findOverlappingBlocks(propertyId, startDate, endDate);
    if (!overlappingBlocks.isEmpty()) throw BadRequestException.blockOverlappingDate();
  }

  private void validateBookingsInRangeWithExcludeId(
      final String propertyId,
      final LocalDate startDate,
      final LocalDate endDate,
      final String excludeId) {

    List<Booking> overlappingBookings =
        bookingRepository.findOverlappingBookingsWithExcludeId(
            propertyId, startDate, endDate, excludeId);
    if (!overlappingBookings.isEmpty()) throw BadRequestException.bookingsOverlappingDate();
  }

  private void validateBookingInRangeWithoutExcludeId(
      final String propertyId, final LocalDate startDate, final LocalDate endDate) {

    List<Booking> overlappingBookings =
        bookingRepository.findOverlappingBookings(propertyId, startDate, endDate);
    if (!overlappingBookings.isEmpty()) throw BadRequestException.bookingsOverlappingDate();
  }
}
