package com.nhannhan159.weather.common.dto;

import com.nhannhan159.weather.common.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author tien.tan
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class CityWeatherDTO extends BaseDTO {
    private Integer id;
    private String name;
    private String base;
    private String ds;
    private Integer dt;
    private Integer cod;
    private List<WeatherDTO> weather;
    private CoordDTO coord;
    private SysDTO sys;
    private MainDTO main;
    private WindDTO wind;
    private CloudsDTO clouds;
}
