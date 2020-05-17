package com.js.mapper.group;

import com.js.pojo.group.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Author: jiangshuang
 * @Description: 小组相关Mapper
 * @Date: 2020/5/9 19:47
 **/
@Mapper
public interface GroupMapper {
    /**添加组别**/
    int addGroup(Group group);

    /**根据条件查询组别**/
    List<Group> getGroupByMess(Group group);

    /**根据主键查询操作**/
    Group getGroupByUuid(@Param("uuid") String uuid);

    /**修改操作**/
    int editGroup(Group group);
}