package com.js.form.broadcast.time;

import com.js.form.system.BasePageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 节目相关播放日期数据
 **/
@Data
@ToString
public class BroadcastTimeForm extends BasePageForm {
    @ApiModelProperty("主键")
    private String uuid;

    @ApiModelProperty("节目id")
    private String programId;

    @ApiModelProperty("周几")
    private Integer dayOfWeek;

    @ApiModelProperty("开始签到时间(时分秒)")
    private String startSignTime;

    @ApiModelProperty("签到时间截至(时分秒)")
    private String stopSignTime;
}