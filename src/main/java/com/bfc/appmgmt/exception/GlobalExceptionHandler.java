package com.bfc.appmgmt.exception;

import com.bfc.appmgmt.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public ResponseEntity DuplicateKeyExceptionHandler(DuplicateKeyException e) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder().message(e.getMessage()).build());
    }
}
