package com.nhannhan159.sample.infrastructure.config;

import com.google.common.base.Throwables;
import com.nhannhan159.base.application.AppException;
import com.nhannhan159.base.application.ErrorCode;
import com.nhannhan159.base.application.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author tien.tan
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handleException(Throwable e) {
        log.error(Throwables.getStackTraceAsString(e));
        return buildResponseEntity(e);
    }

    private ResponseEntity<ErrorDTO> buildResponseEntity(Throwable e) {
        HttpStatus httpStatus;
        ErrorDTO errorDTO;
        if (e instanceof AppException) {
            httpStatus = HttpStatus.valueOf(((AppException) e).getError().getCode());
            errorDTO = ((AppException) e).getErrorDTO();
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            errorDTO = ErrorDTO.builder()
                .code(ErrorCode.SYSTEM_ERROR.getCode())
                .message(ErrorCode.SYSTEM_ERROR.getMessage())
                .build();
        }
        return new ResponseEntity<>(errorDTO, httpStatus);
    }
}
