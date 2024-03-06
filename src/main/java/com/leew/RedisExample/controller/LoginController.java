package com.leew.RedisExample.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    HashMap<String, String> sessionMap = new HashMap<>();

    // TODO : login?name=Jay
    @PostMapping("/post")
    public String login(HttpSession session, @RequestBody Map<String, String> map) {
        /* application/json
        {
            "name": "Jay"
        }
         */

        // 이름 되게 저장
        String name = map.get("name");
        sessionMap.put(session.getId(), name);


        return "Login Success!!";
    }

    // TODO : myName => "Jay"
    @GetMapping("/get")
    public String getMyName(HttpSession session) {
        // 그 세션에 저장된 이름을 불러온다.
        String myName = sessionMap.get(session.getId());

        return "Your Name is: " + myName;
    }


}
