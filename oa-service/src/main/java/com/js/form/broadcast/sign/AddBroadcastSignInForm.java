package com.js.form.broadcast.sign;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangshuang
 * @Description: 节目相关签到日期数据
 **/
@Data
@ToString
public class AddBroadcastSignInForm {

    @ApiModelProperty("节目id")
    @NotNull(message = "节目id不可以为空")
    private String programId;

    @ApiModelProperty("签到人")
    private String userId;
}