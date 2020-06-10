package com.js.vo.broadcast;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: jiangshuang
 * @Description: 节目错误相关实体类
 **/
@Data
@ToString
public class BroadcastMistakeExport {

    /** 主键 **/
    private String uuid;

    /** 节目id **/
    private String programName;

    /** 错误描述 **/
    private String detail;

    /** 错误审核状态 **/
    private String status;

    /** 所处时间段编码 **/
    private String period;
}