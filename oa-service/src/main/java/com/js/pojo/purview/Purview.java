package com.js.pojo.purview;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 用户对应权限实体类
 **/
@Data
@ToString
public class Purview {
    /** 主键uuid**/
    private String uuid;

    /** 用户名**/
    private String userId;

    /** 0：无权限1：有权限 **/
    private String manageSign;

    /** 提交错误0：无权限1：有权限**/
    private String submitBroadcastMistake;

    /** 错误管理0：无权限1：有权限 **/
    private String manageBroadcastMistake;

    /** 节目评估0：无权限1：有权限 **/
    private String evaluateProgram;

    /** 评估管理0：无权限1：有权限 **/
    private String manageProgramEvaluation;

    /** 系统管理0：无权限1：有权限 **/
    private String manageSystemConfig;
}