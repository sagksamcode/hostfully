package com.hostfully.booking.block.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.hostfully.booking.block.domain.vos.models.Block;
import com.hostfully.booking.block.domain.vos.request.BlockRequest;
import com.hostfully.booking.block.domain.vos.request.BlockUpdateRequest;
import com.hostfully.booking.block.domain.vos.response.BlockPostResponse;
import com.hostfully.booking.block.fixture.BlockFixture;
import com.hostfully.booking.block.repositories.BlockRepository;
import com.hostfully.booking.booking.repositories.BookingRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BlockServiceTest {

  @Mock private BlockRepository blockRepository;

  @Mock private BookingRepository bookingRepository;

  @InjectMocks private BlockService blockService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void createBlock() {
    BlockRequest blockRequest = BlockFixture.createBlockRequest();
    assertDoesNotThrow(
        () -> {
          when(blockRepository.save(any(Block.class))).thenReturn(new Block());
          BlockPostResponse response = blockService.createBlock(blockRequest);
          assertNotNull(response);
        });

    verify(blockRepository, times(1)).save(any(Block.class));
    verify(blockRepository, times(1))
        .findOverlappingBlocks(anyString(), any(LocalDate.class), any(LocalDate.class));
  }

  @Test
  void updateBlock() {
    String id = "123";
    BlockUpdateRequest updateRequest = BlockFixture.createBlockUpdateRequest();
    Block block = BlockFixture.createBlock();
    when(blockRepository.findById(anyString())).thenReturn(Optional.of(block));

    assertDoesNotThrow(
        () -> {
          BlockPostResponse response = blockService.updateBlock(id, updateRequest);
          assertNotNull(response);
        });

    verify(blockRepository, times(1)).findById(anyString());
    verify(blockRepository, times(1)).save(any(Block.class));
    verify(blockRepository, times(1))
        .findOverlappingBlocksWithExcludeId(
            anyString(), any(LocalDate.class), any(LocalDate.class), any(String.class));
  }

  @Test
  void deleteBlock() {
    Block block = BlockFixture.createBlock();
    when(blockRepository.findById(anyString())).thenReturn(Optional.of(block));
    assertDoesNotThrow(
        () -> {
          blockService.deleteBlock("123");
        });

    verify(blockRepository, times(1)).deleteById("123");
  }
}
