package com.nhannhan159.sample.application.dto;

import com.nhannhan159.base.application.BaseDTO;
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
public class MainDTO extends BaseDTO {
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Double tempMin;
    private Double tempMax;
}
