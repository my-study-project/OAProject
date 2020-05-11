package com.js.task;

import com.js.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 表示每隔一个小时执行一次定时任务
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    private void proces(){
        log.info("执行当前定时任务的时间{}", DateUtil.dateToStringMin(null));
        /**
         * 下面的代码是执行的业务逻辑代码：
         * 暂定需要根据设置的开始时间确认当前的周数和其余相关信息的定时更新
         **/
    }
}
