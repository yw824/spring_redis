package com.leew.RedisExample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis")
@Slf4j
public class HelloWorldController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!!!";
    }

    @Autowired
    StringRedisTemplate redisTemplate;

    // Redis 설정 후
    // TODO: setFruit?name=banana
    @PostMapping("/setFruit")
    public String setFruit(@RequestBody Map<String, String> map) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String name = map.get("fruit");

        ops.set("fruit", name);

        log.info("Fruit: {}", name);

        return "SAVED-Fruit: " + name;
    }

    @GetMapping("/getFruit")
    public String getFruit() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String fruitName = ops.get("fruit");

        log.info("Get-Fruit: {}", fruitName);

        /* TODO : application.yml 파일에 redis port를 설정해 주어야 한다.
            설정 후 Gradle의 Tasks/application/bootRun을 통해 실행 테스트 수행
         */

        return fruitName;
    }
}
