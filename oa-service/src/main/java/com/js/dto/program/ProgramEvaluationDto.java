package com.js.dto.program;

import com.js.dto.system.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 节目评估实体类
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper=true)
public class ProgramEvaluationDto extends BasePageDto {
    /** 主键uuid **/
    private String uuid;

    /** 节目评估人 **/
    private String assessor;

    /** 评估的节目id **/
    private String programFileId;

    /** 评估分数 **/
    private Long score;

    /** 对应节目评论 **/
    private String comment;

    /** 审核人 **/
    private String reviewer;

    /** 0:待审核 1:通过 2：驳回 **/
    private String evaluationStatus;

    /** 当前年份 **/
    private String academicYear;

    /** 1:上学期 2:下学期 **/
    private String academicTerm;

    /** 所处教学周 **/
    private Integer teachingWeek;
}