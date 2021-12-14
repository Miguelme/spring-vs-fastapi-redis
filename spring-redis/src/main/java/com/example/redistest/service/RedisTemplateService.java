package com.example.redistest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RedisTemplateService implements RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String TEMPLATE_PREFIX = "template:";

    public Set<String> getKeys() {
        return redisTemplate.keys(TEMPLATE_PREFIX + "*");
    }

    public List<String> getKeys(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public boolean saveKey(String key, String value) {
        redisTemplate.opsForValue().set(TEMPLATE_PREFIX + key, value);
        return true;
    }

    public String getKey(String key) {
        return redisTemplate.opsForValue().get(TEMPLATE_PREFIX + key);
    }

    public boolean saveKeys(Map<String, String> generatedKeyValues) {
        redisTemplate.opsForValue().multiSet(generatedKeyValues);
        return true;
    }
}
