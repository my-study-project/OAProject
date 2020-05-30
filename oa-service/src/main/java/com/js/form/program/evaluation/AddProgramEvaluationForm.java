package com.js.form.program.evaluation;

import com.js.form.system.BasePageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangshuang
 * @Description: 节目评估实体类
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper=true)
public class AddProgramEvaluationForm extends BasePageForm {

    @ApiModelProperty("节目评估人")
    private String assessor;

    @ApiModelProperty("评估的节目id")
    @NotNull(message = "评估的节目id不可以为空")
    private String programFileId;

    @ApiModelProperty("评估分数")
    @NotNull(message = "评估分数不可以为空")
    private Long score;

    @ApiModelProperty("对应节目评论")
    @NotNull(message = "对应节目评论不可以为空")
    private String comment;

    @ApiModelProperty("审核人")
    private String reviewer;

    @ApiModelProperty("0:待审核 1:通过 2：驳回 ")
    private String evaluationStatus;

    @ApiModelProperty("当前年份")
    private String academicYear;

    @ApiModelProperty("1:上学期 2:下学期")
    private String academicTerm;

    @ApiModelProperty("所处教学周")
    private Integer teachingWeek;
}