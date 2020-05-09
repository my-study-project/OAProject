package com.js.mapper.broadcast;

import com.js.pojo.broadcast.BroadcastIime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BroadcastIimeMapper {
    int deleteBroadcastIime(@Param("uuid") String uuid);

    int addBroadcastIime(BroadcastIime broadcastIime);

    BroadcastIime getBroadcastIimeById(@Param("uuid") String uuid);

    int editBroadcastIime(BroadcastIime broadcastIime);

    List<BroadcastIime> getBroadcastIimeByMess(BroadcastIime broadcastIime);

}