package com.js.service.group;

import com.js.common.util.IdUtils;
import com.js.dto.group.GroupDto;
import com.js.enums.group.GroupEnum;
import com.js.mapper.group.GroupMapper;
import com.js.pojo.group.Group;
import com.js.vo.group.GroupVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangshuang
 * @Description: 小组相关Service
 * @Date: 2020/5/9 19:47
 **/
@Slf4j
@Service
public class GroupService {
    @Autowired
    private GroupMapper groupMapper;

    /**添加组别**/
    public int addGroup(GroupDto groupDto){
        Group group = new Group();
        BeanUtils.copyProperties(groupDto,group);
        group.setUuid(IdUtils.get32Uuid());
        return groupMapper.addGroup(group);
    }

    /**根据条件查询组别**/
    public List<GroupVo> getGroupByMess(GroupDto groupDto) {
        log.info("根据条件查询入参{}",groupDto);
        Group group = new Group();
        BeanUtils.copyProperties(groupDto,group);
        List<GroupVo> groupVos = new ArrayList<>();
        List<Group> groups = groupMapper.getGroupByMess(group);
        groups.forEach(groupTemp -> {
            GroupVo groupVo = new GroupVo();
            BeanUtils.copyProperties(groupTemp,groupVo);
            if (GroupEnum.HAVING_PROGRAM.getCode().equals(groupVo.getHasProgram())) {
                groupVo.setHasProgram(GroupEnum.HAVING_PROGRAM.getMsg());
            } else {
                groupVo.setHasProgram(GroupEnum.NO_HAVING_PROGRAM.getMsg());
            }
            if (GroupEnum.IS_ALIVE.getCode().equals(groupVo.getIsAlive())) {
                groupVo.setIsAlive(GroupEnum.IS_ALIVE.getMsg());
            } else {
                groupVo.setIsAlive(GroupEnum.NOT_ALIVE.getMsg());
            }
            groupVos.add(groupVo);
        });
        return groupVos;
    }


    /**根据主键查询操作**/
    public GroupVo getGroupByUuid(String uuid) {
        GroupVo groupVo = new GroupVo();
        Group group = groupMapper.getGroupByUuid(uuid);
        BeanUtils.copyProperties(group,groupVo);
        return groupVo;
    }

    /**修改操作**/
    public int editGroup(GroupDto groupDto) {
        Group group = new Group();
        BeanUtils.copyProperties(groupDto,group);
        return groupMapper.editGroup(group);
    }
}