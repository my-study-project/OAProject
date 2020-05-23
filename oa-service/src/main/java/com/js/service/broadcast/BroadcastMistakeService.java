package com.js.service.broadcast;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.exception.SystemException;
import com.js.common.util.IdUtils;
import com.js.dto.broadcast.BroadcastMistakeDto;
import com.js.dto.system.SysConfigDto;
import com.js.mapper.broadcast.BroadcastMistakeMapper;
import com.js.pojo.broadcast.BroadcastMistake;
import com.js.service.system.SysConfigService;
import com.js.vo.broadcast.BroadcastMistakeVo;
import com.js.vo.system.SysConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目错误相关操作Mapper
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class BroadcastMistakeService {

    /**当前年度**/
    private static final String ACADEMIC_YEAR = "ACADEMIC_YEAR";

    /**当前学期**/
    private static final String ACADEMIC_TERM = "ACADEMIC_TERM";

    /**当前周**/
    private static final String ACADEMIC_WEEK = "ACADEMIC_WEEK";

    @Autowired
    private BroadcastMistakeMapper broadcastMistakeMapper;

    @Autowired
    private SysConfigService sysConfigService;

    /**删除操作**/
    public int deleteBroadcastMistake(String uuid){
        return broadcastMistakeMapper.deleteBroadcastMistake(uuid);
    }

    /**添加操作**/
    public int addBroadcastMistake(BroadcastMistakeDto broadcastMistakeDto){
        BroadcastMistake broadcastMistake = new BroadcastMistake();
        BeanUtils.copyProperties(broadcastMistakeDto,broadcastMistake);

        //获取当前学期详细信息
        SysConfigDto sysConfigDto = new SysConfigDto();
        List<SysConfigVo> sysConfigVoList = sysConfigService.getSysConfigByMess(sysConfigDto);
        sysConfigVoList.forEach(sysConfigVo ->{
            if (ACADEMIC_YEAR.equals(sysConfigVo.getName())){
                broadcastMistake.setAcademicYear(sysConfigVo.getValue());
            }else if(ACADEMIC_TERM.equals(sysConfigVo.getName())){
                broadcastMistake.setAcademicTerm(sysConfigVo.getValue());
            }else if(ACADEMIC_WEEK.equals(sysConfigVo.getName())){
                broadcastMistake.setTeachingWeek(Integer.valueOf(sysConfigVo.getValue()));
            }
        });
        broadcastMistake.setUuid(IdUtils.get32Uuid());
        return broadcastMistakeMapper.addBroadcastMistake(broadcastMistake);
    }

    /**根据主键查询**/
    public BroadcastMistakeVo getBroadcastMistakeById(String uuid){
        BroadcastMistake broadcastMistake = broadcastMistakeMapper.getBroadcastMistakeById(uuid);
        BroadcastMistakeVo broadcastMistakeVo = new BroadcastMistakeVo();
        BeanUtils.copyProperties(broadcastMistake,broadcastMistakeVo);
        return broadcastMistakeVo;
    }

    /**修改操作**/
    public int editBroadcastMistake(BroadcastMistakeDto broadcastMistakeDto){
        BroadcastMistake broadcastMistake = new BroadcastMistake();
        BeanUtils.copyProperties(broadcastMistakeDto,broadcastMistake);
        return broadcastMistakeMapper.editBroadcastMistake(broadcastMistake);
    }

    /**根据条件查询操作**/
    public PageInfo<BroadcastMistakeVo> getBroadcastMistakeByMess(BroadcastMistakeDto broadcastMistakeDto){
        log.info("条件查询节目入参={}",broadcastMistakeDto.toString());
        /**封装入参**/
        BroadcastMistake broadcastMistake = new BroadcastMistake();
        BeanUtils.copyProperties(broadcastMistakeDto,broadcastMistake);
        PageHelper.startPage(broadcastMistakeDto.getOffset(),broadcastMistakeDto.getLimit());

        /**结果变量**/
        PageInfo<BroadcastMistakeVo> broadcastMistakeVoPageInfo = new PageInfo<>();
        try{
            List<BroadcastMistake> broadcastMistakeList = broadcastMistakeMapper.getBroadcastMistakeByMess(broadcastMistake);
            PageInfo<BroadcastMistake> broadcastMistakePageInfo = new PageInfo<>(broadcastMistakeList);
            BeanUtils.copyProperties(broadcastMistakePageInfo,broadcastMistakeVoPageInfo);

            /**结果集转换**/
            List<BroadcastMistakeVo> broadcastMistakeVoList = new ArrayList<>();
            broadcastMistakeList.forEach(broadcastMistakeTemp -> {
                BroadcastMistakeVo broadcastMistakeVo = new BroadcastMistakeVo();
                BeanUtils.copyProperties(broadcastMistakeTemp,broadcastMistakeVo);
                broadcastMistakeVoList.add(broadcastMistakeVo);
            });
            broadcastMistakeVoPageInfo.setList(broadcastMistakeVoList);
            return broadcastMistakeVoPageInfo;
        }catch (Exception e){
            throw new SystemException("查询签到失败");
        }
    }

}