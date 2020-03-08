package com.nhannhan159.base.application;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tien.tan
 */
@Getter
@Setter
public class SortQuery {
    private String orderBy;
    private Direction direction;

    enum Direction {
        ASC, DESC
    }
}
