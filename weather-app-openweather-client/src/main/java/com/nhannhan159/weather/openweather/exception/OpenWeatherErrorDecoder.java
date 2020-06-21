package com.nhannhan159.weather.openweather.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author tien.tan
 */
public class OpenWeatherErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> OpenWeatherExceptionFactory.badRequest();
            case 404 -> OpenWeatherExceptionFactory.notFound();
            default -> OpenWeatherExceptionFactory.generic();
        };
    }
}
