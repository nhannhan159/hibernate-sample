package com.nhannhan159.weather.web.config;

import com.google.common.base.Throwables;
import com.nhannhan159.weather.common.base.AppException;
import com.nhannhan159.weather.common.base.ErrorCode;
import com.nhannhan159.weather.common.base.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author tien.tan
 */
@Slf4j
@RestControllerAdvice
public class WebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public WebExceptionHandler(ErrorAttributes errorAttributes,
                               ResourceProperties resourceProperties,
                               ApplicationContext applicationContext,
                               ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, resourceProperties, applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * error handler for legacy rest controller
     */
    @ExceptionHandler(Throwable.class)
    ResponseEntity<ErrorDTO> handleException(Throwable e) {
        log.error(Throwables.getStackTraceAsString(e));
        return buildResponseEntity(e);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        var error = this.getError(request);
        var errorResponse = this.buildResponseEntity(error);
        return ServerResponse.status(errorResponse.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(Objects.requireNonNull(errorResponse.getBody())));
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
                .message(e.getMessage())
                .stackTrace(Throwables.getStackTraceAsString(e))
                .build();
        }
        return new ResponseEntity<>(errorDTO, httpStatus);
    }
}
