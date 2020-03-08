package com.nhannhan159.sample.domain.converter;

import com.nhannhan159.base.domain.converter.BaseConverter;
import com.nhannhan159.sample.application.dto.*;
import com.nhannhan159.sample.infrastructure.entity.CityWeatherDO;
import com.nhannhan159.sample.infrastructure.entity.WeatherDO;
import com.nhannhan159.sample.infrastructure.entity.embedded.*;
import org.mapstruct.Mapper;

/**
 * @author tien.tan
 */
@Mapper(componentModel = "spring")
public interface CityWeatherDtoConverter extends BaseConverter<CityWeatherDTO, CityWeatherDO> {

    CloudsDO convert(CloudsDTO dto);
    CoordDO convert(CoordDTO dto);
    MainDO convert(MainDTO dto);
    SysDO convert(SysDTO dto);
    WeatherDO convert(WeatherDTO dto);
    WindDO convert(WindDTO dto);

    CloudsDTO convert(CloudsDO entity);
    CoordDTO convert(CoordDO entity);
    MainDTO convert(MainDO entity);
    SysDTO convert(SysDO entity);
    WeatherDTO convert(WeatherDO entity);
    WindDTO convert(WindDO entity);
}
