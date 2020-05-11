package com.js.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 姜爽
 * @Description: 相关redis的操作
 * @Date: 2020/5/11 11:29
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void login(String studentNumber,String token) {
        redisTemplate.opsForValue().set(studentNumber,token);
        redisTemplate.expire(studentNumber, 15, TimeUnit.MINUTES);
    }

    public Boolean logOut(String studentNumber) {
        return redisTemplate.delete(studentNumber);
    }

    public String getToken(String studentNumber) {
        return String.valueOf(redisTemplate.opsForValue().get(studentNumber));
    }
}
