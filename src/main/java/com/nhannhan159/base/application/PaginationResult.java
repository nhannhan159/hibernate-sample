package com.nhannhan159.base.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author tien.tan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResult<T> {
    private List<T> data;
    private Long totalElements;
    private Integer totalPages;
}
