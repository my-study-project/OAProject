package com.js.form.group;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jiangshuang
 * @Description: 添加小组Form
 **/
@Data
@ToString
public class AddGroupForm {

    @ApiModelProperty("主键")
    private String uuid;

    @ApiModelProperty("组名")
    @NotBlank(message = "小组名称不可以为空")
    private String deptName;

    @ApiModelProperty("错误总分")
    private Integer mistakeScore;

    @ApiModelProperty("节目总分")
    private Integer programScore;

    @ApiModelProperty("负责人")
    @NotBlank(message = "负责人不可以为空")
    private String leaderUserId;

    @ApiModelProperty("0：有节目 1：无节目")
    @NotBlank(message = "是否有节目不可以为空")
    private String hasProgram;

    @ApiModelProperty("0：正在运营 1：已停用")
    @NotBlank(message = "小组运行状态不可以为空")
    private String isAlive;
}