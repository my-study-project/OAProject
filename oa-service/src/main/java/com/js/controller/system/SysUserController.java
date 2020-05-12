package com.js.controller.system;

import com.github.pagehelper.PageInfo;
import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.common.util.Md5Util;
import com.js.dto.system.SysUserDto;
import com.js.enums.system.SysUserEnum;
import com.js.form.system.user.AddSysUserForm;
import com.js.form.system.user.EditSysUserForm;
import com.js.form.system.user.SysUserForm;
import com.js.form.system.user.UserPassForm;
import com.js.service.system.SysUserService;
import com.js.vo.system.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 姜爽
 * @Description: 用户操作对应Controller
 * @Date: 2020/5/10 14:15
 */
@RestController
@RequestMapping("/system/user")
@Api("用户操作对应Controller")
@Slf4j
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("getList")
    @ApiOperation(value = "分页查询所有用户", notes = "分页查询所有用户")
    @Log(value = "分页查询所有用户")
    public BaseResponse<PageInfo<SysUserVo>> getUserMess(@RequestBody SysUserForm sysUserForm) {
        log.info("分页查询所有用户Controller的入参为{}",sysUserForm.toString());
        SysUserDto sysUserDto = new SysUserDto();
        BeanUtils.copyProperties(sysUserForm,sysUserDto);
        PageInfo<SysUserVo> sysUserVoPageInfo = new PageInfo<>();
        try{
            sysUserVoPageInfo = sysUserService.getUserAllList(sysUserDto);
        }catch (Exception e) {
            log.info("查询用户出现的异常为{}",e);
            throw new SystemException("查询失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),sysUserVoPageInfo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @Log(value = "添加用户")
    public BaseResponse<String> addUserMess(@RequestBody AddSysUserForm addSysUserForm) {
        log.info("添加用户Controller的入参为{}",addSysUserForm.toString());
        SysUserDto sysUserDto = new SysUserDto();
        BeanUtils.copyProperties(addSysUserForm,sysUserDto);
        int result = 0;
        try{
            result = sysUserService.addSysUser(sysUserDto);
        }catch (Exception e) {
            log.info("添加用户出现异常{}",e);
            throw new SystemException("添加用户时出现异常");
        }
        if (result > 0){
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),"添加用户成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(),StatusCode.FAIL.getMsg(),"添加用户失败");
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @Log(value = "修改用户信息")
    public BaseResponse<String> editUserMess(@RequestBody EditSysUserForm editSysUserForm) {
        log.info("修改用户信息Controller的入参为{}",editSysUserForm.toString());
        SysUserDto sysUserDto = new SysUserDto();
        BeanUtils.copyProperties(editSysUserForm,sysUserDto);
        int result = 0;
        try{
            result = sysUserService.editSysUser(sysUserDto);
        }catch (Exception e) {
            log.info("修改出现用户信息异常{}",e);
            throw new SystemException("修改用户信息时出现异常");
        }
        if (result > 0){
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),"修改用户信息成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(),StatusCode.FAIL.getMsg(),"修改失败用户信息");
    }

    @PostMapping("/editPass")
    @ApiOperation(value = "用户修改密码/激活账号", notes = "用户修改密码/激活账号")
    @Log(value = "用户修改密码/激活账号")
    public BaseResponse<String> editPass(@RequestBody UserPassForm userPassForm) {
        log.info("修改用户密码Controller的入参为{}",userPassForm.toString());
        if(!userPassForm.getPassword().equals(userPassForm.getRepassword())){
            throw new SystemException("两次密码不一致，操作失败");
        }
        SysUserDto sysUserDto = new SysUserDto();
        BeanUtils.copyProperties(userPassForm,sysUserDto);
        sysUserDto.setPassword(Md5Util.stringToMd5(sysUserDto.getPassword()));
        int result = 0;

        //验证学号对应的用户信息
        SysUserVo sysUserVo= null;
        try{
            sysUserVo = sysUserService.getUserById(userPassForm.getStudentNumber());
        }catch (Exception e){
            log.info("查询用户出现异常");
        }
        if(sysUserVo == null) {
            throw new SystemException("用户不存在,请联系管理人员");
        }

        if (SysUserEnum.IS_ALIVE.getCode().equals(sysUserVo.getIsAlive()) || SysUserEnum.INACTIVATED.getCode().equals(sysUserVo.getIsAlive())) {
            sysUserDto.setIsAlive(SysUserEnum.IS_ALIVE.getCode());
            result = sysUserService.editSysUser(sysUserDto);
        }else if (SysUserEnum.NOT_ALIVE.getCode().equals(sysUserVo.getIsAlive())){
            throw new SystemException("该账号已被停用，请联系管理人员");
        } else {
            throw new SystemException("账号异常，请联系管理人员");
        }
        if (result > 0){
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),"重置密码成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(),StatusCode.FAIL.getMsg(),"重置密码失败");
    }

    @GetMapping("getById")
    @ApiOperation(value = "根据用户的详细信息", notes = "根据用户的详细信息")
    @Log(value = "根据用户的详细信息")
    public BaseResponse<SysUserVo> getUserMessById(@RequestParam("studentNumber") String studentNumber) {
        log.info("获取学号为{}的详细信息Controller",studentNumber);
        SysUserVo programVo = new SysUserVo();
        try{
            programVo = sysUserService.getUserById(studentNumber);
        }catch (Exception e) {
            log.info("查询出现的异常为{}",e);
            throw new SystemException("查询用户信息失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),programVo);
    }
}
