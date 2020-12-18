package com.today.roc.go.dal.mapper;

import com.today.roc.go.dal.model.RocInfo;
import com.today.roc.go.dal.model.RocInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RocInfoMapper {
    long countByExample(RocInfoExample example);

    int deleteByExample(RocInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RocInfo record);

    int insertSelective(RocInfo record);

    List<RocInfo> selectByExample(RocInfoExample example);

    RocInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RocInfo record, @Param("example") RocInfoExample example);

    int updateByExample(@Param("record") RocInfo record, @Param("example") RocInfoExample example);

    int updateByPrimaryKeySelective(RocInfo record);

    int updateByPrimaryKey(RocInfo record);
}