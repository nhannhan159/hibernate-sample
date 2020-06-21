package com.nhannhan159.weather.openweather.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhannhan159.weather.common.base.AppException;
import com.nhannhan159.weather.common.base.ErrorCode;
import com.nhannhan159.weather.common.util.ReactiveWrapper;
import com.nhannhan159.weather.common.util.ResourceUtils;
import com.nhannhan159.weather.openweather.model.City;
import com.nhannhan159.weather.openweather.model.CityWeather;
import feign.Response;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tien.tan
 */
public interface OpenWeatherBulkApiClient {

    @GetMapping("/city.list.json.gz")
    @Retry(name = "open-weather")
    String bulkCities();

    @GetMapping("/weather_14.json.gz")
    @Retry(name = "open-weather")
    Response bulkCityWeathers();

    @Retry(name = "open-weather")
    default Flux<City> fetchBulkCities() {
        var cityListType = new TypeToken<ArrayList<City>>(){}.getType();
        return ReactiveWrapper.toFlux(() -> {
            var rawStr = bulkCities();
            List<City> response;
            try {
                response = new Gson().fromJson(rawStr, cityListType);
            } catch (Exception e) {
                throw new AppException(ErrorCode.SYSTEM_ERROR, "error while parse json", e);
            }
            return response;
        });
    }

    @Retry(name = "open-weather")
    default Flux<CityWeather> fetchBulkWeathers() {
        try (var inputStream = bulkCityWeathers().body().asInputStream()) {
            return ReactiveWrapper.toFlux(ResourceUtils.readLinesFromGzipStream(inputStream))
                .map(line -> {
                    CityWeather response;
                    try {
                        response = new Gson().fromJson(line, CityWeather.class);
                    } catch (Exception e) {
                        throw new AppException(ErrorCode.SYSTEM_ERROR, "error while parse json", e);
                    }
                    return response;
                });
        } catch (IOException e) {
            throw new AppException(ErrorCode.SYSTEM_ERROR, "error while parse json", e);
        }
    }
}
