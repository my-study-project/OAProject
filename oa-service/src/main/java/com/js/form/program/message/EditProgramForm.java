package com.js.form.program.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jiangshuang
 * @Description: 节目相关实体类
 **/
@Data
@ToString
public class EditProgramForm {

    @ApiModelProperty("主键")
    @NotBlank(message = "修改时主键不可以为空")
    private String uuid;

    @ApiModelProperty("节目名称")
    private String name;

    @ApiModelProperty("所属组别id")
    private String groupId;

    @ApiModelProperty("0：节目有效1：节目无效")
    private String isAlive;

    @ApiModelProperty("0：需要评估 1：不需要评估")
    private String needEvaluate;

    @ApiModelProperty("负责节目的人员，中间；隔开")
    private String principal;
}