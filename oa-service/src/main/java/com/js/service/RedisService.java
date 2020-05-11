package com.js.service;

import com.js.vo.system.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: 姜爽
 * @Description: 相关redis的操作
 * @Date: 2020/5/11 11:29
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void login(SysUserVo sysUserVo) {
        redisTemplate.opsForValue().set(sysUserVo.getStudentNumber(), sysUserVo);
    }

    public Boolean logOut(String studentNumber) {
        return redisTemplate.delete(studentNumber);
    }
}
