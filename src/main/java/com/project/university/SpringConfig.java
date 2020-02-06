package com.project.university;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/** @author Eugene
 * A factory for connections to the physical data source that this DataSource object represents
 */
@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("com.project.university.dao")
public class SpringConfig {
	
	/**
	 * Attempts to establish a connection with the data source
	 * @return dataSourse
	 */
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("${db.DRIVER]");
        dataSource.setUrl("${db.HOST}");
        dataSource.setUsername("${db.USERNAME}");
        dataSource.setPassword("${db.PASSWORD}");
        return dataSource;
    }
}

