package com.js.mapper.broadcast;

import com.js.pojo.broadcast.BroadcastTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目对应的播出时间详细信息Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface BroadcastTimeMapper {
    /** 删除操作 **/
    int deleteBroadcastIime(@Param("uuid") String uuid);

    /** 添加操作 **/
    int addBroadcastIime(BroadcastTime broadcastTime);

    /** 操作根据主键查询 **/
    BroadcastTime getBroadcastIimeById(@Param("uuid") String uuid);

    /** 修改操作 **/
    int editBroadcastIime(BroadcastTime broadcastTime);

    /** 根据条件查询操作 **/
    List<BroadcastTime> getBroadcastIimeByMess(BroadcastTime broadcastTime);

}