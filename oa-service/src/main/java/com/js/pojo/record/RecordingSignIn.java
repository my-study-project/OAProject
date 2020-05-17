package com.js.pojo.record;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
/**
 * @Author: jiangshuang
 * @Description: 录音室签到实体类
 **/
@Data
@ToString
public class RecordingSignIn {
    /** 主键**/
    private String uuid;

    /** 用户名 **/
    private String userId;

    /**1:第一录音室2：第二录音室 **/
    private String recordingRoom;

    /** 签到时间**/
    private Date createTime;
}