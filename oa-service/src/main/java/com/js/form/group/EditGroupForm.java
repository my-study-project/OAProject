package com.js.form.group;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangshuang
 * @Description: 修改小组Form
 **/
@Data
@ToString
public class EditGroupForm {

    @ApiModelProperty("主键")
    @NotNull(message = "修改时主键不可以为空")
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