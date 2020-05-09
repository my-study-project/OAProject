package com.js.mapper.broadcast;

import com.js.pojo.broadcast.BroadcastSignIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BroadcastSignInMapper {

    int addBroadcastSignIn(BroadcastSignIn broadcastSignIn);

    List<BroadcastSignIn> getBroadcastSignInByMess(BroadcastSignIn broadcastSignIn);

}