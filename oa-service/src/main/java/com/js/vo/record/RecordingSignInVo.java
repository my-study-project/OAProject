package com.js.vo.record;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: jiangshuang
 * @Description: 录音室签到实体类
 **/
@Data
@ToString
public class RecordingSignInVo {
    @ApiModelProperty("主键")
    private String uuid;

    @ApiModelProperty("用户名")
    private String userId;

    @ApiModelProperty("1:第一录音室2：第二录音室 ")
    private String recordingRoom;

    @ApiModelProperty("签到时间")
    private Date createTime;
}