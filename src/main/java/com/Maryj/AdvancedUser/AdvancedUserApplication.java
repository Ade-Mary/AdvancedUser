package com.Maryj.AdvancedUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AdvancedUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedUserApplication.class, args);
	}

}
