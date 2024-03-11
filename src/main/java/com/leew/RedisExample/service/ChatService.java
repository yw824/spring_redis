package com.leew.RedisExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;
import java.util.Scanner;

@Service
public class ChatService implements MessageListener {

    // 어떤 채널에서 수신할 지 설정하는 컨테이너
    @Autowired
    private RedisMessageListenerContainer container;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // redis subscriber를 통해 온 메세지를 확인 가능
        System.out.println("Message: " + message.toString());
    }

    // 사용자의 입력을 기다리면서, 채팅방에 전송받은 메세지를 출력
    public void enterChatRoom(String chatRoomName) {
        // 이 service가 listener가 되고, chatRoomName에 채널 토픽이 설정됨 -> 프로듀서가 여기로 전송됨
        container.addMessageListener(this, new ChannelTopic(chatRoomName));

        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String line = in.nextLine();

            // 종료 조건 : "q"를 입력하면 종료한다.
            if(line.equals("q")) {
                System.out.println("Quit....");
                break;
            }
        }
    }
}
