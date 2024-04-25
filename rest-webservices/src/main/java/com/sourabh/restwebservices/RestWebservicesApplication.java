package com.sourabh.restwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@EnableCaching
@SpringBootApplication
public class RestWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWebservicesApplication.class, args);
	}

}
