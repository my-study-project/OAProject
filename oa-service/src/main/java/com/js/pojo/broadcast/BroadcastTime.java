package com.js.pojo.broadcast;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 节目相关播放日期数据
 **/
@Data
@ToString
public class BroadcastTime {
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
}