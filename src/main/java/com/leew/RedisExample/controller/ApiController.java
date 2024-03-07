package com.leew.RedisExample.controller;

import com.leew.RedisExample.dto.UserProfile;
import com.leew.RedisExample.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userId}/profile")
    public UserProfile get(@PathVariable(value="userId") String userId) {
        log.info("Requested UserId: {}", userId);

        return userService.getUserProfile(userId);
    }
}
