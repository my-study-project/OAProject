package com.js.mapper.program;

import com.js.pojo.program.ProgramEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Author: jiangshuang
 * @Description: 节目评估相关操作Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface ProgramEvaluationMapper {

    /**删除操作**/
    int deleteProgramEvaluation(@Param("uuid") String uuid);

    /**添加节目评估**/
    int addProgramEvaluation(ProgramEvaluation programEvaluation);

    /**根据主键查询操作**/
    ProgramEvaluation selectById(@Param("uuid") String uuid);

    /**修改评估操作**/
    int editProgramEvaluation(ProgramEvaluation programEvaluation);

    /**根据条件查询评估**/
    List<ProgramEvaluation> selectByMess(ProgramEvaluation programEvaluation);

}