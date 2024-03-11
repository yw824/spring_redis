package com.leew.RedisExample.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RankingServiceTest {

    @Test
    void contextLoads() {}

    @Autowired
    private RankingService rankingService;

    @Test
    void insertScore() {
        for(int i = 0; i < 1_000_000; ++i) {
            int score = (int)(Math.random() * 1_000_000);
            String userId = "user_" + i;

            Instant before = Instant.now();
            rankingService.setUserScore(userId, score);

            Duration elapsed = Duration.between(before, Instant.now());
        }
    }
}