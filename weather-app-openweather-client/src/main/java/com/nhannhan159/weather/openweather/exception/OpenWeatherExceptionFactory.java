package com.nhannhan159.weather.openweather.exception;

import com.nhannhan159.weather.common.base.AppException;
import com.nhannhan159.weather.common.base.ErrorCode;

/**
 * @author tien.tan
 */
public class OpenWeatherExceptionFactory {

    private OpenWeatherExceptionFactory() {}

    public static AppException badRequest() {
        return new AppException(ErrorCode.OPEN_WEATHER_BAD_REQUEST_ERROR);
    }

    public static AppException notFound() {
        return new AppException(ErrorCode.OPEN_WEATHER_NOT_FOUND_ERROR);
    }

    public static AppException generic() {
        return new AppException(ErrorCode.OPEN_WEATHER_GENERIC_ERROR);
    }
}
