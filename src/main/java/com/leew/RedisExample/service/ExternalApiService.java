package com.leew.RedisExample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExternalApiService {
    // RedisTemplate 객체를 사용해 직접 캐싱
    public String getUserName(String userId) {
        // 외부 서비스나 DB 호출
        // 좀 시간이 걸린다고 가정
        try {
            Thread.sleep(1000); // checkedException
        } catch (InterruptedException e) {

        }

        if(userId.equals("A")) {
            return "Adam";
        }
        if(userId.equals("B")) {
            return "Bob";
        }
        return "Error: None";
    }

    // 스프링의 캐싱 라이브러리를 이용해 yml에 설정한 대로 캐시를 redis에 저장
    @Cacheable(cacheNames = "userAgeCache", key="#userId") // key를 명시적으로 지정
    public int getUserAge(String userId) {
        // 외부 서비스나 DB 호출
        // 좀 시간이 걸린다고 가정
        try {
            Thread.sleep(1000); // checkedException
        } catch (InterruptedException e) {

        }

        if(userId.equals("A")) {
            return 10;
        }
        if(userId.equals("B")) {
            return 20;
        }
        return 0;
    }
}
