package com.project.university;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
public class MySpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(MySpringApplication.class, args);
	}
}

