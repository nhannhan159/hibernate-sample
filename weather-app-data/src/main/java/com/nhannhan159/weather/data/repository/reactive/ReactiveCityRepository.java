package com.nhannhan159.weather.data.repository.reactive;

import com.nhannhan159.weather.data.entity.CityDO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tien.tan
 */
@Repository
public interface ReactiveCityRepository extends ReactiveCrudRepository<CityDO, String> {
}
