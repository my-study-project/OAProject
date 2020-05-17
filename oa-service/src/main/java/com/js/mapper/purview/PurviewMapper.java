package com.js.mapper.purview;

import com.js.pojo.purview.Purview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: jiangshuang
 * @Description: 节目对应的用户对应权限Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface PurviewMapper {

    /**删除操作**/
    int deletePurview(@Param("user_id") String userId);

    /**添加操作**/
    int addPurview(Purview purview);

    /**根据用户id查询操作**/
    Purview selectPurviewByUserId(@Param("user_id") String userId);

    /**修改权限操作**/
    int editPurview(Purview purview);

}