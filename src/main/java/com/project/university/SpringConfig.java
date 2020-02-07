package com.project.university;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
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
	 * @return jdbcTemplate
	 */
	@Bean
    public JdbcTemplate dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("${db.DRIVER]");
        dataSource.setUrl("${db.HOST}");
        dataSource.setUsername("${db.USERNAME}");
        dataSource.setPassword("${db.PASSWORD}");
        return new JdbcTemplate(dataSource);
    }
}

