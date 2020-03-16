package com.nhannhan159.sample.infrastructure.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * @author tien.tan
 */
@Configuration
@EnableJpaRepositories("com.nhannhan159.sample.infrastructure.repository.jpa")
public class JpaConfig {

    @Bean
    @ConditionalOnMissingBean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaProperties properties, DataSource dataSource) {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.nhannhan159.sample.infrastructure.entity");
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
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
