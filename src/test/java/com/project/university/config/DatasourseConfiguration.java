package com.project.university.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/** @author Eugene
 * A factory for connections to the physical data source that this DataSource object represents
 */
@Configuration
@PropertySource("classpath:h2.properties")
@ComponentScan("com.project.university.repository")
@Profile("dev")
public class DatasourseConfiguration {
	
	@Value("${h2.DRIVER}") private String DRIVER_H2;
	@Value("${h2.HOST}") private String HOST_H2;
    @Value("${h2.USERNAME}") private String USER_NAME_H2;
	@Value("${h2.PASSWORD}") private String PASSWORD_H2;
	
	/**
	 * Attempts to establish a connection with the data source
	 * @return dataSourse
	 */
	@Bean
    public DataSource dataSourceH2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_H2);
        dataSource.setUrl(HOST_H2);
        dataSource.setUsername(USER_NAME_H2);
        dataSource.setPassword(PASSWORD_H2);
        return dataSource;
    }
	
	/**
	 * Construct a new JdbcTemplate, given a DataSource to obtain connections from.
	 * @return jdbcTemplate
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
}

