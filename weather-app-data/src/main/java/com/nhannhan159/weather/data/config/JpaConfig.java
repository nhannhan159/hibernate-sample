package com.nhannhan159.weather.data.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * @author tien.tan
 */
@Configuration
@EnableJpaRepositories("com.nhannhan159.weather.data.repository.jpa")
public class JpaConfig {

    @Bean
    @ConditionalOnMissingBean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaProperties properties,
                                                                JpaVendorAdapter jpaVendorAdapter,
                                                                DataSource dataSource) {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.nhannhan159.weather.data.entity");
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaPropertyMap(properties.getProperties());
        return factoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    DataSource jpaDataSource(DataSourceProperties properties) {
        return DataSourceBuilder.create()
            .driverClassName(properties.getDriverClassName())
            .url(properties.getUrl())
            .username(properties.getUsername())
            .password(properties.getPassword())
            .build();
    }
}
