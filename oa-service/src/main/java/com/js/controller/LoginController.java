package com.js.controller;

import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.common.util.Md5Util;
import com.js.common.util.TokenUtil;
import com.js.dto.system.SysUserDto;
import com.js.enums.system.SysUserEnum;
import com.js.form.system.user.UserPassForm;
import com.js.service.RedisService;
import com.js.service.system.SysUserService;
import com.js.vo.system.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 姜爽
 * @Description: 用户登录注销等基础功能
 * @Date: 2020/5/11 9:57
 */
@RestController
@RequestMapping("/userLogin")
@Api("用户登录注销等基础功能Controller")
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @Log(value = "用户登陆")
    public BaseResponse<SysUserVo> userLogin(@RequestBody UserPassForm userPassForm, HttpServletResponse response) {
        log.info("用户登录的入参为{}",userPassForm.toString());
        SysUserDto sysUserDto = new SysUserDto();
        BeanUtils.copyProperties(userPassForm,sysUserDto);
        //根据用户名查询用户信息
        SysUserVo sysUserVo= null;
        try{
            sysUserVo = sysUserService.getUserById(userPassForm.getStudentNumber());
        }catch (Exception e){
            log.info("查询用户时出现异常");
        }
        if(sysUserVo == null) {
            throw new SystemException("用户不存在,请联系管理人员");
        }
        if (SysUserEnum.IS_ALIVE.getCode().equals(sysUserVo.getIsAlive()) || SysUserEnum.INACTIVATED.getCode().equals(sysUserVo.getIsAlive())) {
            String password = Md5Util.convertMd5(sysUserVo.getPassword());
            if (userPassForm.getPassword().equals(password)){
//                首先生成token
                String token = TokenUtil.sign(sysUserVo.getStudentNumber(),sysUserVo.getName());
                redisService.login(sysUserVo.getStudentNumber(),token);
                response.setHeader("Token",token);
                return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),sysUserVo);
            }else {
                throw new SystemException("用户名或者密码不正确");
            }

        }else if (SysUserEnum.NOT_ALIVE.getCode().equals(sysUserVo.getIsAlive())){
            throw new SystemException("该账号已被停用，请联系管理人员");
        } else{
            throw new SystemException("账号异常，请联系管理人员");
        }
    }

    @GetMapping("/logOut")
    @ApiOperation(value = "用户注销", notes = "用户注销")
    @Log(value = "用户注销")
    public BaseResponse<String> userLogOut(@RequestHeader("studentNumber") String studentNumber) {
        log.info("用户注销的入参为{}",studentNumber);
        Boolean flag = false;
        try{
            flag = redisService.logOut(studentNumber);
        }catch (Exception e){
            log.info("查询用户时出现异常");
        }
        if (Boolean.TRUE.toString().equals(flag.toString())) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),"用户已退出登录");
        }else if(Boolean.FALSE.toString().equals(flag.toString())){
            throw new SystemException("用户注销失败，请重试");
        }else{
            throw new SystemException("系统异常");
        }
    }
}
