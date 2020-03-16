package com.nhannhan159.sample.infrastructure.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author tien.tan
 */
@Data
@Entity
@Table("weather")
public class WeatherDO implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_id", nullable = false)
    private CityWeatherDO cityWeather;

    private String main;
    private String description;
    private String icon;
}
