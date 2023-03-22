package com.sudip.store.electronicstore.exception;

import com.sudip.store.electronicstore.payload.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseMessage> handleBadRequestException(BadRequestException exception) {
        String message = exception.getMessage();
        ResponseMessage responseMessage = ResponseMessage.builder()
                .message(message)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .success(false)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
    }
}
