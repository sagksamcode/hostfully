package com.hostfully.booking.block.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.hostfully.booking.block.domain.vos.request.BlockRequest;
import com.hostfully.booking.block.domain.vos.request.BlockUpdateRequest;
import com.hostfully.booking.block.domain.vos.response.BlockPostResponse;
import com.hostfully.booking.block.fixture.BlockFixture;
import com.hostfully.booking.block.services.BlockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BlockControllerTest {

  @Mock private BlockService blockService;

  @InjectMocks private BlockController blockController;

  @Test
  public void testCreateBlock() {
    BlockRequest blockRequest = BlockFixture.createBlockRequest();
    when(blockService.createBlock(any(BlockRequest.class)))
        .thenReturn(BlockFixture.createBlockPostResponse());
    ResponseEntity<BlockPostResponse> responseEntity = blockController.createBlock(blockRequest);
    verify(blockService, times(1)).createBlock(any(BlockRequest.class));
    assert responseEntity.getStatusCode() == HttpStatus.CREATED;
  }

  @Test
  public void testUpdateBlock() {
    String id = "blockId";
    BlockUpdateRequest blockUpdateRequest = BlockFixture.createBlockUpdateRequest();
    when(blockService.updateBlock(eq(id), any(BlockUpdateRequest.class)))
        .thenReturn(new BlockPostResponse());
    ResponseEntity<BlockPostResponse> responseEntity =
        blockController.updateBlock(id, blockUpdateRequest);
    verify(blockService, times(1)).updateBlock(eq(id), any(BlockUpdateRequest.class));
    assert responseEntity.getStatusCode() == HttpStatus.OK;
  }

  @Test
  public void testDeleteBlock() {
    String id = "blockId";
    ResponseEntity<BlockPostResponse> responseEntity = blockController.deleteBlock(id);
    verify(blockService, times(1)).deleteBlock(eq(id));
    assert responseEntity.getStatusCode() == HttpStatus.NO_CONTENT;
  }
}
