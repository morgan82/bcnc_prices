package com.bcnc.price.adapter.in.api;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ProblemDetail> handleNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        val detail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        detail.setTitle("Resource not found");
        detail.setType(URI.create(getFullURL(request)));
        detail.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detail);
    }

    @ExceptionHandler({
            ConstraintViolationException.class
    })
    public ResponseEntity<ProblemDetail> handleConflict(RuntimeException ex, HttpServletRequest request) {
        val detail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        detail.setType(URI.create(getFullURL(request)));
        detail.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detail);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ProblemDetail> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
        val detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setType(URI.create(getFullURL(request)));
        detail.setDetail(ex.getMessage());
        return ResponseEntity.status(detail.getStatus()).body(detail);
    }

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ProblemDetail> handleGlobalException(Exception ex, HttpServletRequest request) {
        log.error("Exception {}", ex.getMessage());
        val detail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        detail.setType(URI.create(getFullURL(request)));
        detail.setDetail(ex.getMessage());
        return ResponseEntity.status(detail.getStatus()).body(detail);
    }

    private String getFullURL(HttpServletRequest request) {
        val requestURL = request.getRequestURL();
        val queryString = request.getQueryString();
        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

}
