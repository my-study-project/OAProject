package com.js.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.exception.SystemException;
import com.js.dto.system.SysUserDto;
import com.js.enums.system.SysUserEnum;
import com.js.mapper.purview.PurviewMapper;
import com.js.mapper.system.SysUserMapper;
import com.js.pojo.system.SysUser;
import com.js.vo.system.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 用户表相关Service
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PurviewMapper purviewMapper;

    /**添加用户操作**/
    public int addSysUser(SysUserDto sysUserDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDto,sysUser);
        return sysUserMapper.addSysUser(sysUser);
    }

    /**根据学号查询**/
    public SysUserVo getUserById(String studentNumber) {
        SysUser sysUser = sysUserMapper.getUserById(studentNumber);
        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(sysUser,sysUserVo);
        return sysUserVo;
    }

    /**条件查询用户**/
    public PageInfo<SysUserVo> getUserAllList(SysUserDto sysUserDto){
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDto,sysUser);
        PageHelper.startPage(sysUserDto.getOffset(),sysUserDto.getLimit());

        List<SysUserVo> sysUserVoList = new ArrayList<>();

        List<SysUser> sysUserList = sysUserMapper.getUserAllList(sysUser);
        sysUserList.forEach(sysUserTemp -> {
            SysUserVo sysUserVo = new SysUserVo();
            BeanUtils.copyProperties(sysUserTemp,sysUserVo);
            if (SysUserEnum.IS_ALIVE.getCode().equals(sysUserTemp.getIsAlive())){
                sysUserVo.setIsAlive(SysUserEnum.IS_ALIVE.getMsg());
            }else if(SysUserEnum.NOT_ALIVE.getCode().equals(sysUserTemp.getIsAlive())){
                sysUserVo.setIsAlive(SysUserEnum.NOT_ALIVE.getMsg());
            }else{
                sysUserVo.setIsAlive(SysUserEnum.UNKONW_TYPE.getMsg());
            }

            if (SysUserEnum.IS_SUPER.getCode().equals(sysUserTemp.getIsSuper())){
                sysUserVo.setIsSuper(SysUserEnum.IS_SUPER.getMsg());
            }else if(SysUserEnum.NOT_SUPER.getCode().equals(sysUserTemp.getIsSuper())){
                sysUserVo.setIsSuper(SysUserEnum.NOT_SUPER.getMsg());
            }else{
                sysUserVo.setIsSuper(SysUserEnum.UNKONW_TYPE.getMsg());
            }
            sysUserVoList.add(sysUserVo);
        });
        PageInfo<SysUserVo> sysUserVoPageInfo = null;
        try{
            sysUserVoPageInfo = new PageInfo<>(sysUserVoList);
        }catch (Exception e){
            log.info("分页查询用户出现异常{}",e);
            throw new SystemException("分页查询出现异常");
        }
        return sysUserVoPageInfo;
    }

    /**修改用户**/
    public int editSysUser(SysUserDto sysUserDto){
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDto,sysUser);
        return sysUserMapper.editSysUser(sysUser);
    }

    /**删除用户**/
    public int deleteSysUser(String studentNumber){
        SysUser sysUser = new SysUser();
        sysUser.setStudentNumber(studentNumber);
        sysUser.setIsAlive(SysUserEnum.NOT_ALIVE.getCode());
        int result = sysUserMapper.editSysUser(sysUser);
        if (result > 0) {
            purviewMapper.deletePurview(studentNumber);
        }
        return result;
    }

}