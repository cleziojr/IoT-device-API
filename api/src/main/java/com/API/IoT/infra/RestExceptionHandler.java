package com.API.IoT.infra;

import com.API.IoT.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<RestErrorMessage> handleNotFoundStatus(RuntimeException exception,
                                                                 HttpServletRequest request) {
        log.error("API ERROR - ", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestErrorMessage(request, HttpStatus.NOT_FOUND, exception.getMessage()));
    }
}