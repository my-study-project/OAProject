package com.js.service.program;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.exception.SystemException;
import com.js.common.util.IdUtils;
import com.js.dto.program.ProgramEvaluationDto;
import com.js.enums.program.ProgramEnum;
import com.js.mapper.program.ProgramEvaluationMapper;
import com.js.pojo.program.ProgramEvaluation;
import com.js.vo.program.ProgramEvaluationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目评估相关操作Mapper
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class ProgramEvaluationService {

    @Autowired
    private ProgramEvaluationMapper programEvaluationMapper;

    /**删除操作**/
    public int deleteProgramEvaluation(String uuid){
        return programEvaluationMapper.deleteProgramEvaluation(uuid);
    }

    /**添加节目评估**/
    public int addProgramEvaluation(ProgramEvaluationDto programEvaluationDto){
        ProgramEvaluation programEvaluation = new ProgramEvaluation();
        BeanUtils.copyProperties(programEvaluationDto,programEvaluation);
        programEvaluation.setUuid(IdUtils.get32Uuid());
        return programEvaluationMapper.addProgramEvaluation(programEvaluation);
    }

    /**根据主键查询操作**/
    public ProgramEvaluationVo selectById(String uuid) {
        ProgramEvaluation programEvaluation = programEvaluationMapper.selectById(uuid);
        ProgramEvaluationVo programEvaluationVo = new ProgramEvaluationVo();
        BeanUtils.copyProperties(programEvaluation,programEvaluationVo);
        return programEvaluationVo;
    }

    /**修改评估操作**/
    public int editProgramEvaluation(ProgramEvaluationDto programEvaluationDto) {
        ProgramEvaluation programEvaluation = new ProgramEvaluation();
        BeanUtils.copyProperties(programEvaluationDto,programEvaluation);
        return programEvaluationMapper.editProgramEvaluation(programEvaluation);
    }

    /**根据条件查询评估**/
    public PageInfo<ProgramEvaluationVo> selectByMess(ProgramEvaluationDto programEvaluationDto) {
        log.info("查询评估入参为{}",programEvaluationDto.toString());
        ProgramEvaluation programEvaluation = new ProgramEvaluation();
        BeanUtils.copyProperties(programEvaluationDto,programEvaluation);
        PageHelper.startPage(programEvaluationDto.getOffset(),programEvaluationDto.getLimit());
        List<ProgramEvaluation> programEvaluationList = programEvaluationMapper.selectByMess(programEvaluation);
        //中间值
        PageInfo<ProgramEvaluation> programEvaluationPageInfo = new PageInfo<>(programEvaluationList);
        //结果集
        PageInfo<ProgramEvaluationVo> programEvaluationVoPageInfo = null;
        BeanUtils.copyProperties(programEvaluationPageInfo,programEvaluationVoPageInfo);
        List<ProgramEvaluationVo> programEvaluationVoList = new ArrayList<>();
        programEvaluationList.forEach(programEvaluationTemp -> {
            ProgramEvaluationVo programEvaluationVo = new ProgramEvaluationVo();
            BeanUtils.copyProperties(programEvaluationTemp,programEvaluationVo);
            if (ProgramEnum.PEND_REVIEW.getCode().equals(programEvaluationVo.getEvaluationStatus())){
                programEvaluationVo.setEvaluationStatus(ProgramEnum.PEND_REVIEW.getMsg());
            } else if(ProgramEnum.EXAMINATION_PASS.getCode().equals(programEvaluationVo.getEvaluationStatus())) {
                programEvaluationVo.setEvaluationStatus(ProgramEnum.EXAMINATION_PASS.getMsg());
            }else{
                programEvaluationVo.setEvaluationStatus(ProgramEnum.TURN_DOWN.getMsg());
            }

            if (ProgramEnum.LAST_SEMESTER.getCode().equals(programEvaluationVo.getAcademicTerm())){
                programEvaluationVo.setAcademicTerm(ProgramEnum.LAST_SEMESTER.getMsg());
            } else{
                programEvaluationVo.setAcademicTerm(ProgramEnum.NEXT_SEMESTER.getMsg());
            }
            programEvaluationVoList.add(programEvaluationVo);

        });
        programEvaluationVoPageInfo.setList(programEvaluationVoList);
        return programEvaluationVoPageInfo;
    }

}