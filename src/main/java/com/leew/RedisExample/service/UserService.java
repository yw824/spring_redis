package com.leew.RedisExample.service;

import com.leew.RedisExample.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ExternalApiService externalApiService;

    public UserProfile getUserProfile(String userId) {
        String userName = externalApiService.getUserName(userId);
        int userAge = externalApiService.getUserAge(userId);

        return new UserProfile(userName, userAge);
    }
}
