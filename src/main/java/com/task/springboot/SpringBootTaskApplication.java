package com.task.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.task.springboot.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.task.springboot"})
// @Configuration @EnableAutoConfiguration @ComponentScan can be used instead of @SpringBootApplication
public class SpringBootTaskApplication{
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTaskApplication.class, args);
	}
}
