package com.nnam01.MyStudy.global.exception;

import jakarta.persistence.EntityNotFoundException;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProblemDetailExceptionHandler extends ResponseEntityExceptionHandler {

  // Bad Request 400
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ProblemDetail> handleException(
      IllegalArgumentException e, WebRequest request) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());

    problemDetail.setType(URI.create("about:blank"));
    problemDetail.setTitle("Bad Request");
    problemDetail.setInstance(URI.create(request.getContextPath()));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
  }

  // Unauthorized 401
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ProblemDetail> handleException(
      UnauthorizedException e, WebRequest request) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());

    problemDetail.setType(URI.create("about:blank"));
    problemDetail.setTitle("Unauthorized");
    problemDetail.setInstance(URI.create(request.getContextPath()));

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
  }

  // Forbidden 403
  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ProblemDetail> handleException(ForbiddenException e, WebRequest request) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());

    problemDetail.setType(URI.create("about:blank"));
    problemDetail.setTitle("Forbidden");
    problemDetail.setInstance(URI.create(request.getContextPath()));

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problemDetail);
  }

  // Not Found 404
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleException(
      EntityNotFoundException e, WebRequest request) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

    problemDetail.setType(URI.create("about:blank"));
    problemDetail.setTitle("Not Found");
    problemDetail.setInstance(URI.create(request.getContextPath()));

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
  }

  // RuntimeException 다 서버문제로 예외 처리
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Object> handleException(RuntimeException e, WebRequest request) {
    return this.handleExceptionInternal(e, null, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
