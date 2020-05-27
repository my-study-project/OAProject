package com.js.pojo.broadcast;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: jiangshuang
 * @Description: 节目错误相关实体类
 **/
@Data
@ToString
public class BroadcastMistake {
    /** 主键 **/
    private String uuid;

    /** 节目id **/
    private String programId;

    /** 节目所属组别 **/
    private String groupId;

    /** 日期 **/
    private String broadcastDate;

    /** 错误描述 **/
    private String detail;

    /** 所处年度 **/
    private String academicYear;

    /** 所处学期 1：第一学期2：第二学期 **/
    private String academicTerm;

    /** 所处教学周 **/
    private Integer teachingWeek;

    /** 错误提交时间 **/
    private Date createData;

    /** 错误审核状态 **/
    private String status;
}