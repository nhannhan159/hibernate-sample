package com.nhannhan159.base.application;

import com.google.common.base.Throwables;
import lombok.Getter;

/**
 * @author tien.tan
 */
@Getter
public class AppException extends RuntimeException {
    private final ErrorCode error;

    public AppException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
    }

    public AppException(ErrorCode error, String message) {
        super(message);
        this.error = error;
    }

    public AppException(ErrorCode error, Throwable errorCause) {
        super(error.getMessage(), errorCause);
        this.error = error;
    }

    public AppException(ErrorCode error, String message, Throwable errorCause) {
        super(message, errorCause);
        this.error = error;
    }

    public AppException(Throwable errorCause) {
        super(errorCause.getMessage(), errorCause);
        if (errorCause instanceof AppException) {
            this.error = ((AppException) errorCause).getError();
        } else {
            this.error = ErrorCode.SYSTEM_ERROR;
        }
    }

    public ErrorDTO getErrorDTO() {
        return ErrorDTO.builder()
            .code(this.error.getCode())
            .message(this.getMessage())
            .stackTrace(Throwables.getStackTraceAsString(this))
            .build();
    }
}
