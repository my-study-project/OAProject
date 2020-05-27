package com.js.mapper.system;

import com.js.pojo.system.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 系统配置文件Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface SysConfigMapper {
    /** 删除操作 **/
    int deleteSysConfig(@Param("uuid") String uuid);

    /** 添加系统配置操作 **/
    int addSysConfig(SysConfig sysConfig);

    /** 根据主键查询操作 **/
    SysConfig getSysConfigById(@Param("uuid") String uuid);

    /** 根据条件查询操作 **/
    List<SysConfig> getSysConfigByMess(SysConfig sysConfig);

    /** 修改操作 **/
    int editSysConfig(SysConfig sysConfig);

}