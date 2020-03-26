package com.nhannhan159.weather.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author tien.tan
 */
@Data
@Entity
@Table(name = "weather")
public class WeatherDO implements Serializable {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CityWeatherDO cityWeather;

    private String main;
    private String description;
    private String icon;
}
