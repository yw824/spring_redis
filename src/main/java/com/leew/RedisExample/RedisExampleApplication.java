package com.leew.RedisExample;

import com.leew.RedisExample.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisExampleApplication implements CommandLineRunner {

	@Autowired
	private ChatService chatService;

	public static void main(String[] args) {
		SpringApplication.run(RedisExampleApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Application Started...");
		chatService.enterChatRoom("chat1");
	} // run을 하고 바로 main이 종료된다. ChatService의 enterChatRoom 함수로 지속 구현
}
// build/libs/RedisExample-0.0.1-SNAPSHOT.jar
// java -Dserver.port=8080 -jar RedisExample-0.0.1-SNAPSHOT.jar