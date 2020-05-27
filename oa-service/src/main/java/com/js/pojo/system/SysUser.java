package com.js.pojo.system;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 系统用户
 **/
@Data
@ToString
public class SysUser {
    /** 学号 **/
    private String studentNumber;

    /** 姓名 **/
    private String name;

    /** 学院 **/
    private String college;

    /** 邮箱 **/
    private String email;

    /** 密码 **/
    private String password;

    /** 联系方式 **/
    private String phoneNumber;

    /** 所属组别 **/
    private String groupId;

    /** 0：正常1：停用 **/
    private String isAlive;

    /** 0：不是小组组长 1：是小组组长 **/
    private String isSuper;

    /** 对应师傅id **/
    private String master;
}