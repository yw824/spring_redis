package com.leew.RedisExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisExampleApplication.class, args);
	}
}
// build/libs/RedisExample-0.0.1-SNAPSHOT.jar
// java -Dserver.port=8080 -jar RedisExample-0.0.1-SNAPSHOT.jar