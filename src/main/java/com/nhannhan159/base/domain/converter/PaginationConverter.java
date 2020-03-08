package com.nhannhan159.base.domain.converter;

import com.nhannhan159.base.application.PaginationResult;
import org.springframework.data.domain.Page;

import java.util.Objects;

/**
 * @author tien.tan
 */
public interface PaginationConverter<S, T> extends BaseConverter<S, T> {

    default PaginationResult<T> convert(Page<S> sourcePage) {
        if (Objects.isNull(sourcePage)) {
            return null;
        }
        var dtoPage = new PaginationResult<T>();
        dtoPage.setData(this.convert(sourcePage.getContent()));
        dtoPage.setTotalElements(sourcePage.getTotalElements());
        dtoPage.setTotalPages(sourcePage.getTotalPages());
        return dtoPage;
    }
}
