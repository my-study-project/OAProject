package com.js.mapper.program;

import com.js.pojo.program.ProgramFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目相应时间的对应文件存储Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface ProgramFileMapper {
    /** 删除操作 **/
    int deleteProgramFile(String uuid);

    /** 添加节目文件操作 **/
    int addProgramFile(ProgramFile programFile);

    /** 根据条件查询操作 **/
    List<ProgramFile> getProgramFileByMess(ProgramFile programFile);

    /** 删除操作 **/
    int editProgramFile(ProgramFile programFile);

}