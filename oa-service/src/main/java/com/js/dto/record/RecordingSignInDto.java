package com.js.dto.record;

import com.js.dto.system.BasePageDto;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: jiangshuang
 * @Description: 录音室签到实体类
 **/
@Data
@ToString
public class RecordingSignInDto extends BasePageDto {
    /** 主键**/
    private String uuid;

    /** 用户名 **/
    private String userId;

    /**1:第一录音室2：第二录音室 **/
    private String recordingRoom;

}