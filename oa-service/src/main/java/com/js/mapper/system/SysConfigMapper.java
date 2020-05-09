package com.js.mapper.system;


import com.js.pojo.system.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysConfigMapper {
    int deleteSysConfig(@Param("uuid") String uuid);

    int addSysConfig(SysConfig sysConfig);

    SysConfig getSysConfigById(@Param("uuid") String uuid);

    List<SysConfig> getSysConfigByMess(SysConfig sysConfig);

    int editSysConfig(SysConfig sysConfig);

}