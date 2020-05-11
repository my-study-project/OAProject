package com.js.controller.group;

import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.dto.group.GroupDto;
import com.js.form.group.AddGroupForm;
import com.js.form.group.EditGroupForm;
import com.js.form.group.GroupForm;
import com.js.service.group.GroupService;
import com.js.vo.group.GroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 小组相关Service
 * @Date: 2020/5/9 19:47
 **/
@RestController
@Slf4j
@Api("小组相关操作Controller")
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("add")
    @ApiOperation(value = "添加组别", notes = "添加组别")
    @Log(value = "添加组别")
    public BaseResponse<String> addGroup(@RequestBody AddGroupForm addGroupForm){
        log.info("添加小组入参为{}",addGroupForm.toString());
        GroupDto groupDto = new GroupDto();
        BeanUtils.copyProperties(addGroupForm,groupDto);
        int result = 0;
        try{
            result = groupService.addGroup(groupDto);
        }catch (Exception e) {
            log.info("添加小组出现异常{}",e);
            throw new SystemException("添加时出现异常");
        }
        if (result  > 0){
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),"添加成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(),StatusCode.FAIL.getMsg(),"添加失败");
    }

    @PostMapping("list")
    @ApiOperation(value = "根据条件查询组别", notes = "根据条件查询组别")
    @Log(value = "条件查询小组信息")
    public BaseResponse<List<GroupVo>> getGroupByMess(@RequestBody GroupForm groupForm) {
        log.info("查询小组信息的入参为{}",groupForm.toString());
        GroupDto groupDto = new GroupDto();
        BeanUtils.copyProperties(groupForm,groupDto);
        List<GroupVo> groupVoList = new ArrayList<>();
        try{
            groupVoList = groupService.getGroupByMess(groupDto);
        }catch (Exception e){
            log.info("查询小组出现异常{}",e);
            throw new SystemException("获取小组数据出现异常");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),groupVoList);
    }

    @GetMapping("getById")
    @ApiOperation(value = "根据主键查询操作", notes = "根据主键查询操作")
    @Log(value = "根据主键查询小组信息操作")
    public BaseResponse<GroupVo> getGroupByUuid(@RequestParam("uuid") String uuid) {
        GroupVo groupVo = new GroupVo();
        try{
            groupVo = groupService.getGroupByUuid(uuid);
        }catch (Exception e){
            log.info("id为{}查询小组出现异常{}",uuid,e);
            throw new SystemException("获取小组数据出现异常");
        }
        if (groupVo == null) {
            throw new SystemException("查询此条信息失败，请刷新页面后重试");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),groupVo);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改操作", notes = "修改操作")
    @Log(value = "修改组别操作")
    public BaseResponse<String> addGroup(@RequestBody EditGroupForm editGroupForm){
        log.info("修改小组入参为{}",editGroupForm.toString());
        GroupDto groupDto = new GroupDto();
        BeanUtils.copyProperties(editGroupForm,groupDto);
        int result = 0;
        try{
            result = groupService.editGroup(groupDto);
        }catch (Exception e) {
            log.info("修改小组出现异常{}",e);
            throw new SystemException("修改时出现异常");
        }
        if (result  > 0){
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),"修改成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(),StatusCode.FAIL.getMsg(),"修改失败");
    }
}