package com.nhannhan159.weather.data.repository.jpa;

import com.nhannhan159.weather.data.entity.CityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tien.tan
 */
@Repository
public interface CityRepository extends JpaRepository<CityDO, String> {
}
