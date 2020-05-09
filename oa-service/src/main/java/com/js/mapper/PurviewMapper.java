package com.js.mapper;

import com.js.pojo.Purview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PurviewMapper {

    int deletePurview(@Param("user_id") String userId);

    int addPurview(Purview purview);

    Purview selectPurviewByUserId(@Param("user_id") String userId);

    int editPurview(Purview purview);

}