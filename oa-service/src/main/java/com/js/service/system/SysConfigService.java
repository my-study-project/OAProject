package com.js.service.system;


import com.github.pagehelper.PageHelper;
import com.js.dto.system.SysConfigDto;
import com.js.mapper.system.SysConfigMapper;
import com.js.pojo.system.SysConfig;
import com.js.vo.system.SysConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 系统配置文件Mapper
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;
    /**删除操作**/
    public int deleteSysConfig(String uuid) {
        log.info("查询主键uuid={}入参={}",uuid);
        return sysConfigMapper.deleteSysConfig(uuid);
    }

    /**添加系统配置操作**/
    public int addSysConfig(SysConfigDto sysConfigDto) {
        SysConfig sysConfig = new SysConfig();
        BeanUtils.copyProperties(sysConfigDto,sysConfig);
        return sysConfigMapper.addSysConfig(sysConfig);
    }

    /**根据主键查询操作**/
    public SysConfigVo getSysConfigById(String uuid) {
        log.info("查询主键uuid={}入参={}",uuid);
        SysConfig sysConfig = sysConfigMapper.getSysConfigById(uuid);
        SysConfigVo sysConfigVo = null;
        BeanUtils.copyProperties(sysConfig,sysConfigVo);
        return sysConfigVo;
    }

    /**根据条件查询操作**/
    public List<SysConfigVo> getSysConfigByMess(SysConfigDto sysConfigDto) {
        log.info("条件查询系统配置入参={}",sysConfigDto.toString());
        SysConfig sysConfig = new SysConfig();
        BeanUtils.copyProperties(sysConfigDto,sysConfig);
        List<SysConfigVo> sysConfigVoList = new ArrayList<>();
        List<SysConfig> sysConfigList = sysConfigMapper.getSysConfigByMess(sysConfig);
        sysConfigList.forEach(sysConfigTemp -> {
            SysConfigVo sysConfigVo = new SysConfigVo();
            BeanUtils.copyProperties(sysConfigTemp,sysConfigVo);
            sysConfigVoList.add(sysConfigVo);
        });
        return sysConfigVoList;
    }

    /**修改操作**/
    public int editSysConfig(SysConfigDto sysConfigDto){
        log.info("条件查询系统配置入参={}",sysConfigDto.toString());
        SysConfig sysConfig = new SysConfig();
        BeanUtils.copyProperties(sysConfigDto,sysConfig);
        return sysConfigMapper.editSysConfig(sysConfig);
    }

}