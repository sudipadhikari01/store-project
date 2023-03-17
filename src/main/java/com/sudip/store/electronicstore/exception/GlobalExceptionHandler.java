package com.sudip.store.electronicstore.exception;

import com.sudip.store.electronicstore.payload.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //    exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        int a = 5;
        logger.info("resource handler invoked: {}",a);
        ResponseMessage responseMessage = ResponseMessage.builder().message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }
}
