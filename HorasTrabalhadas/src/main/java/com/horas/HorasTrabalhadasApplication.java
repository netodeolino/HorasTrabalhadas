package com.horas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HorasTrabalhadasApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorasTrabalhadasApplication.class, args);
	}
}
