package com.js.mapper.broadcast;

import com.js.pojo.broadcast.BroadcastMistake;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BroadcastMistakeMapper {
    int deleteBroadcastMistake(@Param("uuid") String uuid);

    int addBroadcastMistake(BroadcastMistake broadcastMistake);

    BroadcastMistake getBroadcastMistakeById(@Param("uuid") String uuid);

    int editBroadcastMistake(BroadcastMistake broadcastMistake);

    List<BroadcastMistake> getBroadcastMistakeByMess(BroadcastMistake broadcastMistake);

}