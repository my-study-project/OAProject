package com.js.controller.broadcast;

import com.github.pagehelper.PageInfo;
import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.dto.broadcast.BroadcastSignInDto;
import com.js.form.broadcast.sign.AddBroadcastSignInForm;
import com.js.form.broadcast.sign.BroadcastSignInForm;
import com.js.service.broadcast.BroadcastSignInService;
import com.js.vo.broadcast.BroadcastSignInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jiangshuang
 * @Description: 签到相关操作Controller
 * @Date: 2020/5/9 19:47
 **/
@RestController
@RequestMapping("/broadcast/sign")
@Api("签到相关接口Controller")
@Slf4j
public class BroadcastSignInController {

    @Autowired
    private BroadcastSignInService broadcastSignInService;

    @PostMapping("/add")
    @ApiOperation(value = "添加签到操作", notes = "添加签到操作")
    @Log(value = "添加签到操作")
    public BaseResponse<String> addBroadcastSignIn(
        @RequestBody @Validated AddBroadcastSignInForm addBroadcastSignInForm,
        @RequestHeader("studentNumber") String studentNumber, @RequestHeader("name") String name) {
        log.info("添加签到操作的入参为{},签到人学号为{}，姓名为{}", addBroadcastSignInForm, studentNumber, name);
        BroadcastSignInDto broadcastSignInDto = new BroadcastSignInDto();
        BeanUtils.copyProperties(addBroadcastSignInForm, broadcastSignInDto);
        broadcastSignInDto.setUserId(studentNumber + name);
        int result = 0;
        try {
            result = broadcastSignInService.addBroadcastSignIn(broadcastSignInDto);
        } catch (Exception e) {
            log.info("用户签到失败{}", e);
            throw new SystemException("用户签到失败");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "签到成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "签到失败");
    }

    @PostMapping("/getList")
    @ApiOperation(value = "根据条件查询查询签到操作", notes = "根据条件查询查询签到操作")
    @Log(value = "根据条件查询查询签到操作")
    public BaseResponse<PageInfo<BroadcastSignInVo>>
        getBroadcastSignInByMess(@RequestBody @Validated BroadcastSignInForm broadcastSignInForm) {
        log.info("根据条件查询查询签到操作入参为{}", broadcastSignInForm.toString());
        BroadcastSignInDto broadcastSignInDto = new BroadcastSignInDto();
        BeanUtils.copyProperties(broadcastSignInForm, broadcastSignInDto);
        try {
            PageInfo<BroadcastSignInVo> broadcastSignInVoPageInfo =
                broadcastSignInService.getBroadcastSignInByMess(broadcastSignInDto);
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(),
                broadcastSignInVoPageInfo);
        } catch (Exception e) {
            log.info("用户签到失败{}", e);
            throw new SystemException("用户签到失败");
        }
    }

}