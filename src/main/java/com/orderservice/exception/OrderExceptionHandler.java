package com.orderservice.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class OrderExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFoundException(
            OrderNotFoundException ex) {
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
        return buildResponseEntity(message);
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    protected ResponseEntity<Object> handleInsufficientOrderItemsException(InsufficientQuantityException e) {
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage()).build();
        return buildResponseEntity(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach((fieldError) -> {
            errorList.add(fieldError.getDefaultMessage() );
        });
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.BAD_REQUEST)
                .message(errorList.toString()).build();
        return buildResponseEntity(message);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignStatusException(FeignException e, HttpServletRequest request, HttpServletResponse response) {
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage()).build();
        return buildResponseEntity(message);
    }
}
