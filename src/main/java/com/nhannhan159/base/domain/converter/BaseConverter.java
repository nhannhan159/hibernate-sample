package com.nhannhan159.base.domain.converter;

import org.mapstruct.IterableMapping;

import java.util.List;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

/**
 * @author tien.tan
 */

public interface BaseConverter<S, T>  {
    T convert(S source);
    S convertBack(T target);

    @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    List<T> convert(List<S> sourceList);

    @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    List<S> convertBack(List<T> targetList);

    default Boolean convert(Byte var) {
        return var > 0;
    }

    default Byte convert(Boolean var) {
        return (byte) (Boolean.TRUE.equals(var) ? 1 : 0);
    }
}
