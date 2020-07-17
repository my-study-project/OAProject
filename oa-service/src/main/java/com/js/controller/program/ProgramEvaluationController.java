package com.js.controller.program;

import com.github.pagehelper.PageInfo;
import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.dto.program.ProgramEvaluationDto;
import com.js.form.program.evaluation.AddProgramEvaluationForm;
import com.js.form.program.evaluation.EditProgramEvaluationForm;
import com.js.form.program.evaluation.ProgramEvaluationForm;
import com.js.service.program.ProgramEvaluationService;
import com.js.vo.program.ProgramEvaluationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 姜爽
 * @Description: 节目评估对应Controller
 * @Date: 2020/5/10 14:15
 */
@RestController
@RequestMapping("/program/evaluation")
@Api("节目评估对应Controller")
@Slf4j
public class ProgramEvaluationController {
    @Autowired
    private ProgramEvaluationService programEvaluationService;

    @PostMapping("getList")
    @ApiOperation(value = "分页查询节目表", notes = "分页查询节目表")
    @Log(value = "分页查询节目评估信息")
    public BaseResponse<PageInfo<ProgramEvaluationVo>>
        getLogMess(@RequestBody @Validated ProgramEvaluationForm programEvaluationForm) {
        log.info("分页查询节目评估Controller的入参为{}", programEvaluationForm.toString());
        ProgramEvaluationDto programEvaluationDto = new ProgramEvaluationDto();
        BeanUtils.copyProperties(programEvaluationForm, programEvaluationDto);
        PageInfo<ProgramEvaluationVo> programEvaluationVoPageInfo = new PageInfo<>();
        try {
            programEvaluationVoPageInfo = programEvaluationService.selectByMess(programEvaluationDto);
        } catch (Exception e) {
            log.info("查询出现的异常为{}", e);
            throw new SystemException("查询失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(),
            programEvaluationVoPageInfo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加节目评估", notes = "添加节目评估")
    @Log(value = "添加节目评估")
    public BaseResponse<String>
        addProgramEval(@RequestBody @Validated AddProgramEvaluationForm addProgramEvaluationForm) {
        log.info("添加节目评估Controller的入参为{}", addProgramEvaluationForm.toString());
        ProgramEvaluationDto programEvaluationDto = new ProgramEvaluationDto();
        BeanUtils.copyProperties(addProgramEvaluationForm, programEvaluationDto);
        int result = 0;
        try {
            result = programEvaluationService.addProgramEvaluation(programEvaluationDto);
        } catch (Exception e) {
            log.info("添加节目评估出现异常{}", e);
            throw new SystemException("添加节目评估时出现异常");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "添加节目评估成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "添加节目评估失败");
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改节目评估", notes = "修改节目评估")
    @Log(value = "修改节目评估")
    public BaseResponse<String>
        editProgramEval(@RequestBody @Validated EditProgramEvaluationForm editProgramEvaluationForm) {
        log.info("修改节目评估Controller的入参为{}", editProgramEvaluationForm.toString());
        ProgramEvaluationDto programEvaluationDto = new ProgramEvaluationDto();
        BeanUtils.copyProperties(editProgramEvaluationForm, programEvaluationDto);
        int result = 0;
        try {
            result = programEvaluationService.editProgramEvaluation(programEvaluationDto);
        } catch (Exception e) {
            log.info("修改节目评估出现异常{}", e);
            throw new SystemException("修改节目评估时出现异常");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "修改节目评估成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "修改节目评估失败");
    }

    @GetMapping("getById")
    @ApiOperation(value = "根据主键获取节目评估的详细信息", notes = "根据主键获取节目评估的详细信息")
    @Log(value = "根据主键查询节目评估的详细信息")
    public BaseResponse<ProgramEvaluationVo> getProgramEvalById(@RequestParam("uuid") String uuid) {
        log.info("根据主键uuid={}获取节目评估的详细信息Controller", uuid);
        ProgramEvaluationVo programEvaluationVo = null;
        try {
            programEvaluationVo = programEvaluationService.selectById(uuid);
        } catch (Exception e) {
            log.info("查询评估出现的异常为{}", e);
            throw new SystemException("查询评估信息失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), programEvaluationVo);
    }
}
