package com.nhannhan159.weather.data.converter;

import com.nhannhan159.weather.common.base.BaseConverter;
import com.nhannhan159.weather.common.dto.CityDTO;
import com.nhannhan159.weather.data.entity.CityDO;
import org.mapstruct.Mapper;

/**
 * @author tien.tan
 */
@Mapper(componentModel = "spring")
public interface CityDtoConverter extends BaseConverter<CityDTO, CityDO> {
}
