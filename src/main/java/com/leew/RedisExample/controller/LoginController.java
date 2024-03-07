package com.leew.RedisExample.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    // 직접 로컬 저장소로 세션 관련 쿠키 데이터를 저장
    // HashMap<String, String> sessionMap = new HashMap<>();

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
        // sessionMap.put(session.getId(), name);

        // 세션도 자체적으로 key-value 저장소를 가지고 있다.
        session.setAttribute("name", name);
        // 그러나 한 서버에만 공유된다. 서버끼리 연결하려면 Redis가 필요
        // 먼저 application.yml 파일로 가서 redis 판단하자.

        return "Session Data Saved.";
    }

    // TODO : myName => "Jay"
    @GetMapping("/get")
    public String getMyName(HttpSession session) {
        String myName;
        // 그 세션에 저장된 이름을 불러온다.
        // myName = sessionMap.get(session.getId());
        myName = (String) session.getAttribute("name");

        return "Your Name is: " + myName;
    }


}
