package com.nhannhan159.sample.infrastructure.repository.jpa;

import com.nhannhan159.sample.infrastructure.entity.CityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tien.tan
 */
@Repository
public interface CityRepository extends JpaRepository<CityDO, String> {
}
