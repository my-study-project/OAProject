package com.js.service.broadcast;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.exception.SystemException;
import com.js.common.util.DateUtil;
import com.js.dto.broadcast.BroadcastTimeDto;
import com.js.enums.program.ProgramDateEnum;
import com.js.mapper.broadcast.BroadcastSignInMapper;
import com.js.mapper.broadcast.BroadcastTimeMapper;
import com.js.mapper.system.SysConfigMapper;
import com.js.pojo.broadcast.BroadcastSignIn;
import com.js.pojo.broadcast.BroadcastTime;
import com.js.pojo.system.SysConfig;
import com.js.vo.broadcast.BroadcastTimeVo;
import com.js.vo.program.DefaultProgramDateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目对应的播出时间详细信息Service
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class BroadcastTimeService {

    /**当前年度**/
    private static final String ACADEMIC_YEAR = "ACADEMIC_YEAR";

    /**当前学期**/
    private static final String ACADEMIC_TERM = "ACADEMIC_TERM";

    /**当前周**/
    private static final String ACADEMIC_WEEK = "ACADEMIC_WEEK";

    @Autowired
    private BroadcastTimeMapper broadcastTimeMapper;

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Autowired
    private BroadcastSignInMapper broadcastSignInMapper;

    /**删除操作**/
    public int deleteBroadcastIime(String uuid) {
        log.info("删除节目详细播出时间的uuid={}",uuid);
        return broadcastTimeMapper.deleteBroadcastIime(uuid);
    }

    /**添加操作**/
    public int addBroadcastIime(BroadcastTimeDto broadcastTimeDto){
        log.info("添加节目播出时间入参{}",broadcastTimeDto.toString());
        BroadcastTime broadcastTime = new BroadcastTime();
        BeanUtils.copyProperties(broadcastTimeDto,broadcastTime);
        DefaultProgramDateVo defaultProgramDateVo = ProgramDateEnum.getEnumValues(broadcastTimeDto.getCode());
        broadcastTimeDto.setDayOfWeek(defaultProgramDateVo.getDayOfWeek());
        broadcastTimeDto.setStartSignTime(broadcastTimeDto.getStartSignTime().replace(":",""));
        broadcastTimeDto.setStopSignTime(broadcastTimeDto.getStopSignTime().replace(":",""));

        return broadcastTimeMapper.addBroadcastIime(broadcastTime);
    }

    /**操作根据主键查询**/
    public BroadcastTimeVo getBroadcastIimeById(String uuid) {
        log.info("根据主键查询播出时间的uuid={}",uuid);
        BroadcastTime broadcastTime = broadcastTimeMapper.getBroadcastIimeById(uuid);
        BroadcastTimeVo broadcastTimeVo = new BroadcastTimeVo();
        BeanUtils.copyProperties(broadcastTime,broadcastTimeVo);
        return broadcastTimeVo;
    }

    /**修改操作**/
    public int editBroadcastIime(BroadcastTimeDto broadcastTimeDto){
        log.info("修改节目播出时间入参{}",broadcastTimeDto.toString());
        BroadcastTime broadcastTime = new BroadcastTime();
        BeanUtils.copyProperties(broadcastTimeDto,broadcastTime);
        DefaultProgramDateVo defaultProgramDateVo = ProgramDateEnum.getEnumValues(broadcastTimeDto.getCode());
        broadcastTimeDto.setStartSignTime(broadcastTimeDto.getStartSignTime().replace(",",""));
        broadcastTimeDto.setStopSignTime(broadcastTimeDto.getStopSignTime().replace(",",""));
        broadcastTimeDto.setDayOfWeek(defaultProgramDateVo.getDayOfWeek());
        return broadcastTimeMapper.editBroadcastIime(broadcastTime);
    }

    /**根据条件查询操作**/
    public PageInfo<BroadcastTimeVo> getBroadcastIimeByMess(BroadcastTimeDto broadcastTimeDto){
        log.info("条件查询节目签到时间节目入参={}",broadcastTimeDto.toString());
        PageHelper.startPage(broadcastTimeDto.getOffset(),broadcastTimeDto.getLimit());
        /**条件查询入参**/
        BroadcastTime broadcastTime = new BroadcastTime();
        BeanUtils.copyProperties(broadcastTimeDto,broadcastTime);
        //返回的结果集
        PageInfo<BroadcastTimeVo> broadcastTimeVoPageInfo = null;

        List<BroadcastTimeVo> broadcastTimeVoList = new ArrayList<>();
        try{
            List<BroadcastTime> broadcastTimeList = broadcastTimeMapper.getBroadcastIimeByMess(broadcastTime);
            //通过中间变量保存page信息
            PageInfo<BroadcastTime> broadcastTimePageInfo = new PageInfo<>(broadcastTimeList);
            broadcastTimeList.forEach(broadcastTimeTemp -> {
                BroadcastTimeVo broadcastTimeVo = new BroadcastTimeVo();
                BeanUtils.copyProperties(broadcastTimeTemp,broadcastTimeVo);
                broadcastTimeVoList.add(broadcastTimeVo);
            });
            broadcastTimeVoPageInfo = new PageInfo<>(broadcastTimeVoList);
            BeanUtils.copyProperties(broadcastTimePageInfo,broadcastTimeVoPageInfo);
        }catch (Exception e){
            throw new SystemException("查询签到失败");
        }
        broadcastTimeVoPageInfo.setList(broadcastTimeVoList);
        return broadcastTimeVoPageInfo;
    }

    /**获取当前时间范围的签到信息**/
    public List<BroadcastTimeVo> getBroadcastIimeByDate(BroadcastTimeDto broadcastTimeDto){
        log.info("条件查询节目签到时间节目入参={}",broadcastTimeDto.toString());
        //获取当前时间
        Date date = DateUtil.getDate(null);

        String week = DateUtil.getWeekOfDate(date);
        String nowHour = DateUtil.dateToStringByPattern(date,"HHmm");
        /**条件查询入参**/
        BroadcastTime broadcastTime = new BroadcastTime();
        broadcastTimeDto.setStartSignTime(nowHour);
        broadcastTimeDto.setStopSignTime(nowHour);
        broadcastTimeDto.setDayOfWeek(Integer.valueOf(week));
        BeanUtils.copyProperties(broadcastTimeDto,broadcastTime);

        //获取系统日志文件，目的是获取当前时间的基础数据例如当前学期等基本信息组装查询数据
        SysConfig sysConfig = new SysConfig();
        List<SysConfig> sysConfigList = sysConfigMapper.getSysConfigByMess(sysConfig);

        //获取已经签到的节目信息(组装查询参数)
        BroadcastSignIn broadcastSignIn = new BroadcastSignIn();
        sysConfigList.forEach(sysConfigVo ->{
            if (ACADEMIC_YEAR.equals(sysConfigVo.getName())){
                broadcastSignIn.setAcademicYear(sysConfigVo.getValue());
            }else if(ACADEMIC_TERM.equals(sysConfigVo.getName())){
                broadcastSignIn.setAcademicTerm(sysConfigVo.getValue());
            }else if(ACADEMIC_WEEK.equals(sysConfigVo.getName())){
                broadcastSignIn.setTeachingWeek(Integer.valueOf(sysConfigVo.getValue()));
            }
        });
        List<BroadcastSignIn> broadcastSignInList = broadcastSignInMapper.getBroadcastSignInByMess(broadcastSignIn);
        List<BroadcastTimeVo> broadcastTimeVoList = new ArrayList<>();
        try{
            List<BroadcastTime> broadcastTimeList = broadcastTimeMapper.getBroadcastIimeByMess(broadcastTime);
            //首先判空
            if (!broadcastTimeList.isEmpty()){
                broadcastTimeList.forEach(broadcastTimeTemp -> {
                    BroadcastTimeVo broadcastTimeVo = new BroadcastTimeVo();
                    BeanUtils.copyProperties(broadcastTimeTemp,broadcastTimeVo);
                    //根剧节目id和相关时间判定当前节目是否已经签到，如果已完成签到则设置为1
                    if (!broadcastSignInList.isEmpty()){
                        broadcastSignInList.forEach(broadcastSignInTemp -> {
                            if (broadcastSignInTemp.getProgramId().equals(broadcastTimeVo.getProgramId())){
                                broadcastTimeVo.setStatus("1");
                            }
                        });
                    }
                    broadcastTimeVoList.add(broadcastTimeVo);
                });
            }

        }catch (Exception e){
            log.info("查询出现异常{}",e);
            throw new SystemException("查询失败");
        }
        return broadcastTimeVoList;
    }
}