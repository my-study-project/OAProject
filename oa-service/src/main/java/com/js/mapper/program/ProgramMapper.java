package com.js.mapper.program;

import com.js.pojo.program.Program;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目表相关操作Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface ProgramMapper {

    /** 添加操作 **/
    int addProgram(Program program);

    /** 根据主键查询操作 **/
    Program getProgramByUuid(@Param("uuid") String uuid);

    /** 修改操作 **/
    int editProgram(Program program);

    /** 根据条件查询节目 **/
    List<Program> getAllProgram(Program program);

}