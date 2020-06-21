package com.nhannhan159.weather.common.base;

import lombok.Getter;

/**
 * @author tien.tan
 */
public enum ErrorCode {
    /**
     * SYSTEM_ERROR
     */
    SYSTEM_ERROR("SYSTEM_ERROR", 500, "system error"),

    /**
     * SYSTEM_API_ERROR
     */
    SYSTEM_API_ERROR("SYSTEM_API_ERROR", 500,"system api unknown exception"),

    /**
     * PARAMETER_ERROR
     */
    PARAMETER_ERROR("PARAMETER_ERROR", 400, "Missing required parameters or parameters are illegal"),

    /**
     * DATA_STRUCTURE_ERROR
     */
    DATA_STRUCTURE_ERROR("DATA_STRUCTURE_ERROR", 400, "Data structure error,parsing JSON failed"),

    /**
     * NO_VALID_DATA_ERROR
     */
    NO_VALID_DATA_ERROR("NO_VALID_DATA_ERROR", 400, "unable to find valid data"),

    /**
     * API_OPERATION_FAILED
     */
    API_OPERATION_FAILED("API_OPERATION_FAILED", 400, "Interface execution failed"),

    /**
     * API_OPERATION_REPEAT_ERROR
     */
    API_OPERATION_REPEAT_ERROR("API_OPERATION_REPEAT_ERROR", 400, "Interface cannot be repeated"),

    /**
     * OPEN_WEATHER_BAD_REQUEST_ERROR
     */
    OPEN_WEATHER_BAD_REQUEST_ERROR("OPEN_WEATHER_BAD_REQUEST_ERROR", 400, "Open weather api bad request"),

    /**
     * OPEN_WEATHER_NOT_FOUND_ERROR
     */
    OPEN_WEATHER_NOT_FOUND_ERROR("OPEN_WEATHER_NOT_FOUND_ERROR", 400, "Open weather api not found"),

    /**
     * OPEN_WEATHER_GENERIC_ERROR
     */
    OPEN_WEATHER_GENERIC_ERROR("OPEN_WEATHER_GENERIC_ERROR", 400, "Open weather api generic error"),

    ;

    @Getter
    private final String code;

    @Getter
    private final int httpCode;

    @Getter
    private final String message;

    ErrorCode(String code, int httpCode, String message) {
        this.code = code;
        this.httpCode = httpCode;
        this.message = message;
    }
}
