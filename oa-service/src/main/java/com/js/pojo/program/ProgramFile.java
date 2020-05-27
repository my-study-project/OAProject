package com.js.pojo.program;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: jiangshuang
 * @Description: 节目文件相关信息
 **/
@Data
@ToString
public class ProgramFile {
    /** 节目文件uuid **/
    private String uuid;

    /** 上传者 **/
    private String uploadUserId;

    /** 节目id **/
    private String programId;

    /** 所处年份 **/
    private String academicYear;

    /** 所处学期 **/
    private String academicTerm;

    /** 所处教学周 **/
    private Integer teachingWeek;

    /** 播出时间 **/
    private Date broadcastDate;

    /** 0：有效1：无效 **/
    private String canBroadcast;

    /** 0：可以删除1：不可以删除 **/
    private String canDelete;

    /** 0：未结束1：已结束 **/
    private String evaluateFinish;

    /** 评估期限距离播放时间 **/
    private Integer needEvaluateTimes;

    /** 平均分数 **/
    private Long averageScore;

    /** 文件路径 **/
    private String filePosition;

    /** 文件上传时间 **/
    private Date uploadDate;
}