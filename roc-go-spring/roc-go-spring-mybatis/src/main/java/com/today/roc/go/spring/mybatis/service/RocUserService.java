package com.today.roc.go.spring.mybatis.service;

import com.today.roc.go.spring.mybatis.model.RocUser;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年09月20日 10:24*
 * log.info()
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface RocUserService {

    public void save(RocUser rocUser);

}
