package com.example.pawgather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "com.example.pawgather.repository.redis")
public class PawgatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(PawgatherApplication.class, args);
	}

}
