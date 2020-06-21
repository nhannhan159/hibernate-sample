package com.nhannhan159.weather.data.converter;

import com.nhannhan159.weather.common.base.BaseConverter;
import com.nhannhan159.weather.data.entity.CityDO;
import com.nhannhan159.weather.openweather.model.City;
import org.mapstruct.Mapper;

/**
 * @author tien.tan
 */
@Mapper(componentModel = "spring")
public interface CityApiConverter extends BaseConverter<City, CityDO> {
}
