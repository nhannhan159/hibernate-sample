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
public class CityDTO extends BaseDTO {
    private String name;
    private List<CityWeatherDTO> cityWeathers;
}
