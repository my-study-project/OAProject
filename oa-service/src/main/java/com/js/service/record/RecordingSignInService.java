package com.js.service.record;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.util.IdUtils;
import com.js.dto.record.RecordingSignInDto;
import com.js.mapper.record.RecordingSignInMapper;
import com.js.pojo.record.RecordingSignIn;
import com.js.vo.record.RecordingSignInVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目对应的播出时间详录音室签到操作相关Mapper
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class RecordingSignInService {

    @Autowired
    private RecordingSignInMapper recordingSignInMapper;

    /** 添加操作 **/
    public int addRecordingSignIn(RecordingSignInDto recordingSignInDto) {
        RecordingSignIn recordingSignIn = new RecordingSignIn();
        BeanUtils.copyProperties(recordingSignInDto, recordingSignIn);
        recordingSignIn.setUuid(IdUtils.get32Uuid());
        return recordingSignInMapper.addRecordingSignIn(recordingSignIn);
    }

    /** 操作条件查询 **/
    public PageInfo<RecordingSignInVo> getRecordingSignInByMess(RecordingSignInDto recordingSignInDto) {
        log.info("录音间签到查询入参为{}", recordingSignInDto.toString());
        RecordingSignIn recordingSignIn = new RecordingSignIn();
        BeanUtils.copyProperties(recordingSignInDto, recordingSignIn);
        PageHelper.startPage(recordingSignInDto.getOffset(), recordingSignInDto.getLimit());
        List<RecordingSignIn> recordingSignInList = recordingSignInMapper.getRecordingSignInByMess(recordingSignIn);
        // 中间值
        PageInfo<RecordingSignIn> recordingSignInPageInfo = new PageInfo<>(recordingSignInList);
        // 结果集
        PageInfo<RecordingSignInVo> recordingSignInVoPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(recordingSignInPageInfo, recordingSignInVoPageInfo);
        // 结果集转换
        List<RecordingSignInVo> recordingSignInVoList = new ArrayList<>();
        recordingSignInList.forEach(recordingSignInTemp -> {
            RecordingSignInVo recordingSignInVo = new RecordingSignInVo();
            BeanUtils.copyProperties(recordingSignInTemp, recordingSignInVo);
            recordingSignInVoList.add(recordingSignInVo);
        });
        recordingSignInVoPageInfo.setList(recordingSignInVoList);
        return recordingSignInVoPageInfo;
    }

    /**
     * 清理所有日志
     */
    public void truncateRecordingSignIn(){
        recordingSignInMapper.truncateRecordingSignIn();
    }
}