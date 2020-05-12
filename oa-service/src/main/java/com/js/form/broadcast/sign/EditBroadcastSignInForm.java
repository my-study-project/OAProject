package com.js.form.broadcast.sign;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: jiangshuang
 * @Description: 节目相关签到日期数据
 **/
@Data
@ToString
public class EditBroadcastSignInForm{
    @ApiModelProperty("主键")
    private String uuid;

    @ApiModelProperty("节目id")
    private String programId;

    @ApiModelProperty("签到人")
    private String userId;

    @ApiModelProperty("签到时间")
    private Date createData;

    @ApiModelProperty("当前所处教学周")
    private Integer teachingWeek;

    /**当前所处年度**/
    @ApiModelProperty("主键")
    private String academicYear;

    @ApiModelProperty("当前年度第几学期")
    private String academicTerm;
}