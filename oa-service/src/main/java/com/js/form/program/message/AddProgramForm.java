package com.js.form.program.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangshuang
 * @Description: 节目相关实体类
 **/
@Data
@ToString
public class AddProgramForm {

    @ApiModelProperty("节目名称")
    @NotNull(message = "节目名称不可以为空")
    private String programName;

    @ApiModelProperty("所属组别id")
    @NotNull(message = "所属组别不可以为空")
    private String groupId;

    @ApiModelProperty("0：节目有效1：节目无效")
    private String isAlive;

    @ApiModelProperty("0：需要评估 1：不需要评估")
    @NotNull(message = "节目是否需要评估不可以为空")
    private String needEvaluate;

    @ApiModelProperty("负责节目的人员，中间；隔开")
    private String principal;
}