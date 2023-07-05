package com.bfc.appmgmt.exception;

import com.bfc.appmgmt.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

/**
 * packageName    : com.bfc.appmgmt.exception
 * fileName       : GlobalExceptionController
 * author         : joyousang
 * date           : 2023/07/01
 * description    :
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity exceptionHandler(DuplicateKeyException e) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity exceptionHandler(EntityNotFoundException e) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity exceptionHandler(PasswordNotMatchException e) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exceptionHandler(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getFieldErrors().stream().findFirst().get();
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .message(fieldError.getDefaultMessage()).build());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity exceptionHandler(UnauthorizedException e) {
        return new ResponseEntity(ErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(ErrorResponse.builder().message(e.getMessage()));
    }
}
