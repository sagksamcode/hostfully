package com.hostfully.booking.block.builders;

import com.hostfully.booking.block.domain.vos.response.BlockPostResponse;

public class BlockPostResponseBuilder {

  private BlockPostResponse blockPostResponse = new BlockPostResponse();

  public BlockPostResponseBuilder withId(final String id) {

    blockPostResponse.setId(id);
    return this;
  }

  public BlockPostResponse build() {
    return blockPostResponse;
  }
}
