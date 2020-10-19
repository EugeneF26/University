package com.project.university;

import com.project.university.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

    @PropertySource("classpath:application.properties")
    @Component
    public class SessionManager {

//        @Resource
//        private Environment env;

        private SessionFactory sessionFactory;

        public SessionFactory getConfiguration() {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
            configuration.setProperty("hibernate.connection.username", "postgres");
            configuration.setProperty("hibernate.connection.password", "2610");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            configuration.setProperty("hibernate.show_sql", "true");

//            Configuration configuration = new Configuration();
//            configuration.setProperty("hibernate.connection.driver_class", env.getRequiredProperty("spring.datasource.driver-class-name"));
//            configuration.setProperty("hibernate.connection.url", env.getRequiredProperty("spring.datasource.url"));
//            configuration.setProperty("hibernate.connection.username", env.getRequiredProperty("spring.datasource.username"));
//            configuration.setProperty("hibernate.connection.password", env.getRequiredProperty("spring.datasource.password"));
//            configuration.setProperty("hibernate.dialect", env.getRequiredProperty("org.hibernate.dialect.PostgreSQLDialect"));
//            configuration.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
//            configuration.setProperty("hibernate.show_sql", env.getRequiredProperty("spring.jpa.show-sql"));

            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Professor.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Group.class);

            sessionFactory = configuration.buildSessionFactory();

            return sessionFactory;
        }
    }

