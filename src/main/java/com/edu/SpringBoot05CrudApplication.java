package com.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan(value = "com.edu.dao")
@SpringBootApplication
@EnableCaching
public class SpringBoot05CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot05CrudApplication.class, args);
	}
}
