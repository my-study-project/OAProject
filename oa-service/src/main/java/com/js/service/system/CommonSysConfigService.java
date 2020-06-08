package com.js.service.system;

import com.js.mapper.system.SysConfigMapper;
import com.js.pojo.system.SysConfig;
import com.js.vo.system.SysConfigCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 系统配置文件学期和年份等
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class CommonSysConfigService {

    /** 当前年度 **/
    private static final String ACADEMIC_YEAR = "ACADEMIC_YEAR";

    /** 当前学期 **/
    private static final String ACADEMIC_TERM = "ACADEMIC_TERM";

    /** 当前周 **/
    private static final String ACADEMIC_WEEK = "ACADEMIC_WEEK";

    /** 当前学期开始时间 **/
    private static final String ACADEMIC_BEGIN = "ACADEMIC_BEGIN";

    @Autowired
    private SysConfigMapper sysConfigMapper;

    public SysConfigCommon getSysConfig(){
        SysConfigCommon sysConfigCommon = new SysConfigCommon();
        SysConfig sysConfig = new SysConfig();
        List<SysConfig> sysConfigList = sysConfigMapper.getSysConfigByMess(sysConfig);
        sysConfigList.forEach(sysConfigVo -> {
            if (ACADEMIC_YEAR.equals(sysConfigVo.getName())) {
                sysConfigCommon.setAcademicYear(sysConfigVo.getValue());
            } else if (ACADEMIC_TERM.equals(sysConfigVo.getName())) {
                sysConfigCommon.setAcademicTerm(sysConfigVo.getValue());
            } else if (ACADEMIC_WEEK.equals(sysConfigVo.getName())) {
                sysConfigCommon.setTeachingWeek(Integer.valueOf(sysConfigVo.getValue()));
            } else if(ACADEMIC_BEGIN.equals(sysConfigVo.getName())){
                sysConfigCommon.setAcademicBegin(sysConfigVo.getName());
            }
        });
        return sysConfigCommon;
    }
}
