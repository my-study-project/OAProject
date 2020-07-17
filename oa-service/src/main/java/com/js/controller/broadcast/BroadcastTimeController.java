package com.js.controller.broadcast;

import com.github.pagehelper.PageInfo;
import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.dto.broadcast.BroadcastTimeDto;
import com.js.form.broadcast.time.AddBroadcastTimeForm;
import com.js.form.broadcast.time.BroadcastTimeForm;
import com.js.form.broadcast.time.EditBroadcastTimeForm;
import com.js.service.broadcast.BroadcastTimeService;
import com.js.vo.broadcast.BroadcastTimeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 姜爽
 * @Description: 节目时间范围相关接口Controller
 * @Date: 2020/5/12 14:06
 */
@RestController
@RequestMapping("/broadcast/time")
@Api("节目时间范围相关接口Controller")
@Slf4j
public class BroadcastTimeController {
    @Autowired
    private BroadcastTimeService broadcastTimeService;

    @PostMapping("/add")
    @ApiOperation(value = "添加节目时间范围", notes = "添加节目时间范围")
    @Log(value = "添加节目时间范围")
    public BaseResponse<String> addBroadcastTime(@RequestBody @Validated AddBroadcastTimeForm addBroadcastTimeForm) {
        log.info("添加节目时间范围入参为{}", addBroadcastTimeForm.toString());
        BroadcastTimeDto broadcastTimeDto = new BroadcastTimeDto();
        BeanUtils.copyProperties(addBroadcastTimeForm, broadcastTimeDto);
        if (null == broadcastTimeDto.getPeriod()) {
            broadcastTimeDto.setPeriod("00");
        }
        int result = 0;
        try {
            result = broadcastTimeService.addBroadcastIime(broadcastTimeDto);
        } catch (Exception e) {
            log.info("添加节目时间出现异常{}", e);
            throw new SystemException("添加时出现异常");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "添加成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "添加失败");
    }

    @PostMapping("listAll")
    @ApiOperation(value = "条件查询节目时间范围", notes = "条件查询节目时间范围")
    @Log(value = "条件查询节目时间范围")
    public BaseResponse<PageInfo<BroadcastTimeVo>>
        getBroadcastTimeByMess(@RequestBody @Validated BroadcastTimeForm broadcastTimeForm) {
        log.info("条件查询节目时间范围入参为{}", broadcastTimeForm.toString());
        BroadcastTimeDto broadcastTimeDto = new BroadcastTimeDto();
        BeanUtils.copyProperties(broadcastTimeForm, broadcastTimeDto);
        PageInfo<BroadcastTimeVo> broadcastTimeVoPageInfo = new PageInfo<>();
        try {
            broadcastTimeVoPageInfo = broadcastTimeService.getBroadcastIimeByMess(broadcastTimeDto);
        } catch (Exception e) {
            log.info("查询节目出现异常{}", e);
            throw new SystemException("获取节目数据出现异常");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), broadcastTimeVoPageInfo);
    }

    @GetMapping("getById")
    @ApiOperation(value = "根据主键查询节目时间范围", notes = "根据主键查询节目时间范围")
    @Log(value = "根据主键查询节目时间范围")
    public BaseResponse<BroadcastTimeVo> getBroadcastTimeByUuid(@RequestParam("uuid") String uuid) {
        BroadcastTimeVo broadcastTimeVo = new BroadcastTimeVo();
        try {
            broadcastTimeVo = broadcastTimeService.getBroadcastIimeById(uuid);
        } catch (Exception e) {
            log.info("id为{}查询小组出现异常{}", uuid, e);
            throw new SystemException("获取小组数据出现异常");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), broadcastTimeVo);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改节目时间范围", notes = "修改节目时间范围")
    @Log(value = "修改节目时间范围")
    public BaseResponse<String> editBroadcastTime(@RequestBody @Validated EditBroadcastTimeForm editBroadcastTimeForm) {
        log.info("修改节目时间范围入参为{}", editBroadcastTimeForm.toString());
        BroadcastTimeDto broadcastTimeDto = new BroadcastTimeDto();
        BeanUtils.copyProperties(editBroadcastTimeForm, broadcastTimeDto);
        // 判空赋值
        if (null == broadcastTimeDto.getPeriod()) {
            broadcastTimeDto.setPeriod("00");
        }
        int result = 0;
        try {
            result = broadcastTimeService.editBroadcastIime(broadcastTimeDto);
        } catch (Exception e) {
            log.info("修改节目时间出现异常{}", e);
            throw new SystemException("修改时出现异常");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "修改成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "修改失败");
    }

    @GetMapping("getSign")
    @ApiOperation(value = "条件查询所需要签到的节目", notes = "条件查询所需要签到的节目")
    @Log(value = "条件查询所需要签到的节目")
    public BaseResponse<List<BroadcastTimeVo>> getBroadcastTimeByDate() {
        BroadcastTimeDto broadcastTimeDto = new BroadcastTimeDto();
        List<BroadcastTimeVo> broadcastTimeVoList = new ArrayList<>();
        try {
            broadcastTimeVoList = broadcastTimeService.getBroadcastIimeByDate(broadcastTimeDto);
        } catch (Exception e) {
            log.info("查询所需要签到的节目出现异常{}", e);
            throw new SystemException("获取所需要签到的节目出现异常");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), broadcastTimeVoList);
    }
}
