package com.test.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@MapperScan(basePackages= "com.test.project")
public class JoinProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoinProjectApplication.class, args);
	}
 
}
