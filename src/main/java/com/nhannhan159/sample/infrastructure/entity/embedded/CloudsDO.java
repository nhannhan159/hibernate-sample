package com.nhannhan159.sample.infrastructure.entity.embedded;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * @author tien.tan
 */
@Data
@Embeddable
public class CloudsDO {
    private Integer allc;
}