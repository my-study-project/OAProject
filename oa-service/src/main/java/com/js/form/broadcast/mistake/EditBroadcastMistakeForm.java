package com.js.form.broadcast.mistake;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: jiangshuang
 * @Description: 节目错误相关实体类
 **/
@Data
@ToString
public class EditBroadcastMistakeForm {
    @ApiModelProperty("主键")
    @NotNull(message = "主键不可以为空")
    private String uuid;

    @ApiModelProperty("节目id")
    private String programId;

    @ApiModelProperty("节目所属组别")
    private String groupId;

    @ApiModelProperty("日期")
    private String broadcastDate;

    @ApiModelProperty("错误描述")
    private String detail;

    @ApiModelProperty("错误提交时间")
    private Date createData;

    @ApiModelProperty("错误审核状态0:未审核1:已审核")
    private String status;
}