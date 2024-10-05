package com.ecommerce_backend.ecommerce.common.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecommerce_backend.ecommerce.common.exceptions.AlreadyPresentException;
import com.ecommerce_backend.ecommerce.common.exceptions.InvalidaInputException;
import com.ecommerce_backend.ecommerce.common.exceptions.NotPresentException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ InvalidaInputException.class, NotPresentException.class,
            AlreadyPresentException.class })
    public ResponseEntity<CommonResponse<String>> exceptionHandler(Exception e) {

        CommonResponse<String> errorResponse = new CommonResponse<>(e.getMessage(),
                HttpStatus.BAD_REQUEST.value(), null);

        // Return a ResponseEntity with CustomErrorResponse as a single object
        return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<CommonResponse<String>> systemExceptionHandler(Exception e) {

        CommonResponse<String> errorResponse = new CommonResponse<>(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

        // Return a ResponseEntity with CustomErrorResponse as a single object
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
