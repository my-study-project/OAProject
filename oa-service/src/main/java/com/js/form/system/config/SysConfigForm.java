package com.js.form.system.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * @Author jiangshuang
 * @Description 查询条件系统常用配置文件
 **/
@ToString
@Data
public class SysConfigForm {

    @ApiModelProperty("属性名称")
    private String name;

    @ApiModelProperty("对应属性值")
    private String value;

    @ApiModelProperty("属性描述")
    private String confDesc;
}