package com.js.controller.purview;

import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.dto.purview.PurviewDto;
import com.js.form.purview.AddPurviewForm;
import com.js.form.purview.EditPurviewForm;
import com.js.service.purview.PurviewService;
import com.js.vo.purview.PurviewVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jiangshuang
 * @Description: 节目对应的用户对应权限Mapper
 * @Date: 2020/5/9 19:47
 **/
@RestController
@RequestMapping("/purview")
@Api("用户权限对应Controller")
@Slf4j
public class PurviewController {

    @Autowired
    private PurviewService purviewService;

    @PostMapping("add")
    @ApiOperation(value = "添加用户权限", notes = "添加用户权限")
    @Log(value = "添加用户权限")
    public BaseResponse<String> addPurview(@RequestBody @Validated AddPurviewForm addPurviewForm) {
        log.info("添加用户权限入参{}", addPurviewForm.toString());
        PurviewDto purviewDto = new PurviewDto();
        BeanUtils.copyProperties(addPurviewForm, purviewDto);
        int result = 0;
        try {
            result = purviewService.addPurview(purviewDto);
        } catch (Exception e) {
            throw new SystemException("添加用户权限失败");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "添加用户权限成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "添加用户权限失败");
    }

    @GetMapping("getById")
    @ApiOperation(value = "根据用户id查询操作", notes = "根据用户id查询操作")
    @Log(value = "根据用户id查询操作")
    public BaseResponse<PurviewVo> selectPurviewByUserId(@RequestParam("userId") String userId) {
        PurviewVo purviewVo = null;
        try {
            purviewService.selectPurviewByUserId(userId);
        } catch (Exception e) {
            log.info("查询出现的异常为{}", e);
            throw new SystemException("查询失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), purviewVo);
    }

    /** 修改权限操作 **/
    @PostMapping("edit")
    @ApiOperation(value = "修改权限操作", notes = "修改权限操作")
    @Log(value = "修改权限操作")
    public BaseResponse<String> editPurview(@RequestBody @Validated EditPurviewForm editPurviewForm) {
        log.info("添加用户权限入参{}", editPurviewForm.toString());
        PurviewDto purviewDto = new PurviewDto();
        int result = 0;
        try {
            result = purviewService.editPurview(purviewDto);
        } catch (Exception e) {
            log.info("查询出现的异常为{}", e);
            throw new SystemException("查询失败");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "修改用户权限成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "修改用户权限失败");
    }

}