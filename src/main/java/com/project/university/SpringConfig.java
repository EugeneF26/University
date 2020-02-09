package com.project.university;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

/** @author Eugene
 * A factory for connections to the physical data source that this DataSource object represents
 */
@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("com.project.university.dao")
public class SpringConfig {
	
	@Value("${db.DRIVER]") private String DRIVER;
	@Value("${db.HOST}") private String HOST;
    @Value("${db.USERNAME}") private String USER_NAME;
	@Value("${db.PASSWORD}") private String PASSWORD;
	
	/**
	 * Attempts to establish a connection with the data source
	 * @return jdbcTemplate
	 */
	@Bean
    public JdbcTemplate dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DRIVER);
        hikariDataSource.setJdbcUrl(HOST);
        hikariDataSource.setUsername(USER_NAME);
        hikariDataSource.setPassword(PASSWORD);
        return new JdbcTemplate(hikariDataSource);
    }
	
	@Bean
	public NamedParameterJdbcTemplate nameParamJdbcTemplate() {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
		return namedParameterJdbcTemplate;
	}
}

