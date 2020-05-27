package com.js.dto.broadcast;

import com.js.dto.system.BasePageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 节目相关播放日期数据
 **/
@Data
@ToString
public class BroadcastTimeDto extends BasePageDto {
    /** 主键 **/
    private String uuid;

    /** 节目id **/
    private String programId;

    /** 周几 **/
    private Integer dayOfWeek;

    /** 开始签到时间(时分秒) **/
    private String startSignTime;

    /** 签到时间截至(时分秒) **/
    private String stopSignTime;

    @ApiModelProperty("对应时间范围编码")
    private String code;
}