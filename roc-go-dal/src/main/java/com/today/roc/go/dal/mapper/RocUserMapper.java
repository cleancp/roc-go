package com.today.roc.go.dal.mapper;

import com.today.roc.go.dal.model.RocUser;
import com.today.roc.go.dal.model.RocUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RocUserMapper {
    long countByExample(RocUserExample example);

    int deleteByExample(RocUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RocUser record);

    int insertSelective(RocUser record);

    List<RocUser> selectByExample(RocUserExample example);

    RocUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RocUser record, @Param("example") RocUserExample example);

    int updateByExample(@Param("record") RocUser record, @Param("example") RocUserExample example);

    int updateByPrimaryKeySelective(RocUser record);

    int updateByPrimaryKey(RocUser record);
}