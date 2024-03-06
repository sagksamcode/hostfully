package com.hostfully.booking.block.controllers;

import com.hostfully.booking.block.domain.vos.request.BlockRequest;
import com.hostfully.booking.block.domain.vos.request.BlockUpdateRequest;
import com.hostfully.booking.block.domain.vos.response.BlockPostResponse;
import com.hostfully.booking.block.services.BlockService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blocks")
public class BlockController {

  @Autowired private BlockService service;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BlockPostResponse> createBlock(
      @RequestBody @Valid BlockRequest blockRequest) {

    final BlockPostResponse blockPostResponse = service.createBlock(blockRequest);
    return new ResponseEntity<>(blockPostResponse, HttpStatus.CREATED);
  }

  @PutMapping(
      path = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BlockPostResponse> updateBlock(
      @PathVariable final String id, @RequestBody @Valid BlockUpdateRequest blockUpdateRequest) {

    final BlockPostResponse blockPostResponse = service.updateBlock(id, blockUpdateRequest);
    return new ResponseEntity<>(blockPostResponse, HttpStatus.OK);
  }

  @DeleteMapping(
      path = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BlockPostResponse> deleteBlock(@PathVariable String id) {

    service.deleteBlock(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
