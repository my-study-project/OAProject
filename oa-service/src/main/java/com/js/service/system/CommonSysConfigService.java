package com.js.service.system;

import com.js.common.enums.SysEnum;
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

    @Autowired
    private SysConfigMapper sysConfigMapper;

    public SysConfigCommon getSysConfig(){
        SysConfigCommon sysConfigCommon = new SysConfigCommon();
        SysConfig sysConfig = new SysConfig();
        List<SysConfig> sysConfigList = sysConfigMapper.getSysConfigByMess(sysConfig);
        sysConfigList.forEach(sysConfigVo -> {
            if (SysEnum.ACADEMIC_YEAR.getCode().equals(sysConfigVo.getName())) {
                sysConfigCommon.setAcademicYear(sysConfigVo.getValue());
            } else if (SysEnum.ACADEMIC_TERM.getCode().equals(sysConfigVo.getName())) {
                sysConfigCommon.setAcademicTerm(sysConfigVo.getValue());
            } else if (SysEnum.ACADEMIC_WEEK.getCode().equals(sysConfigVo.getName())) {
                sysConfigCommon.setTeachingWeek(Integer.valueOf(sysConfigVo.getValue()));
            } else if(SysEnum.ACADEMIC_BEGIN.getCode().equals(sysConfigVo.getName())){
                sysConfigCommon.setAcademicBegin(sysConfigVo.getName());
            }
        });
        return sysConfigCommon;
    }
}
