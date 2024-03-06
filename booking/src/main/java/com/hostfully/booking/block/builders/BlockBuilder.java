package com.hostfully.booking.block.builders;

import com.hostfully.booking.block.domain.vos.models.Block;
import java.time.LocalDate;

public class BlockBuilder {

  public Block block = new Block();

  public BlockBuilder withStartDate(final LocalDate startDate) {

    block.setStartDate(startDate);
    return this;
  }

  public BlockBuilder withEndDate(final LocalDate endDate) {

    block.setEndDate(endDate);
    return this;
  }

  public BlockBuilder withPropertyId(final String propertyId) {
    block.setPropertyId(propertyId);
    return this;
  }

  public BlockBuilder withReason(final String reason) {
    block.setReason(reason);
    return this;
  }

  public Block build() {

    return block;
  }
}
