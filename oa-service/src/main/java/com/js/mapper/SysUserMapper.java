package com.js.mapper;

import com.js.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    int addSysUser(SysUser sysUser);

    SysUser getUserById(@Param("studentNumber") String studentNumber);

    List<SysUser> getUserAllList(SysUser sysUser);

    int editSysUser(SysUser sysUser);

}