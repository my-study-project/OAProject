package com.js.form.broadcast.mistake;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangshuang
 * @Description: 节目错误相关实体类
 **/
@Data
@ToString
public class AddBroadcastMistakeForm {

    @ApiModelProperty("节目id")
    @NotNull(message = "节目id不可以为空")
    private String programId;

    @ApiModelProperty("节目所属组别")
    @NotNull(message = "节目所属组别不可以为空")
    private String groupId;

    @ApiModelProperty("日期")
    @NotNull(message = "日期不可以为空")
    private String broadcastDate;

    @ApiModelProperty("错误描述")
    @NotNull(message = "错误描述不可以为空")
    private String detail;

    @ApiModelProperty("错误审核状态0:未审核1:已审核")
    private String status;
}