package com.js.service.broadcast;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.exception.SystemException;
import com.js.common.util.IdUtils;
import com.js.dto.broadcast.BroadcastSignInDto;
import com.js.mapper.broadcast.BroadcastSignInMapper;
import com.js.pojo.broadcast.BroadcastSignIn;
import com.js.service.system.CommonSysConfigService;
import com.js.vo.broadcast.BroadcastSignInVo;
import com.js.vo.system.SysConfigCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 签到相关操作Service
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class BroadcastSignInService {

    @Autowired
    private CommonSysConfigService commonSysConfigService;

    @Autowired
    private BroadcastSignInMapper broadcastSignInMapper;

    /** 添加签到操作 **/
    @Transactional(isolation = Isolation.SERIALIZABLE,rollbackFor = SystemException.class)
    public int addBroadcastSignIn(BroadcastSignInDto broadcastSignInDto) {
        BroadcastSignIn broadcastSignIn = new BroadcastSignIn();
        BeanUtils.copyProperties(broadcastSignInDto, broadcastSignIn);
        broadcastSignIn.setUuid(IdUtils.get32Uuid());

        SysConfigCommon sysConfigCommon = commonSysConfigService.getSysConfig();
        broadcastSignIn.setAcademicYear(sysConfigCommon.getAcademicYear());
        broadcastSignIn.setAcademicTerm(sysConfigCommon.getAcademicTerm());
        broadcastSignIn.setTeachingWeek(sysConfigCommon.getTeachingWeek());

        List<BroadcastSignIn> broadcastSignInList = broadcastSignInMapper.getBroadcastSignInByMess(broadcastSignIn);
        if (broadcastSignInList.isEmpty()) {
            broadcastSignInMapper.addBroadcastSignIn(broadcastSignIn);
            return 1;
        } else {
            throw new SystemException("当前节目已完成签到，无需二次签到");
        }
    }

    /** 根据条件查询查询签到操作 **/
    public PageInfo<BroadcastSignInVo> getBroadcastSignInByMess(BroadcastSignInDto broadcastSignInDto) {
        log.info("条件查询节目默认查询当前学期入参={}", broadcastSignInDto.toString());
        PageHelper.startPage(broadcastSignInDto.getOffset(), broadcastSignInDto.getLimit());
        /** 条件查询入参 **/
        BroadcastSignIn broadcastSignIn = new BroadcastSignIn();
        BeanUtils.copyProperties(broadcastSignInDto,broadcastSignIn);

        if ("".equals(broadcastSignIn.getAcademicYear()) || "".equals(broadcastSignIn.getAcademicTerm())){
            SysConfigCommon sysConfigCommon = commonSysConfigService.getSysConfig();
            broadcastSignIn.setAcademicYear(sysConfigCommon.getAcademicYear());
            broadcastSignIn.setAcademicTerm(sysConfigCommon.getAcademicTerm());
            broadcastSignIn.setTeachingWeek(sysConfigCommon.getTeachingWeek());
            if (null == broadcastSignInDto.getTeachingWeek()) {
                broadcastSignIn.setTeachingWeek(sysConfigCommon.getTeachingWeek());
            }
        }
        List<BroadcastSignInVo> broadcastSignInVoList = new ArrayList<>();
        try {
            List<BroadcastSignIn> broadcastSignInList = broadcastSignInMapper.getBroadcastSignInByMess(broadcastSignIn);
            PageInfo<BroadcastSignIn> broadcastSignInPageInfo = new PageInfo<>(broadcastSignInList);
            PageInfo<BroadcastSignInVo> broadcastSignInVoPageInfo = new PageInfo<>();
            BeanUtils.copyProperties(broadcastSignInPageInfo, broadcastSignInVoPageInfo);
            broadcastSignInList.forEach(broadcastSignInTemp -> {
                BroadcastSignInVo broadcastSignInVo = new BroadcastSignInVo();
                BeanUtils.copyProperties(broadcastSignInTemp, broadcastSignInVo);
                broadcastSignInVoList.add(broadcastSignInVo);
            });
            broadcastSignInVoPageInfo.setList(broadcastSignInVoList);
            return broadcastSignInVoPageInfo;
        } catch (Exception e) {
            throw new SystemException("查询签到失败");
        }
    }

    /**
     * 清理所有日志
     */
    public void truncateBroadcastSignIn() {
        broadcastSignInMapper.truncateBroadcastSignIn();
    }

}