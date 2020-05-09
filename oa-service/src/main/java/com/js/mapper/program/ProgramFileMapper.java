package com.js.mapper.program;

import com.js.pojo.program.ProgramFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProgramFileMapper {
    int deleteProgramFile(String uuid);

    int addProgramFile(ProgramFile programFile);

    List<ProgramFile> getProgramFileByMess(ProgramFile programFile);

    int updateByPrimaryKeySelective(ProgramFile record);

}