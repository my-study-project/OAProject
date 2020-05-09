package com.js.mapper;

import com.js.pojo.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper {
    int addGroup(Group group);

    List<Group> getGroupByMess(Group group);

    Group getGroupByUuid(@Param("uuid") String uuid);

    int editGroup(Group group);
}