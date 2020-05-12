package com.js.vo.program;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 姜爽
 * @Description: 获取默认时间范围列表
 * @Date: 2020/5/9 11:22
 */
@Data
@Slf4j
@Builder
public class DefaultProgramDateVo {
    private String code;

    private String msg;

    private String startTime;

    private String endTime;
}
