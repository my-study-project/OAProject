package com.js.vo.program;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 节目相关实体类
 **/
@Data
@ToString
public class ProgramVo {
    @ApiModelProperty("主键")
    private String uuid;

    @ApiModelProperty("节目名称")
    private String programName;

    @ApiModelProperty("所属组别id")
    private String groupId;

    @ApiModelProperty("0：节目有效1：节目无效")
    private String isAlive;

    @ApiModelProperty("0：需要评估 1：不需要评估")
    private String needEvaluate;

    @ApiModelProperty("负责节目的人员，中间；隔开")
    private String principal;
}