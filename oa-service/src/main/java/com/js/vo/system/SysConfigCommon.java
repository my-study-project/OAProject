package com.js.vo.system;

import lombok.Data;
import lombok.ToString;

/**
 * @Author jiangshuang
 * @Description 系统最常用配置文件部分实体
 **/
@ToString
@Data
public class SysConfigCommon {
    /** 当前所处教学周 **/
    private Integer teachingWeek;

    /** 当前所处年度 **/
    private String academicYear;

    /** 当前年度第几学期 **/
    private String academicTerm;

    /** 当前学期开始时间 **/
    private String academicBegin;
}
