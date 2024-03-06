package com.hostfully.booking.block.fixture;

import com.hostfully.booking.block.domain.vos.models.Block;
import com.hostfully.booking.block.domain.vos.request.BlockRequest;
import com.hostfully.booking.block.domain.vos.request.BlockUpdateRequest;
import com.hostfully.booking.block.domain.vos.response.BlockPostResponse;
import java.time.LocalDate;

public class BlockFixture {

  public static BlockRequest createBlockRequest() {

    BlockRequest blockRequest = new BlockRequest();
    blockRequest.setStartDate(LocalDate.of(2024, 1, 20));
    blockRequest.setEndDate(LocalDate.of(2025, 1, 30));
    blockRequest.setReason("Unavailable House");
    blockRequest.setPropertyId("UUID");

    return blockRequest;
  }

  public static BlockPostResponse createBlockPostResponse() {

    BlockPostResponse blockPostResponse = new BlockPostResponse();
    blockPostResponse.setId("UUID");

    return blockPostResponse;
  }

  public static BlockUpdateRequest createBlockUpdateRequest() {

    BlockUpdateRequest blockUpdateRequest = new BlockUpdateRequest();
    blockUpdateRequest.setStartDate(LocalDate.of(2025, 1, 1));
    blockUpdateRequest.setEndDate(LocalDate.of(2025, 1, 28));
    blockUpdateRequest.setReason("Unavailable House");

    return blockUpdateRequest;
  }

  public static Block createBlock() {

    Block block = new Block();
    block.setStartDate(LocalDate.of(2025, 1, 1));
    block.setEndDate(LocalDate.of(2025, 1, 28));
    block.setReason("Unavailable House");
    block.setPropertyId("UUID");
    return block;
  }
}
