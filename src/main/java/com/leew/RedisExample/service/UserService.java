package com.leew.RedisExample.service;

import com.leew.RedisExample.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    StringRedisTemplate redisTemplate;

    public UserProfile getUserProfile(String userId) {

        String userName = null;

        // 요청을 하기 전에, Redis를 먼저 구현
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String cachedName = ops.get("nameKey: " + userId);
        if(cachedName != null) {

        } else {
            userName = externalApiService.getUserName(userId);
            // 가져온 후에 캐시에 담는 과정이 필요함
            // Cache-Aside 과정 - 읽기 과정에서 캐시 탐색
            ops.set("nameKey: " + userId, userName, 5, TimeUnit.SECONDS);
        }

        // userAge는 그대로 구해오는 과정이기 때문에, 시간 timeout이 그대로 걸린다.
        int userAge = externalApiService.getUserAge(userId);

        return new UserProfile(userName, userAge);
    }
}
