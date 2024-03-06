package com.hostfully.booking.block.repositories;

import com.hostfully.booking.block.domain.vos.models.Block;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BlockRepository extends CrudRepository<Block, String> {

  @Query(
      "SELECT b FROM Block b "
          + "WHERE b.propertyId = :propertyId "
          + "AND b.startDate <= :endDate "
          + "AND b.endDate >= :startDate")
  List<Block> findOverlappingBlocks(
      @Param("propertyId") String propertyId,
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);

  @Query(
      "SELECT b FROM Block b "
          + "WHERE b.propertyId = :propertyId "
          + "AND b.startDate <= :endDate "
          + "AND b.endDate >= :startDate "
          + "AND b.id != :id")
  List<Block> findOverlappingBlocksWithExcludeId(
      @Param("propertyId") String propertyId,
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate,
      @Param("id") String id);
}
