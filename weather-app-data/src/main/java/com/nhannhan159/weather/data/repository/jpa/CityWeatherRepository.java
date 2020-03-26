package com.nhannhan159.weather.data.repository.jpa;

import com.nhannhan159.weather.data.entity.CityWeatherDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tien.tan
 */
@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeatherDO, Integer> {

    @Nullable
    CityWeatherDO findByNameIsLikeAndDs(String cityName, String ds);

    List<CityWeatherDO> findByNameIsLikeOrderByDsDesc(String cityName);
}
