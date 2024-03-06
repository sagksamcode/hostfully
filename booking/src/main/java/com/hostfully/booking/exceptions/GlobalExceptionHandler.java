package com.hostfully.booking.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected Issue processException(final Exception ex, final WebRequest webRequest) {

    LOGGER.error(ex);
    return new Issue(IssueEnum.BAD_REQUEST_ERROR, Arrays.asList(ex.getLocalizedMessage()));
  }

  @ExceptionHandler({BadRequestException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected Issue processBadRequestException(final GlobalException ex, final WebRequest request) {
    LOGGER.error(ex);
    return ex.getIssue();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected Issue handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex, final WebRequest request) {

    LOGGER.error(ex);
    final List<String> errors = new ArrayList<>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    return new Issue(IssueEnum.MALFORMED_REQUEST_PAYLOAD, errors);
  }

  @ExceptionHandler({NotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected Issue processNotFoundException(final GlobalException ex, final WebRequest request) {
    LOGGER.error(ex);
    return ex.getIssue();
  }
}
