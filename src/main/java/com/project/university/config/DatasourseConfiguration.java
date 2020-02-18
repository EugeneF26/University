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
@PropertySource({"classpath:h2.properties", "classpath:postgres.properties"})
@ComponentScan("com.project.repository")
public class DatasourseConfiguration {
	
	@Value("${h2.DRIVER}") private String DRIVER_H2;
	@Value("${h2.HOST}") private String HOST_H2;
    @Value("${h2.USERNAME}") private String USER_NAME_H2;
	@Value("${h2.PASSWORD}") private String PASSWORD_H2;
	
	@Value("${postgres.DRIVER}") private String DRIVER_POSTGRES;
	@Value("${postgres.HOST}") private String HOST_POSTGRES;
    @Value("${postgres.USERNAME}") private String USER_NAME_POSTGRES;
	@Value("${postgres.PASSWORD}") private String PASSWORD_H2_POSTGRES;
	
	/**
	 * Attempts to establish a connection with the data source
	 * @return dataSourse
	 */
	@Bean
	@Profile("dev")
    public DataSource dataSourceH2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_H2);
        dataSource.setUrl(HOST_H2);
        dataSource.setUsername(USER_NAME_H2);
        dataSource.setPassword(PASSWORD_H2);
        return dataSource;
    }
	
	/**
	 * Attempts to establish a connection with the data source
	 * @return dataSourse
	 */
	@Bean
	@Profile({"prod","default"})
    public DataSource dataSourcePostgres() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_POSTGRES);
        dataSource.setUrl(HOST_POSTGRES);
        dataSource.setUsername(USER_NAME_POSTGRES);
        dataSource.setPassword(PASSWORD_H2_POSTGRES);
        return dataSource;
    }
	
	/**
	 * Construct a new JdbcTemplate, given a DataSource to obtain connections from.
	 * @return jdbcTemplate
	 */
	@Bean
	@Profile({"prod","dev"})
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
}

