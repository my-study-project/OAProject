package com.js.mapper.program;

import com.js.pojo.program.Program;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProgramMapper {

    int addProgram(Program program);

    Program getProgramByUuid(@Param("uuid") String uuid);

    int editProgram(Program program);

    List<Program> getAllProgram(Program program);

}