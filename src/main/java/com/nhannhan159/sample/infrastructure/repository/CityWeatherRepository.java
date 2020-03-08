package com.nhannhan159.sample.infrastructure.repository;

import com.nhannhan159.sample.infrastructure.entity.CityWeatherDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tien.tan
 */
@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeatherDO, Integer> {

    CityWeatherDO findByCityNameIsLikeAndDs(String cityName, String ds);

    List<CityWeatherDO> findByCityNameIsLikeOrderByDsDesc(String cityName);
}
