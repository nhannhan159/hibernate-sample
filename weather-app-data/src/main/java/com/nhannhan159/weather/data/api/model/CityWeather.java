package com.nhannhan159.weather.data.api.model;

import lombok.Data;

import java.util.List;

/**
 * @author tien.tan
 */
@Data
public class CityWeather {
    private Integer id;
    private String name;
    private String base;
    private Integer dt;
    private Integer cod;
    private List<Weather> weather;
    private Coord coord;
    private Sys sys;
    private Main main;
    private Wind wind;
    private Clouds clouds;
}
