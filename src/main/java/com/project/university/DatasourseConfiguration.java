package com.project.university;

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
@ComponentScan("com.project.university.dao")
public class DatasourseConfiguration {
	
	@Value("${db.DRIVER}") private String DRIVER;
	@Value("${db.HOST}") private String HOST;
    @Value("${db.USERNAME}") private String USER_NAME;
	@Value("${db.PASSWORD}") private String PASSWORD;
	
	/**
	 * Attempts to establish a connection with the data source
	 * @return dataSourse
	 */
	@Bean
	@Profile("dev")
    public DataSource dataSourceH2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(HOST);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
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
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(HOST);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
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

