package com.oddle.app.repository;

import com.oddle.app.model.CityWeather;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CityWeather repository implement
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Repository
public class CityWeatherRepositoryImpl extends AbstractRepositoryImpl<CityWeather, Integer> implements CityWeatherRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<CityWeather> getByCityName(String cityName) throws Exception {
		List<CityWeather> cityWeathers;
		Session session = this.sessionFactory.openSession();
		try {
			cityWeathers = session.createCriteria(this.repositoryClazz)
					.add(Restrictions.eq("city.name", cityName))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.list();
		} catch (Exception e) {
			logger.error("Get error: ", e);
			throw e;
		} finally {
			session.close();
		}
		return cityWeathers;
	}

}
