package com.js.service.program;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.exception.SystemException;
import com.js.common.util.IdUtils;
import com.js.dto.program.ProgramDto;
import com.js.enums.program.ProgramEnum;
import com.js.mapper.program.ProgramMapper;
import com.js.pojo.program.Program;
import com.js.vo.program.ProgramVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目表相关操作Service
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class ProgramService {

    @Autowired
    private ProgramMapper programMapper;

    /**添加操作**/
    public int addProgram(ProgramDto programDto) {
        log.info("添加节目操作的入参为{}",programDto.toString());
        Program program = new Program();
        BeanUtils.copyProperties(programDto,program);
        program.setUuid(IdUtils.get32Uuid());
        return programMapper.addProgram(program);
    }

    /**根据主键查询操作**/
    public ProgramVo getProgramByUuid(String uuid) {
        log.info("根据主键查询节目清单的uuid={}",uuid);
        ProgramVo programVo = new ProgramVo();
        Program program = programMapper.getProgramByUuid(uuid);
        BeanUtils.copyProperties(program,programVo);
        return programVo;
    }

    /**修改操作**/
    public int editProgram(ProgramDto programDto) {
        Program program = new Program();
        BeanUtils.copyProperties(programDto,program);
        return programMapper.editProgram(program);
    }

    /**根据条件查询节目**/
    public PageInfo<ProgramVo> getAllProgram(ProgramDto programDto) {
        log.info("条件查询节目入参={}",programDto.toString());
        PageHelper.startPage(programDto.getOffset(),programDto.getLimit());
        //封装返回数据
        List<ProgramVo> programVoList = new ArrayList<>();
        Program program = new Program();
        BeanUtils.copyProperties(programDto,program);
        List<Program> programList = programMapper.getAllProgram(program);
        //中间量
        PageInfo<Program> programPageInfo = new PageInfo<>(programList);
        //结果集
        PageInfo<ProgramVo> programVoPageInfo = null;
        BeanUtils.copyProperties(programPageInfo,programVoPageInfo);
        programList.forEach(programTemp -> {
            ProgramVo programVo = new ProgramVo();
            BeanUtils.copyProperties(programTemp,programVo);
            if (ProgramEnum.IS_ALIVE.getCode().equals(programVo.getIsAlive())){
                programVo.setIsAlive(ProgramEnum.IS_ALIVE.getMsg());
            } else {
                programVo.setIsAlive(ProgramEnum.NOT_ALIVE.getMsg());
            }
            if (ProgramEnum.IS_EVALUATE.getCode().equals(programVo.getNeedEvaluate())){
                programVo.setNeedEvaluate(ProgramEnum.IS_EVALUATE.getMsg());
            } else {
                programVo.setNeedEvaluate(ProgramEnum.NOT_EVALUATE.getMsg());
            }
            programVoList.add(programVo);
        });
        programVoPageInfo.setList(programVoList);
        return programVoPageInfo;
    }

}