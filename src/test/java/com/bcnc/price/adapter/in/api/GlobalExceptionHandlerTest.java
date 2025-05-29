package com.bcnc.price.adapter.in.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @Test
    void testHandleBadRequest_returnsCorrectProblemDetailAndStatus() {
        // Given
        val globalExceptionHandler = new GlobalExceptionHandler();
        val exception = new IllegalArgumentException("Bad request exception");
        val mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/bad-request"));
        when(mockRequest.getQueryString()).thenReturn(null);

        // When
        val response = globalExceptionHandler.handleBadRequest(exception, mockRequest);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad request exception", response.getBody().getDetail());
        assertEquals("http://localhost/bad-request", response.getBody().getType().toString());
    }

    @Test
    void testHandleGlobalException_returnsCorrectProblemDetailAndStatus() {
        // Given
        val globalExceptionHandler = new GlobalExceptionHandler();
        val exception = new Exception("Test exception");
        val mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/test"));
        when(mockRequest.getQueryString()).thenReturn(null);

        // When
        val response = globalExceptionHandler.handleGlobalException(exception, mockRequest);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Test exception", response.getBody().getDetail());
        assertEquals("http://localhost/test", response.getBody().getType().toString());
    }

    @Test
    void testHandleConflict_returnsCorrectProblemDetailAndStatus() {
        // Given
        val globalExceptionHandler = new GlobalExceptionHandler();
        val exception = new ConstraintViolationException("Conflict exception", null);
        val mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/conflict"));
        when(mockRequest.getQueryString()).thenReturn(null);

        // When
        val response = globalExceptionHandler.handleConflict(exception, mockRequest);

        // Then
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Conflict exception", response.getBody().getDetail());
        assertEquals("http://localhost/conflict", response.getBody().getType().toString());
    }

}