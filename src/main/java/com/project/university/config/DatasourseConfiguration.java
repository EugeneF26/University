package com.project.university.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource("classpath:postgres.properties")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories 
@ComponentScan("com.project.university.repository")
//@Profile({"prod","default"})
public class DatasourseConfiguration {
	
	private static final String DRIVER_POSTGRES = "postgres.DRIVER";
    private static final String PASSWORD_H2_POSTGRES = "postgres.PASSWORD";
    private static final String HOST_POSTGRES = "postgres.HOST";
    private static final String USER_NAME_POSTGRES = "postgres.USERNAME";
    
    private static final String PROP_HIBERNATE_DIALECT = "db.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "db.show_sql";
    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.scan";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "db.auto";
	
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
	        properties.put(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
	        properties.put(PROP_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
	        properties.put(PROP_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));

	        return properties;
	    }
	  
}
