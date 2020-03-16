package com.nhannhan159.sample.infrastructure.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;

/**
 * @author tien.tan
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Table("city")
@Entity
@javax.persistence.Table(name = "city")
public class CityDO extends AbstractAggregateRoot<CityDO> {

    @Id
    @javax.persistence.Id
    private String name;
}