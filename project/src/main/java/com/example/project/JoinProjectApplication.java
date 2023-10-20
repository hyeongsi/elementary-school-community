package com.example.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages= "com.example.project")
public class JoinProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoinProjectApplication.class, args);
	}
 
}
