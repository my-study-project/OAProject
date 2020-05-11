package com.js.mapper.broadcast;

import com.js.pojo.broadcast.BroadcastMistake;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目错误相关操作Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface BroadcastMistakeMapper {
    /**删除操作**/
    int deleteBroadcastMistake(@Param("uuid") String uuid);

    /**添加操作**/
    int addBroadcastMistake(BroadcastMistake broadcastMistake);

    /**根据主键查询**/
    BroadcastMistake getBroadcastMistakeById(@Param("uuid") String uuid);

    /**修改操作**/
    int editBroadcastMistake(BroadcastMistake broadcastMistake);

    /**根据条件查询操作**/
    List<BroadcastMistake> getBroadcastMistakeByMess(BroadcastMistake broadcastMistake);

}