package com.leew.RedisExample;


import com.leew.RedisExample.service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 랭킹 서비스 성능  테스트
@SpringBootTest
public class RankingTest {
    @Autowired
    private RankingService rankingService;

    @Test
    void contextLoads() {
    }

    @Test
    void getRanks() {
        // TODO : insetScore로 Redis에 설정한 값을 가져오는 동작의 성능 테스트
        Instant before = Instant.now();

        Long userRank = rankingService.getUserRanking("user_100");
        Duration elapsed = Duration.between(before, Instant.now());

        System.out.println(String.format("Rank(%d) - Took %d ms",
                userRank, elapsed.getNano() / 1_000_000));
        // 252ms

        // 2번 operation
        before = Instant.now();
        List<String> topRankers = rankingService.getTopRank(10);
        elapsed = Duration.between(before, Instant.now());

        System.out.println(String.format("Range - Took %d ms",
                elapsed.getNano() / 1_000_000)); // 3ms
        for(String rank: topRankers) {
            System.out.println(rank);
        }
    }

    @Test
    void insertScore() {
        for(int i = 0; i < 1_000_000; ++i) {
            int score = (int)(Math.random() * 1_000_000);
            String userId = "user_" + i;

            System.out.println(userId + ": " + score);
        }
    }

    @Test
    public void inMemorySortPerformance() {
    /**
        단순한 ArrayList에다가 값을 추가하고 정렬했을 때
        얼마나 걸리는 지 알아보고자 한다. (SortedSet 사용)
        100만 개 넣을 예정
     */
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < 1_000_000; ++i) {
            int score = (int)(Math.random() * 1000000); // 0~999999
            list.add(score);
        }

        // TODO : 시간을 기록하여 측정하려고 한다.
        // 실행하기 전 시간 측정
        Instant before = Instant.now();

        // 수행
        Collections.sort(list); // 자바 로직 중 가장 빠른 알고리즘

        // 수행 후 시간 체크 후 기간 구하기
        Duration elapsed = Duration.between(before, Instant.now());

        System.out.println(elapsed.getNano() / 1000000 + "ms");

    }

}
