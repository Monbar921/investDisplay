package ru.price.gateway.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Save data to Redis
    public void save(String key, Object value, long timeoutInSeconds) {
        redisTemplate.opsForValue().set(key, value, timeoutInSeconds, TimeUnit.SECONDS);
    }

    // Retrieve data from Redis
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // Delete data from Redis
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
