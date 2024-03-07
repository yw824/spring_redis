package com.leew.RedisExample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
public class UserProfile {
    @JsonProperty
    private String name;
    @JsonProperty
    private int age;
}
