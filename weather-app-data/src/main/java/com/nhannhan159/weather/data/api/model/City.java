package com.nhannhan159.weather.data.api.model;

import lombok.Data;

import java.util.List;

/**
 * @author tien.tan
 */
@Data
public class City {
    private String name;
    private List<CityWeather> cityWeathers;
}
