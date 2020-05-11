package com.js.vo.group;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 小组信息返回类
 **/
@Data
@ToString
public class GroupVo {

    @ApiModelProperty("主键")
    private String uuid;

    @ApiModelProperty("组名")
    private String deptName;

    @ApiModelProperty("错误总分")
    private Integer mistakeScore;

    @ApiModelProperty("节目总分")
    private Integer programScore;

    @ApiModelProperty("负责人")
    private String leaderUserId;

    @ApiModelProperty("0：有节目 1：无节目")
    private String hasProgram;

    @ApiModelProperty("0：正在运营 1：已停用")
    private String isAlive;
}