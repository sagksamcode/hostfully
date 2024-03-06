package com.hostfully.booking.block.services;

import com.hostfully.booking.block.builders.BlockBuilder;
import com.hostfully.booking.block.builders.BlockPostResponseBuilder;
import com.hostfully.booking.block.domain.vos.models.Block;
import com.hostfully.booking.block.domain.vos.request.BlockRequest;
import com.hostfully.booking.block.domain.vos.request.BlockUpdateRequest;
import com.hostfully.booking.block.domain.vos.response.BlockPostResponse;
import com.hostfully.booking.block.repositories.BlockRepository;
import com.hostfully.booking.exceptions.NotFoundException;
import com.hostfully.booking.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockService extends ValidationService {

  @Autowired private BlockRepository repository;

  public BlockPostResponse createBlock(final BlockRequest blockRequest) {

    validateBookingsInRange(
        blockRequest.getPropertyId(), blockRequest.getStartDate(), blockRequest.getEndDate(), null);
    validateBlocksInRange(
        blockRequest.getPropertyId(), blockRequest.getStartDate(), blockRequest.getEndDate(), null);
    final Block block =
        new BlockBuilder()
            .withStartDate(blockRequest.getStartDate())
            .withEndDate(blockRequest.getEndDate())
            .withPropertyId(blockRequest.getPropertyId())
            .withReason(blockRequest.getReason())
            .build();
    repository.save(block);
    return new BlockPostResponseBuilder().withId(block.getId()).build();
  }

  public BlockPostResponse updateBlock(
      final String id, final BlockUpdateRequest blockUpdateRequest) {

    final Block block =
        repository.findById(id).orElseThrow(() -> NotFoundException.blockNotFoundById());
    validateBookingsInRange(
        block.getPropertyId(),
        blockUpdateRequest.getStartDate(),
        blockUpdateRequest.getEndDate(),
        null);
    validateBlocksInRange(
        block.getPropertyId(), block.getStartDate(), block.getEndDate(), block.getId());
    block.setReason(blockUpdateRequest.getReason());
    block.setStartDate(blockUpdateRequest.getStartDate());
    block.setEndDate(blockUpdateRequest.getEndDate());
    repository.save(block);
    return new BlockPostResponseBuilder().withId(block.getId()).build();
  }

  public void deleteBlock(final String id) {
    repository.findById(id).orElseThrow(() -> NotFoundException.blockNotFoundById());
    repository.deleteById(id);
  }
}
