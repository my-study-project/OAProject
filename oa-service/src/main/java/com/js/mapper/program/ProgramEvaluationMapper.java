package com.js.mapper.program;

import com.js.pojo.program.ProgramEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProgramEvaluationMapper {

    int deleteProgramEvaluation(@Param("uuid") String uuid);

    int addProgramEvaluation(ProgramEvaluation programEvaluation);

    ProgramEvaluation selectById(@Param("uuid") String uuid);

    int editProgramEvaluation(ProgramEvaluation programEvaluation);

    List<ProgramEvaluation> selectByMess(ProgramEvaluation programEvaluation);

}