package com.js.controller;

import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.common.util.EncryptUtil;
import com.js.common.util.TokenUtil;
import com.js.dto.system.SysUserDto;
import com.js.enums.system.SysUserEnum;
import com.js.form.system.user.UserPassForm;
import com.js.service.RedisService;
import com.js.service.mail.MailService;
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

    @Autowired
    private MailService mailService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @Log(value = "用户登陆")
    public BaseResponse<SysUserVo> userLogin(@RequestBody UserPassForm userPassForm, HttpServletResponse response) {
        log.info("用户登录的入参为{}",userPassForm.toString());
        if (userPassForm.getPassword() == null){
            throw new SystemException("密码不可为空");
        }
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
            String password = EncryptUtil.shaAndMd5(userPassForm.getPassword());
            if (password.equals(sysUserVo.getPassword())){
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

    @GetMapping("/getCode")
    @ApiOperation(value = "获取邮箱验证码", notes = "获取邮箱验证码")
    @Log(value = "获取邮箱验证码")
    public BaseResponse<String> forgetPass(@RequestHeader("studentNumber") String studentNumber) {
        log.info("获取邮箱验证吗入参为{}",studentNumber);
        if (studentNumber == null || "".equals(studentNumber)){
            throw new SystemException("未知学号");
        }
        //根据用户学号获取邮箱或者手机号
        SysUserVo sysUserVo = sysUserService.getUserById(studentNumber);
        //默认采用邮箱认证，暂不提供手机号认证
        String result = mailService.sendCodeMail(sysUserVo,"1");
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),result);
    }

    @GetMapping("/reCode")
    @ApiOperation(value = "验证码验证修改密码", notes = "验证码验证修改密码")
    @Log(value = "验证码验证修改密码")
    public BaseResponse<String> rePassCode(@RequestBody UserPassForm userPassForm) {
        log.info("验证入参为忘记密码{}",userPassForm.toString());

        if (userPassForm.getMethodCode() == null || "".equals(userPassForm.getMethodCode())){
            throw new SystemException("请输入验证码");
        }
        if(!userPassForm.getPassword().equals(userPassForm.getRepassword())){
            throw new SystemException("两次密码不一致，操作失败");
        }
        String code = redisService.getPassCode(userPassForm.getStudentNumber());
        if (!userPassForm.getMethodCode().equals(code)){
            throw new SystemException("验证码输入错误，请刷新页面重试");
        }

        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setPassword(EncryptUtil.shaAndMd5(sysUserDto.getPassword()));
        sysUserDto.setStudentNumber(userPassForm.getStudentNumber());
        int result = 0;

        //验证学号对应的用户信息
        SysUserVo sysUserVo= null;
        try{
            sysUserVo = sysUserService.getUserById(userPassForm.getStudentNumber());
        }catch (Exception e){
            log.info("查询用户异常");
        }
        if(sysUserVo == null) {
            throw new SystemException("用户不存在,请联系管理员");
        }

        if (SysUserEnum.IS_ALIVE.getCode().equals(sysUserVo.getIsAlive()) || SysUserEnum.INACTIVATED.getCode().equals(sysUserVo.getIsAlive())) {
            result = sysUserService.editSysUser(sysUserDto);
        }else if (SysUserEnum.NOT_ALIVE.getCode().equals(sysUserVo.getIsAlive())){
            throw new SystemException("该账号已被停用，请联系管理人员");
        } else {
            throw new SystemException("账号异常，请联系管理人员");
        }
        if (result > 0){
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),"密码重置成功");
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(),StatusCode.FAIL.getMsg(),"密码重置失败");
    }

}
