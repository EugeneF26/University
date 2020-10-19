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

        private static final String HIBERNATE_DRIVER_CLASS = "spring.datasource.driver-class-name";
        private static final String HIBERNATE_URL = "spring.datasource.url";
        private static final String HIBERNATE_USERNAME = "spring.datasource.name";
        private static final String HIBERNATE_PASSWORD = "spring.datasource.password";
        private static final String HIBERNATE_DIALECT = "spring.jooq.sql-dialect";
        private static final String HIBERNATE_SHOW_SQL = "spring.jpa.show-sql";
        private static final String HIBERNATE_DDL = "spring.jpa.hibernate.ddl-auto";

        @Resource
        private Environment env;

        private SessionFactory sessionFactory;

        public SessionFactory getConfiguration() {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", env.getRequiredProperty(HIBERNATE_DRIVER_CLASS));
            configuration.setProperty("hibernate.connection.url", env.getRequiredProperty(HIBERNATE_URL));
            configuration.setProperty("hibernate.connection.username", env.getRequiredProperty(HIBERNATE_USERNAME));
            configuration.setProperty("hibernate.connection.password", env.getRequiredProperty(HIBERNATE_PASSWORD));
            configuration.setProperty("hibernate.dialect", env.getRequiredProperty(HIBERNATE_DIALECT));
            configuration.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty(HIBERNATE_DDL));
            configuration.setProperty("hibernate.show_sql", env.getRequiredProperty(HIBERNATE_SHOW_SQL));

            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Professor.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Group.class);
            configuration.addAnnotatedClass(Lecture.class);

            sessionFactory = configuration.buildSessionFactory();

            return sessionFactory;
        }
    }

