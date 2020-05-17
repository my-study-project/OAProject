package com.js.mapper.record;

import com.js.pojo.record.RecordingSignIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @Author: jiangshuang
 * @Description: 节目对应的播出时间详录音室签到操作相关Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface RecordingSignInMapper {

    /**添加操作**/
    int addRecordingSignIn(RecordingSignIn recordingSignIn);

    /**操作条件查询**/
    List<RecordingSignIn> getRecordingSignInByMess(RecordingSignIn recordingSignIn);
}