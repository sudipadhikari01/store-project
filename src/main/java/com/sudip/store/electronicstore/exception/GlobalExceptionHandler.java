package com.sudip.store.electronicstore.exception;

import com.sudip.store.electronicstore.payload.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //    exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        int a = 5;
        logger.info("resource handler invoked: {}", a);
        ResponseMessage responseMessage = ResponseMessage.builder().message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }

//    for api field validation --> MethodArgumentNotValidException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        Map<String, String> collect = fieldErrors.stream().collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));

        return new ResponseEntity(collect, HttpStatus.BAD_REQUEST);


    }
}
