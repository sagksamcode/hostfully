package com.hostfully.booking.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.hostfully.booking.block.domain.vos.models.Block;
import com.hostfully.booking.block.repositories.BlockRepository;
import com.hostfully.booking.booking.domain.vos.models.Booking;
import com.hostfully.booking.booking.repositories.BookingRepository;
import com.hostfully.booking.exceptions.BadRequestException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ValidationServiceTest {

  @Mock private BookingRepository bookingRepository;

  @Mock private BlockRepository blockRepository;

  @InjectMocks private ValidationService validationService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void validateBookingsInRangeWithNullExcludeId() {
    String propertyId = "propertyId";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(1);

    assertDoesNotThrow(
        () -> validationService.validateBookingsInRange(propertyId, startDate, endDate, null));

    verify(bookingRepository, times(1)).findOverlappingBookings(propertyId, startDate, endDate);
    verifyNoInteractions(blockRepository);
  }

  @Test
  void validateBookingsInRangeWithNonNullExcludeId() {
    String propertyId = "propertyId";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(1);
    String excludeId = "excludeId";

    assertDoesNotThrow(
        () -> validationService.validateBookingsInRange(propertyId, startDate, endDate, excludeId));

    verify(bookingRepository, times(1))
        .findOverlappingBookingsWithExcludeId(propertyId, startDate, endDate, excludeId);
    verifyNoInteractions(blockRepository);
  }

  @Test
  void validateBlocksInRangeWithNullExcludeId() {
    String propertyId = "propertyId";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(1);

    assertDoesNotThrow(
        () -> validationService.validateBlocksInRange(propertyId, startDate, endDate, null));

    verify(blockRepository, times(1)).findOverlappingBlocks(propertyId, startDate, endDate);
    verifyNoInteractions(bookingRepository);
  }

  @Test
  void validateBlocksInRangeWithNonNullExcludeId() {
    String propertyId = "propertyId";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(1);
    String excludeId = "excludeId";

    assertDoesNotThrow(
        () -> validationService.validateBlocksInRange(propertyId, startDate, endDate, excludeId));

    verify(blockRepository, times(1))
        .findOverlappingBlocksWithExcludeId(propertyId, startDate, endDate, excludeId);
    verifyNoInteractions(bookingRepository);
  }

  @Test
  void validateBookingsInRangeThrowsException() {
    String propertyId = "propertyId";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(1);
    List<Booking> overlappingBookings = new ArrayList<>();
    overlappingBookings.add(new Booking()); // Adding a booking to simulate overlapping bookings

    when(bookingRepository.findOverlappingBookings(propertyId, startDate, endDate))
        .thenReturn(overlappingBookings);

    assertThrows(
        BadRequestException.class,
        () -> validationService.validateBookingsInRange(propertyId, startDate, endDate, null));
  }

  @Test
  void validateBlocksInRangeThrowsException() {
    String propertyId = "propertyId";
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(1);
    List<Block> overlappingBlocks = new ArrayList<>();
    overlappingBlocks.add(new Block()); // Adding a block to simulate overlapping blocks

    when(blockRepository.findOverlappingBlocks(propertyId, startDate, endDate))
        .thenReturn(overlappingBlocks);

    assertThrows(
        BadRequestException.class,
        () -> validationService.validateBlocksInRange(propertyId, startDate, endDate, null));
  }
}
