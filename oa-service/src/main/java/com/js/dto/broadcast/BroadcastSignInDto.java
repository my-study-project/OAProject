package com.js.dto.broadcast;

import com.js.dto.system.BasePageDto;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: jiangshuang
 * @Description: 节目相关签到日期数据
 **/
@Data
@ToString
public class BroadcastSignInDto extends BasePageDto {
    /**主键**/
    private String uuid;

    /**节目id**/
    private String programId;

    /**签到人**/
    private String userId;

    /**签到时间**/
    private Date createData;

    /**当前所处教学周**/
    private Integer teachingWeek;

    /**当前所处年度**/
    private String academicYear;

    /**当前年度第几学期**/
    private String academicTerm;
}