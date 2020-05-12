package com.js.controller.broadcast;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.annotation.Log;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.common.util.IdUtils;
import com.js.dto.broadcast.BroadcastSignInDto;
import com.js.dto.system.SysConfigDto;
import com.js.mapper.broadcast.BroadcastSignInMapper;
import com.js.pojo.broadcast.BroadcastSignIn;
import com.js.service.broadcast.BroadcastSignInService;
import com.js.service.system.SysConfigService;
import com.js.vo.broadcast.BroadcastSignInVo;
import com.js.vo.system.SysConfigVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    /**添加签到操作**/
    @PostMapping("/add")
    @ApiOperation(value = "添加签到操作", notes = "添加签到操作")
    @Log(value = "添加签到操作")
    public BaseResponse<String> addBroadcastSignIn(BroadcastSignInDto broadcastSignInDto) {
        return null;
    }

    /**根据条件查询查询签到操作**/
    @PostMapping("/getList")
    @ApiOperation(value = "根据条件查询查询签到操作", notes = "根据条件查询查询签到操作")
    @Log(value = "根据条件查询查询签到操作")
    public BaseResponse<PageInfo<BroadcastSignInVo>> getBroadcastSignInByMess(BroadcastSignInDto broadcastSignInDto) {
        return null;
    }

}