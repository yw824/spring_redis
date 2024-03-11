package com.leew.RedisExample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    // 메시징을 위해서, MessageListenerAdapter가 ㅣㄹ요
    // Listener : Subscriber의 구현체
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    // Listener 컨테이너 생성하는 bean을 생성
    // low 레벨에서 일어나는 메세지 구독을 처리하고 이에 대한 인터페이스를 컨테이너에 등록하여
    // 수신된 메세지 처리
    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());
        return container;
    }
}
