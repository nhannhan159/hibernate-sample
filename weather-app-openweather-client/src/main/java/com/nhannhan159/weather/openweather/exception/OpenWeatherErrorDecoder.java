package com.nhannhan159.weather.openweather.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author tien.tan
 */
public class OpenWeatherErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
         switch (response.status()) {
             case 400: return OpenWeatherExceptionFactory.badRequest();
             case 404: return OpenWeatherExceptionFactory.notFound();
             default: return OpenWeatherExceptionFactory.generic();
        }
    }
}
