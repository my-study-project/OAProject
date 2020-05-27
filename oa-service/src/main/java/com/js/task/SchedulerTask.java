package com.js.task;

import com.js.common.util.DateUtil;
import com.js.dto.system.SysConfigDto;
import com.js.service.system.SysConfigService;
import com.js.vo.system.SysConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: 姜爽
 * @Description: 定时任务类,要声明为bean，没有声明启动类启动无法实现定时效果
 * @Date: 2020/5/10 18:26
 */
@Component
@Slf4j
public class SchedulerTask {

    private static final String PATTERNDATETOSTRING = "yyyy/MM/dd";

    /** 当前学期开始时间 **/
    private static final String ACADEMIC_BEGIN = "ACADEMIC_BEGIN";

    /** 当前周 **/
    private static final String ACADEMIC_WEEK = "ACADEMIC_WEEK";

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 表示每隔一个小时执行一次定时任务
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    private void proces() {
        log.info("执行当前定时任务的时间{}", DateUtil.dateToStringMin(null));
        /**
         * 下面的代码是执行的业务逻辑代码： 暂定需要根据设置的开始时间确认当前的周数和其余相关信息的定时更新
         **/
        SysConfigDto sysConfigDto = new SysConfigDto();
        try {
            // 获取当前学期开始时间
            sysConfigDto.setName(ACADEMIC_BEGIN);
            String academicBegin = sysConfigService.getSysConfigByMess(sysConfigDto).get(0).getValue();
            // 获取当前周数
            sysConfigDto.setName(ACADEMIC_WEEK);
            SysConfigVo sysConfigVo = sysConfigService.getSysConfigByMess(sysConfigDto).get(0);
            int academicWeek = Integer.parseInt(sysConfigVo.getValue());
            int tempWeek =
                DateUtil.differentDaysByMillisecond(DateUtil.strToDateLongByPattern(academicBegin, PATTERNDATETOSTRING),
                    DateUtil.getDate(null)) / 7 + 1;
            // 计算的当前周和数据库不符，更新数据字典当前周
            if (tempWeek != academicWeek) {
                BeanUtils.copyProperties(sysConfigVo, sysConfigDto);
                sysConfigDto.setValue(String.valueOf(tempWeek));
                sysConfigService.editSysConfig(sysConfigDto);
            }
        } catch (Exception e) {
            log.info("获取系统配置异常{}", e);
        }

    }
}
