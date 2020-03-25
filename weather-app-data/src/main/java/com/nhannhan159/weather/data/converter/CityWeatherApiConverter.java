package com.nhannhan159.weather.data.converter;

import com.nhannhan159.weather.common.base.BaseConverter;
import com.nhannhan159.weather.data.api.model.*;
import com.nhannhan159.weather.data.entity.CityWeatherDO;
import com.nhannhan159.weather.data.entity.WeatherDO;
import com.nhannhan159.weather.data.entity.embedded.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tien.tan
 */
@Mapper(componentModel = "spring")
public interface CityWeatherApiConverter extends BaseConverter<CityWeather, CityWeatherDO> {

    CloudsDO convert(Clouds dto);
    CoordDO convert(Coord dto);
    MainDO convert(Main dto);
    SysDO convert(Sys dto);
    WeatherDO convert(Weather dto);
    WindDO convert(Wind dto);

    Clouds convert(CloudsDO entity);
    Coord convert(CoordDO entity);
    Main convert(MainDO entity);
    Sys convert(SysDO entity);
    Weather convert(WeatherDO entity);
    Wind convert(WindDO entity);

    @Override
    @Mapping(target = "ds", expression = "java(getDs())")
    CityWeatherDO convert(CityWeather dto);

    default String getDs() {
        return new SimpleDateFormat("yyyyMMDD").format(new Date());
    }
}
