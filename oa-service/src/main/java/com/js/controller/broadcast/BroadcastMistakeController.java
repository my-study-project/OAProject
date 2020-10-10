package com.js.controller.broadcast;

import com.github.pagehelper.PageInfo;
import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.common.util.excel.MistakeExcelUtil;
import com.js.dto.broadcast.BroadcastMistakeDto;
import com.js.form.broadcast.mistake.AddBroadcastMistakeForm;
import com.js.form.broadcast.mistake.BroadcastMistakeForm;
import com.js.form.broadcast.mistake.EditBroadcastMistakeForm;
import com.js.service.broadcast.BroadcastMistakeService;
import com.js.service.system.CommonSysConfigService;
import com.js.vo.broadcast.BroadcastMistakeExport;
import com.js.vo.broadcast.BroadcastMistakeVo;
import com.js.vo.system.SysConfigCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 节目错误相关操作Controller
 * @Date: 2020/5/9 19:47
 **/
@RestController
@RequestMapping("/broadcast/mistake")
@Api("放音错误相关接口Controller")
@Slf4j
public class BroadcastMistakeController {

    @Autowired
    private BroadcastMistakeService broadcastMistakeService;

    @Autowired
    private CommonSysConfigService commonSysConfigService;
    @GetMapping("/delete")
    @ApiOperation(value = "删除放音错误操作", notes = "删除放音错误操作")
    @Log(value = "删除放音错误操作")
    public BaseResponse<String> deleteBroadcastMistake(@RequestParam("uuid") String uuid) {
        log.info("删除放音错误操作人参为{}", uuid);
        int result = 0;
        try {
            result = broadcastMistakeService.deleteBroadcastMistake(uuid);
        } catch (Exception e) {
            log.info("用户删除放音失败{}", e);
            throw new SystemException("用户删除放音失败");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "删除成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "删除失败");
    }

    /** 添加 **/
    @PostMapping("/add")
    @ApiOperation(value = "添加放音错误操作", notes = "添加放音错误操作")
    @Log(value = "添加放音错误操作")
    public BaseResponse<String> addBroadcastMistake(@RequestBody AddBroadcastMistakeForm addBroadcastMistakeForm) {
        log.info("添加放音错误操作人参为{}", addBroadcastMistakeForm.toString());
        BroadcastMistakeDto broadcastMistakeDto = new BroadcastMistakeDto();
        BeanUtils.copyProperties(addBroadcastMistakeForm, broadcastMistakeDto);
        int result = 0;
        try {
            result = broadcastMistakeService.addBroadcastMistake(broadcastMistakeDto);
        } catch (Exception e) {
            log.info("添加放音错误操作失败{}", e);
            throw new SystemException("添加放音错误操作失败");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "添加错误成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "添加错误失败");
    }

    @GetMapping("/getById")
    @ApiOperation(value = "根据主键查询放音错误操作", notes = "根据主键查询放音错误操作")
    @Log(value = "根据主键查询放音错误操作")
    public BaseResponse<BroadcastMistakeVo> getBroadcastMistakeById(@RequestParam("uuid") String uuid) {
        BroadcastMistakeVo broadcastMistakeVo = null;
        try {
            broadcastMistakeVo = broadcastMistakeService.getBroadcastMistakeById(uuid);
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), broadcastMistakeVo);
        } catch (Exception e) {
            log.info("根据主键查询失败{}", e);
            throw new SystemException("根据主键查询失败");
        }
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改放音错误操作", notes = "修改放音错误操作")
    @Log(value = "修改放音错误操作")
    public BaseResponse<String> editBroadcastMistake(@RequestBody EditBroadcastMistakeForm editBroadcastMistakeForm) {
        log.info("修改放音错误操作人参为{}", editBroadcastMistakeForm.toString());
        BroadcastMistakeDto broadcastMistakeDto = new BroadcastMistakeDto();
        BeanUtils.copyProperties(editBroadcastMistakeForm, broadcastMistakeDto);
        int result = 0;
        try {
            result = broadcastMistakeService.editBroadcastMistake(broadcastMistakeDto);
        } catch (Exception e) {
            log.info("修改放音错误操作失败{}", e);
            throw new SystemException("修改放音错误操作失败");
        }
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), "修改错误成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg(), "修改错误失败");
    }

    /** 根据条件查询操作 **/
    @PostMapping("/list")
    @ApiOperation(value = "根据条件查询操作放音错误操作", notes = "根据条件查询操作放音错误操作")
    @Log(value = "根据条件查询操作放音错误操作")
    public BaseResponse<PageInfo<BroadcastMistakeVo>>
        getBroadcastMistakeByMess(@RequestBody BroadcastMistakeForm broadcastMistakeForm) {
        log.info("根据条件查询操作放音错误操作入参为{}", broadcastMistakeForm.toString());
        BroadcastMistakeDto broadcastMistakeDto = new BroadcastMistakeDto();
        BeanUtils.copyProperties(broadcastMistakeForm, broadcastMistakeDto);
        PageInfo<BroadcastMistakeVo> pageInfo = new PageInfo<>();
        try {
            pageInfo = broadcastMistakeService.getBroadcastMistakeByMess(broadcastMistakeDto);
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), pageInfo);
        } catch (Exception e) {
            log.info("条件查询操作失败{}", e);
            throw new SystemException("条件查询操作失败");
        }
    }

    /** 根据条件导出错误操作 **/
    @PostMapping("/exportMistake")
    @ApiOperation(value = "根据条件导出错误操作", notes = "根据条件导出错误操作")
    @Log(value = "根据条件导出错误操作")
    public void importMistake(@RequestBody BroadcastMistakeForm broadcastMistakeForm, HttpServletResponse response) {
        log.info("根据条件查询操作放音错误操作入参为{}", broadcastMistakeForm.toString());
        BroadcastMistakeDto broadcastMistakeDto = new BroadcastMistakeDto();
        if (null == broadcastMistakeForm.getTeachingWeek()) {
            throw new SystemException("为避免数据过大，请选择教学周");
        }
        //导出年份由后台限制
        SysConfigCommon sysConfigCommon = commonSysConfigService.getSysConfig();
        BeanUtils.copyProperties(broadcastMistakeForm, broadcastMistakeDto);
        broadcastMistakeDto.setAcademicYear(sysConfigCommon.getAcademicYear());
        broadcastMistakeDto.setAcademicTerm(sysConfigCommon.getAcademicTerm());
        try {
            //查询获取数据集
            List<BroadcastMistakeExport> broadcastMistakeExportList= broadcastMistakeService.exportMistake(broadcastMistakeDto);

            //结果集的excel封装
            MistakeExcelUtil.export(response,broadcastMistakeExportList,sysConfigCommon,broadcastMistakeForm);
        } catch (Exception e) {
            log.info("错误查询操作失败{}", e);
            throw new SystemException("错误查询操作失败");
        }
    }

}