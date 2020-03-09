package com.nhannhan159.sample.infrastructure.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Weather entity
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Data
@Entity
@Table(name = "weather")
public class WeatherDO implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_id", nullable = false)
    private CityWeatherDO cityWeather;

    private String main;
    private String description;
    private String icon;
}
