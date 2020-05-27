package com.js.pojo.program;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 节目相关实体类
 **/
@Data
@ToString
public class Program {
    /** 主键id **/
    private String uuid;

    /** 节目名称 **/
    private String name;

    /** 所属组别id **/
    private String groupId;

    /** 0：节目有效1：节目无效 **/
    private String isAlive;

    /** 0：需要评估 1：不需要评估 **/
    private String needEvaluate;

    /** 负责节目的人员，中间；隔开 **/
    private String principal;
}