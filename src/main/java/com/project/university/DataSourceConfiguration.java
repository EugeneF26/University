package com.project.university;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource("classpath:application.properties")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories 
@ComponentScan
//@Profile({"prod","default"})
class DataSourceConfiguration {
	
	private static final String DRIVER_POSTGRES = "spring.datasource.driver-class-name";
    private static final String PASSWORD_H2_POSTGRES = "spring.datasource.password";
    private static final String HOST_POSTGRES = "spring.datasource.url";
    private static final String USER_NAME_POSTGRES = "spring.datasource.name";
    
    private static final String PROP_HIBERNATE_DIALECT = "spring.jooq.sql-dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "spring.jpa.show-sql";
    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "spring.jpa.mapping-resources";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
	
	@Resource
    private Environment env;
	
	/**
	 * Attempts to establish a connection with the data source
	 * @return dataSourse
	 */
	@Bean
//	@Profile({"prod, default"})
    public DataSource dataSourcePostgres() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(DRIVER_POSTGRES));
        dataSource.setUrl(env.getRequiredProperty(HOST_POSTGRES));
        dataSource.setUsername(env.getRequiredProperty(USER_NAME_POSTGRES));
        dataSource.setPassword(env.getRequiredProperty(PASSWORD_H2_POSTGRES));
        return dataSource;
    }
	
	  @Bean public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		  JdbcTemplate
	  jdbcTemplate = new JdbcTemplate(dataSource); return jdbcTemplate; 
	  }
	  
	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	        entityManagerFactoryBean.setDataSource(dataSourcePostgres());
	        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
	        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));

	        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

	        return entityManagerFactoryBean;
	    }
	  
	  @Bean
	    public JpaTransactionManager transactionManager() {
	        JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

	        return transactionManager;
	    }

	private Properties getHibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("spring.jooq.sql-dialect","org.hibernate.dialect.PostgreSQLDialect");
	        properties.put("spring.jpa.show-sql", "true");
	        properties.put("spring.jpa.hibernate.ddl-auto", "create-drop");

	        return properties;
	    }
}

