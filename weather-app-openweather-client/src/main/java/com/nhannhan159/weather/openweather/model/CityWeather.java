package com.nhannhan159.weather.openweather.model;

import lombok.Data;

import java.util.List;

/**
 * @author tien.tan
 */
@Data
public class CityWeather {
    private Long id;
    private String name;
    private String base;
    private Long dt;
    private Integer cod;
    private List<Weather> weather;
    private Coord coord;
    private Sys sys;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private City city;
    private Long time;
}
