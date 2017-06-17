package com.oddle.app.repository;

import com.oddle.app.model.City;
import com.oddle.app.model.CityWeather;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityWeatherRepositoryImpl extends AbstractRepositoryImpl<CityWeather, Integer> implements CityWeatherRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<CityWeather> getByCityName(String cityName) throws Exception {
        Session session = this.getCurrentSession();
        return session.createCriteria(this.repositoryClazz)
                .add(Restrictions.eq("name", cityName))
                .list();
    }

}
