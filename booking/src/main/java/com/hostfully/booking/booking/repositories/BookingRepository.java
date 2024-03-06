package com.hostfully.booking.booking.repositories;

import com.hostfully.booking.booking.domain.vos.models.Booking;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, String> {

  @Query(
      "SELECT b FROM Booking b "
          + "WHERE b.propertyId = :propertyId "
          + "AND b.startDate <= :endDate "
          + "AND b.endDate >= :startDate "
          + "AND b.status != com.hostfully.booking.booking.domain.vos.requests.BookingStatus.CANCELLED")
  List<Booking> findOverlappingBookings(
      @Param("propertyId") String propertyId,
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);

  @Query(
      "SELECT b FROM Booking b "
          + "WHERE b.propertyId = :propertyId "
          + "AND b.startDate <= :endDate "
          + "AND b.endDate >= :startDate "
          + "AND b.status != com.hostfully.booking.booking.domain.vos.requests.BookingStatus.CANCELLED "
          + "AND b.id != :id")
  List<Booking> findOverlappingBookingsWithExcludeId(
      @Param("propertyId") String propertyId,
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate,
      @Param("id") String id);
}
