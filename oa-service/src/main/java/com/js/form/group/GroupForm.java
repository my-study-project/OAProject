package com.js.form.group;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * @Author: jiangshuang
 * @Description: 条件查询Form
 **/
@Data
@ToString
public class GroupForm {

    @ApiModelProperty("组名")
    private String deptName;

    @ApiModelProperty("0：有节目 1：无节目")
    private String hasProgram;

    @ApiModelProperty("0：正在运营 1：已停用")
    private String isAlive;
}