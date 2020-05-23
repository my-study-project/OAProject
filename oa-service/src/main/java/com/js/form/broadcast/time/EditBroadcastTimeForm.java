package com.js.form.broadcast.time;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangshuang
 * @Description: 节目相关播放日期数据
 **/
@Data
@ToString
public class EditBroadcastTimeForm {
    @ApiModelProperty("主键")
    @NotNull(message = "主键不可以为空")
    private String uuid;

    @ApiModelProperty("节目id")
    private String programId;

    @ApiModelProperty("周几")
    private Integer dayOfWeek;

    @ApiModelProperty("开始签到时间(时分秒)")
    private String startSignTime;

    @ApiModelProperty("签到时间截至(时分秒)")
    private String stopSignTime;

    @ApiModelProperty("对应时间范围编码")
    private String code;
}