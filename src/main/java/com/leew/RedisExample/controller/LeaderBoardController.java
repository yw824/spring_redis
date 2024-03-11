package com.leew.RedisExample.controller;

import com.leew.RedisExample.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class LeaderBoardController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/test")
    public String test() {
        System.out.println("test");
        return "test";
    }

    // 웹 프라우저에서의 테스트 편리함을 위해 모두 GetMapping으로 수행
    @GetMapping("/setScore")
    public Boolean setScore(
            @RequestParam("userId") String userId,
            @RequestParam("score") int score
    ) {
        return rankingService.setUserScore(userId, score);
    }

    @GetMapping("/getRank")
    public Long getUserRank(
            @RequestParam("userId") String userId
    ) {
        return rankingService.getUserRanking(userId);
    }

    @GetMapping("/getTopRanks")
    public List<String> getTopRanks(
            @RequestParam(value = "limit", defaultValue="3") int limit
    ) {
        return rankingService.getTopRank(limit);
    }

}
