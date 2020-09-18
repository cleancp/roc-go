package com.today.roc.go.spring.mybatis.mapper;

import com.today.roc.go.spring.mybatis.model.RocUser;
import com.today.roc.go.spring.mybatis.model.RocUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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