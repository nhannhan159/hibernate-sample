package com.nhannhan159.sample.domain.converter;

import com.nhannhan159.base.domain.converter.BaseConverter;
import com.nhannhan159.sample.application.dto.CityDTO;
import com.nhannhan159.sample.infrastructure.entity.CityDO;
import org.mapstruct.Mapper;

/**
 * @author tien.tan
 */
@Mapper(componentModel = "spring", uses = { CityWeatherDtoConverter.class })
public interface CityDtoConverter extends BaseConverter<CityDTO, CityDO> {
}
