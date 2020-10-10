package com.js.controller.program;

import com.github.pagehelper.PageInfo;
import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.dto.program.ProgramDto;
import com.js.form.program.message.AddProgramForm;
import com.js.form.program.message.EditProgramForm;
import com.js.form.program.message.ProgramForm;
import com.js.service.program.ProgramService;
import com.js.vo.program.ProgramVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 姜爽
 * @Description: 节目表对应Controller
 * @Date: 2020/5/10 14:15
 */
@RestController
@RequestMapping("/program")
@Api("节目表对应Controller")
@Slf4j
public class ProgramController {
    @Autowired
    private ProgramService programService;

    @PostMapping("getList")
    @ApiOperation(value = "分页查询节目", notes = "分页查询节目")
    @Log(value = "分页查询节目")
    public BaseResponse<PageInfo<ProgramVo>> getLogMess(@RequestBody @Validated ProgramForm programForm) {
        log.info("分页查询节目Controller的入参为{}", programForm.toString());
        ProgramDto programDto = new ProgramDto();
        BeanUtils.copyProperties(programForm, programDto);
        PageInfo<ProgramVo> programVoPageInfo = new PageInfo<>();
        try {
            programVoPageInfo = programService.getAllProgram(programDto);
        } catch (Exception e) {
            log.info("查询出现的异常为{}", e);
            throw new SystemException("查询失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), programVoPageInfo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加节目表", notes = "添加节目表")
    @Log(value = "添加节目表")
    public BaseResponse<String> getLogMess(@RequestBody @Validated AddProgramForm addProgramForm) {
        log.info("添加节目表Controller的入参为{}", addProgramForm.toString());
        ProgramDto programDto = new ProgramDto();
        BeanUtils.copyProperties(addProgramForm, programDto);
        int result = 0;
        try {
            result = programService.addProgram(programDto);
        } catch (Exception e) {
            log.info("添加节目出现异常{}", e);
            throw new SystemException("添加节目时出现异常");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "添加节目成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "添加节目失败");
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改节目表", notes = "修改节目表")
    @Log(value = "修改节目表")
    public BaseResponse<String> editProgram(@RequestBody @Validated EditProgramForm editProgramForm) {
        log.info("修改节目表Controller的入参为{}", editProgramForm.toString());
        ProgramDto programDto = new ProgramDto();
        BeanUtils.copyProperties(editProgramForm, programDto);
        int result = 0;
        try {
            result = programService.editProgram(programDto);
        } catch (Exception e) {
            log.info("修改节目出现异常{}", e);
            throw new SystemException("修改节目时出现异常");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "修改节目成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "修改节目失败");
    }

    @GetMapping("getById")
    @ApiOperation(value = "根据主键获取节目的详细信息", notes = "根据主键获取节目的详细信息")
    @Log(value = "根据主键获取节目的详细信息")
    public BaseResponse<ProgramVo> getLogMess(@RequestParam("uuid") String uuid) {
        log.info("根据主键uuid={}获取节目的详细信息Controller", uuid);
        ProgramVo programVo = null;
        try {
            programVo = programService.getProgramByUuid(uuid);
        } catch (Exception e) {
            log.info("查询出现的异常为{}", e);
            throw new SystemException("查询失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), programVo);
    }
}
