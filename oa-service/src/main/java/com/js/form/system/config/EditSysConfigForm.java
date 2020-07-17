package com.js.form.system.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author jiangshuang
 * @Description 修改系统常用配置文件
 **/
@ToString
@Data
public class EditSysConfigForm {
    @ApiModelProperty("主键")
    @NotNull(message = "主键不可以为空")
    private String uuid;

    @ApiModelProperty("属性名称")
    private String name;

    @ApiModelProperty("对应属性值")
    private String value;

    @ApiModelProperty("属性描述")
    private String confDesc;
}