package com.js.form.broadcast.mistake;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: jiangshuang
 * @Description: 节目错误相关实体类
 **/
@Data
@ToString
public class AddBroadcastMistakeForm {
    @ApiModelProperty("主键")
    private String uuid;

    @ApiModelProperty("节目id")
    private String programId;

    @ApiModelProperty("节目所属组别")
    private String groupId;

    @ApiModelProperty("日期")
    private String broadcastDate;

    @ApiModelProperty("错误描述")
    private String detail;

    @ApiModelProperty("所处年度")
    private String academicYear;

    @ApiModelProperty("所处学期 1：第一学期2：第二学期")
    private String academicTerm;

    @ApiModelProperty("所处教学周")
    private Integer teachingWeek;

    @ApiModelProperty("错误提交时间")
    private Date createData;

    @ApiModelProperty("错误审核状态0:未审核1:已审核")
    private String status;
}