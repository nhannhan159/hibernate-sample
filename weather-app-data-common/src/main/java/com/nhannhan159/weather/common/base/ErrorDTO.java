package com.nhannhan159.weather.common.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author tien.tan
 */
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class ErrorDTO extends BaseDTO {
    private String code;
    private String message;
    private String stackTrace;
}
