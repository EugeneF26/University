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

@Configuration
@PropertySource("classpath:postgres.properties")
@ComponentScan("com.project.university.repository")
@Profile({"prod","default"})
public class DatasourseConfigurationMain {
	
	@Value("${postgres.DRIVER}") private String DRIVER_POSTGRES;
	@Value("${postgres.HOST}") private String HOST_POSTGRES;
    @Value("${postgres.USERNAME}") private String USER_NAME_POSTGRES;
	@Value("${postgres.PASSWORD}") private String PASSWORD_H2_POSTGRES;
	
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
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
}

