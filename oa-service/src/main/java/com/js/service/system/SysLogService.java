package com.js.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.dto.system.SysLogDto;
import com.js.mapper.system.SysLogMapper;
import com.js.pojo.system.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 姜爽
 * @Description: 系统日志相关Servcie
 * @Date: 2020/5/4 9:31
 */
@Service
@Slf4j
public class SysLogService{

    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * 添加系统日志
     **/
    public void addSysLog(SysLogDto sysLogDto) {
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(sysLogDto,sysLog);
        int result = sysLogMapper.addSysLog(sysLog);
        log.info("系统执行日志添加功能结果{}",result);
    }

    /**
     * 查询系统日志
     **/
    public PageInfo<SysLogDto> showAllLog(Integer offset,Integer limit) {
        log.info("查询入参为{},{}",offset,limit);
        PageHelper.startPage(offset,limit);
        log.info("========================");
        List<SysLog> sysLogs = sysLogMapper.showAllLog();
        log.info("查询结果为{}",sysLogs);
        if (sysLogs == null){
            return null;
        }
        List<SysLogDto> sysLogDtos = new ArrayList<>();
        sysLogs.forEach(sysLog -> {
            SysLogDto sysLogDto = new SysLogDto();
            BeanUtils.copyProperties(sysLog,sysLogDto);
            sysLogDtos.add(sysLogDto);
        });
        PageInfo<SysLogDto> sysLogDtoPageInfo = new PageInfo<>(sysLogDtos);
        return sysLogDtoPageInfo;
    }
}
