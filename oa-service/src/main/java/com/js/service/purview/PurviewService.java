package com.js.service.purview;

import com.js.common.exception.SystemException;
import com.js.common.util.IdUtils;
import com.js.dto.purview.PurviewDto;
import com.js.mapper.purview.PurviewMapper;
import com.js.pojo.purview.Purview;
import com.js.vo.purview.PurviewVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: jiangshuang
 * @Description: 节目对应的用户对应权限Mapper
 * @Date: 2020/5/9 19:47
 **/
@Service
@Slf4j
public class PurviewService {

    @Autowired
    private PurviewMapper purviewMapper;

    /** 删除操作 **/
    public int deletePurview(String userId) {
        return purviewMapper.deletePurview(userId);
    }

    /** 添加操作 **/
    public int addPurview(PurviewDto purviewDto) {
        log.info("添加权限入参{}", purviewDto.toString());
        Purview purviewTemp = purviewMapper.selectPurviewByUserId(purviewDto.getUserId());
        if (purviewTemp == null) {
            Purview purview = new Purview();
            BeanUtils.copyProperties(purviewDto, purview);
            purview.setUuid(IdUtils.get32Uuid());
            return purviewMapper.addPurview(purview);
        }
        throw new SystemException("该用户已经存在权限信息，如果需要修改，请执行修改操作");
    }

    /** 根据用户id查询操作 **/
    public PurviewVo selectPurviewByUserId(String userId) {
        Purview purview = purviewMapper.selectPurviewByUserId(userId);
        if (purview == null){
            return null;
        }
        PurviewVo purviewVo = new PurviewVo();
        BeanUtils.copyProperties(purview, purviewVo);
        return purviewVo;
    }

    /** 修改权限操作 **/
    public int editPurview(PurviewDto purviewDto) {
        Purview purview = new Purview();
        BeanUtils.copyProperties(purviewDto, purview);
        return purviewMapper.editPurview(purview);
    }

}