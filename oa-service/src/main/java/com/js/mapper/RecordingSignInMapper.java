package com.js.mapper;

import com.js.pojo.RecordingSignIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordingSignInMapper {

    int addRecordingSignIn(RecordingSignIn recordingSignIn);

    List<RecordingSignIn> getRecordingSignInByMess(RecordingSignIn recordingSignIn);
}