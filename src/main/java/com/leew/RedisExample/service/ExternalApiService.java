package com.leew.RedisExample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExternalApiService {
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
