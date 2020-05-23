package com.js.form.program.evaluation;

import com.js.form.system.BasePageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangshuang
 * @Description: 节目评估实体类
 **/
@Data
@ToString
public class EditProgramEvaluationForm extends BasePageForm {
    @ApiModelProperty("主键")
    @NotNull(message = "修改时主键不可以为空")
    private String uuid;

    @ApiModelProperty("节目评估人")
    private String assessor;

    @ApiModelProperty("评估的节目id")
    private String programFileId;

    @ApiModelProperty("评估分数")
    private Long score;

    @ApiModelProperty("对应节目评论")
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