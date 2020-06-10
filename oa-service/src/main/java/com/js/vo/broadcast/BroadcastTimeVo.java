package com.js.vo.broadcast;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 节目相关播放日期数据
 **/
@Data
@ToString
public class BroadcastTimeVo {
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

    @ApiModelProperty("对应的节目签到状态")
    private String status;

    @ApiModelProperty("签到所处时间段编码")
    private String period;
}