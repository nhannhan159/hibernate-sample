package com.nhannhan159.weather.common.dto;

import com.nhannhan159.weather.common.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author tien.tan
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class WindDTO extends BaseDTO {
    private Double speed;
    private Double deg;
}
