package com.today.roc.go.spring.mybatis.service.impl;

import com.today.roc.go.spring.mybatis.mapper.RocUserMapper;
import com.today.roc.go.spring.mybatis.model.RocUser;
import com.today.roc.go.spring.mybatis.service.RocUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年09月20日 10:26*
 * log.info()
 */
@Service
public class RocUserServiceImpl implements RocUserService {

    @Autowired
    RocUserMapper rocUserMapper;

    @Override
    public void save(RocUser rocUser) {
        rocUserMapper.insert(rocUser);
        throw new RuntimeException("异常下的事务处理");
    }
}
