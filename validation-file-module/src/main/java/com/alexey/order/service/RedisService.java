package com.alexey.order.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {
    StringRedisTemplate template;

    public RedisService(StringRedisTemplate stringRedisTemplate){
        this.template = stringRedisTemplate;
    }

    public void save(String key, String value, Duration ttl) {
        template.opsForValue().set(key, value, ttl);
    }

    public String get(String key) {
       return template.opsForValue().get(key);
    }

    public void delete(String key) {
        template.opsForValue().getAndDelete(key);
    }
}
