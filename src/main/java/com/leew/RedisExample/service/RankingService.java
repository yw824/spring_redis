package com.leew.RedisExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RankingService {

    // 공통 키
    private static final String LEADERBOARD_KEY = "leaderBoard";
    @Autowired
    StringRedisTemplate redisTemplate;

    public boolean setUserScore(String userId, int score) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(LEADERBOARD_KEY, userId, score);

        return true;
    }

    public Long getUserRanking(String userId) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Long rank = zSetOps.reverseRank(LEADERBOARD_KEY, userId);

        System.out.println(userId + "'s Rank: " + rank);
        return rank;
    }

    // 상위 범위의 팀 이름을 구하는 것
    public List<String> getTopRank(int limit) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        // 반환값이 Set으로 오기 떄문에 일단 먼저 set으로 받는다.
        // 정렬 기준은 1부터이므로 오름차순(reverse)로 받는다 - 정방향은 내림차순
        Set<String> rangeSet = zSetOps.reverseRange(LEADERBOARD_KEY, 0, limit-1);
        // 0부터 limit-1까지(포함)

        return new ArrayList<>(rangeSet);
    }
}
