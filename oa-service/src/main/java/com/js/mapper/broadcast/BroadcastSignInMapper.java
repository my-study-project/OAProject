package com.js.mapper.broadcast;

import com.js.pojo.broadcast.BroadcastSignIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 签到相关操作Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface BroadcastSignInMapper {

    /** 添加签到操作 **/
    int addBroadcastSignIn(BroadcastSignIn broadcastSignIn);

    /** 根据条件查询查询操作 **/
    List<BroadcastSignIn> getBroadcastSignInByMess(BroadcastSignIn broadcastSignIn);

}