package com.bfc.appmgmt.exception;

import com.bfc.appmgmt.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity exceptionHandler(IllegalArgumentException e) {
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity exceptionHandler(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .message("잘못된 형식의 값입니다.").build());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity exceptionHandler(UnauthorizedException e) {
        return new ResponseEntity(ErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }
}