package com.js.controller.system;

import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.dto.system.SysConfigDto;
import com.js.form.system.config.EditSysConfigForm;
import com.js.form.system.config.SysConfigForm;
import com.js.service.system.SysConfigService;
import com.js.vo.system.SysConfigVo;
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

import java.util.List;

/**
 * @program: OAProject
 * @Date: 2020/7/17 11:39
 * @Author: jiangshuang
 * @Description:系统配置相关操作
 */
@RestController
@RequestMapping("/system/config")
@Api("系统配置相关操作Controller")
@Slf4j
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @PostMapping("edit")
    @ApiOperation(value = "修改系统配置", notes = "修改系统配置")
    @Log(value = "修改系统配置")
    public BaseResponse<String> editConfig(@RequestBody @Validated EditSysConfigForm editSysConfigForm) {
        log.info("修改系统配置入参{}", editSysConfigForm.toString());
        SysConfigDto sysConfigDto = new SysConfigDto();
        BeanUtils.copyProperties(editSysConfigForm,sysConfigDto);
        int result = 0;
        try {
            result = sysConfigService.editSysConfig(sysConfigDto);
        } catch (Exception e) {
            log.info("修改系统配置出现的异常为{}", e);
            throw new SystemException("修改系统配置失败");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "修改系统配置成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "修改系统配置失败");
    }

    @GetMapping("getConfigById")
    @ApiOperation(value = "根据主键获取系统配置", notes = "根据主键获取系统配置")
    @Log(value = "根据主键获取系统配置")
    public BaseResponse<SysConfigVo> getConfig(@RequestParam("uuid") String uuid) {
        log.info("根据主键获取系统配置入参{}", uuid);
        SysConfigVo sysConfigVo = null;
        try {
            sysConfigVo = sysConfigService.getSysConfigById(uuid);
        } catch (Exception e) {
            log.info("获取系统配置出现的异常为{}", e);
            throw new SystemException("获取系统配置失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), sysConfigVo);
    }

    @PostMapping("getConfigByMess")
    @ApiOperation(value = "根据条件获取系统配置", notes = "根据条件获取系统配置")
    @Log(value = "根据条件获取系统配置")
    public BaseResponse<List<SysConfigVo>> getConfigByMess(@RequestBody SysConfigForm sysConfigForm) {
        log.info("根据条件获取系统配置入参{}", sysConfigForm.toString());
        SysConfigDto sysConfigDto = new SysConfigDto();
        BeanUtils.copyProperties(sysConfigForm,sysConfigDto);
        List<SysConfigVo> sysConfigVoList = null;
        try {
            sysConfigVoList = sysConfigService.getSysConfigByMess(sysConfigDto);
        } catch (Exception e) {
            log.info("根据条件获取系统配置出现的异常为{}", e);
            throw new SystemException("获取系统配置失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), sysConfigVoList);
    }
}
