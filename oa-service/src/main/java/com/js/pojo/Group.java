package com.js.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 小组相关实体类
 **/
@Data
@ToString
public class Group {
    /**主键**/
    private String uuid;

    /**组名**/
    private String deptName;

    /**错误总分**/
    private Integer mistakeScore;

    /**节目总分**/
    private Integer programScore;

    /**负责人**/
    private String leaderUserId;

    /**0：有节目 1：无节目**/
    private String hasProgram;

    /**0：正在运营 1：已停用**/
    private String isAlive;
}