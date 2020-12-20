package com.today.roc.go.biz.roc.impl;

import com.today.roc.go.biz.roc.RocInfoService;
import com.today.roc.go.biz.roc.RocUserService;
import com.today.roc.go.dal.dao.RocInfoDao;
import com.today.roc.go.dal.dao.RocUserDao;
import com.today.roc.go.dal.model.RocInfo;
import com.today.roc.go.dal.model.RocUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Software License Declaration.
 * <p>
 * zhilingsd.com, Co,. Ltd.
 * Copyright © 2016 All Rights Reserved.
 * <p>
 * Copyright Notice
 * This documents is provided to zhilingsd contracting agent or authorized programmer only.
 * This source code is written and edited by zhilingsd Co,.Ltd Inc specially for financial
 * business contracting agent or authorized cooperative company, in order to help them to
 * install, programme or central control in certain project by themselves independently.
 * <p>
 * Disclaimer
 * If this source code is needed by the one neither contracting agent nor authorized programmer
 * during the use of the code, should contact to zhilingsd Co,. Ltd Inc, and get the confirmation
 * and agreement of three departments managers  - Research Department, Marketing Department and
 * Production Department.Otherwise zhilingsd will charge the fee according to the programme itself.
 * <p>
 * Any one,including contracting agent and authorized programmer,cannot share this code to
 * the third party without the agreement of zhilingsd. If Any problem cannot be solved in the
 * procedure of programming should be feedback to zhilingsd Co,. Ltd Inc in time, Thank you!
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年12月18日 18:12*
 * log.info()
 */
@Slf4j
@Service
public class RocInfoServiceImpl implements RocInfoService {

    @Autowired
    private RocInfoDao     rocInfoDao;
    @Autowired
    private RocUserService rocUserService;

    //@Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRocInfo(int count) {
        for (int i = 0; i < count; i++) {
            rocInfoDao.insert(buildInfo(i));
            rocUserService.saveRocUser(buildUser(i), i);
        }
    }

    public RocUser buildUser(int i) {
        RocUser rocUser = new RocUser();
        rocUser.setName("user-" + i);
        return rocUser;
    }

    public RocInfo buildInfo(int i) {
        RocInfo rocInfo = new RocInfo();
        rocInfo.setName("info-" + i);
        return rocInfo;
    }

}
