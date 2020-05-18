package com.js.service;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("查询用户学号{}",studentNumber);
        return redisTemplate.opsForValue().get(studentNumber).toString();
//        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50TnVtYmVyIjoiMjAxNjYwOTgiLCJuYW1lIjoi5aec54i9In0.Raqgr1LytpCw7yD7OTqqNUoiQf2jd-JHRRzZeEsaLSw";
    }
}
