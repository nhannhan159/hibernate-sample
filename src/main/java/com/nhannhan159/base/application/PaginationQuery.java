package com.nhannhan159.base.application;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author tien.tan
 */
@Getter
@Setter
public class PaginationQuery extends SortQuery {
    private Integer page;
    private Integer pageSize;

    public Integer getPage() {
        if (Objects.isNull(page)) {
            return 0;
        }
        return page;
    }

    public Integer getPageSize() {
        if (Objects.isNull(pageSize)) {
            return 10;
        }
        return pageSize;
    }
}
