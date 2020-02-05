package com.project.university;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("com.project.university.dao")
public class SpringConfig {
	
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
