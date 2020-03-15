package com.nhannhan159.sample.infrastructure.repository;

import com.nhannhan159.sample.infrastructure.entity.CityWeatherDO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tien.tan
 */
@Repository
public interface CityWeatherRepository extends ReactiveCrudRepository<CityWeatherDO, Integer> {

    @Query("SELECT * FROM city_weather WHERE city_name LIKE :cityName AND ds = :ds")
    Mono<CityWeatherDO> findByCityNameIsLikeAndDs(String cityName, String ds);

    @Query("SELECT * FROM city_weather WHERE city_name LIKE :cityName ORDER BY ds DESC")
    Flux<CityWeatherDO> findByCityNameIsLikeOrderByDsDesc(String cityName);
}
