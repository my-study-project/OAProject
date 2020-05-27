package com.js.mapper.system;

import com.js.pojo.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 用户表相关Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface SysUserMapper {

    /** 添加操作 **/
    int addSysUser(SysUser sysUser);

    /** 根据学号查询 **/
    SysUser getUserById(@Param("studentNumber") String studentNumber);

    /** 条件查询用户 **/
    List<SysUser> getUserAllList(SysUser sysUser);

    /** 修改用户 **/
    int editSysUser(SysUser sysUser);

}