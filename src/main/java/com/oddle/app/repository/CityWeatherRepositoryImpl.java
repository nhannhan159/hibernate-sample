package com.oddle.app.repository;

import com.oddle.app.model.City;
import com.oddle.app.model.CityWeather;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CityWeatherRepositoryImpl extends AbstractRepositoryImpl<CityWeather, Integer> implements CityWeatherRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<CityWeather> getByCityName(String cityName) throws Exception {
		List<CityWeather> cityWeathers;
		Session session = this.openSession();
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
