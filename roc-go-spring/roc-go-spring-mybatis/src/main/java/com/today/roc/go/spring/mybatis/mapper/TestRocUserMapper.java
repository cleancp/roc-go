package com.today.roc.go.spring.mybatis.mapper;

import com.today.roc.go.spring.mybatis.model.RocUser;
import com.today.roc.go.spring.mybatis.model.RocUserExample;
import com.today.roc.go.spring.mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * @createTime 2020年09月17日 14:21*
 * log.info()
 */
public class TestRocUserMapper {

    static SqlSessionFactory sqlSessionFactory = null;

    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    public void testAdd() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            RocUserMapper rocUserMapper = sqlSession.getMapper(RocUserMapper.class);
            RocUser rocUser = new RocUser();
            rocUser.setBirthday(new Date());
            rocUser.setName("王霸");
            rocUser.setNamePinyin("wb");
            rocUserMapper.insert(rocUser);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public List<RocUser> getUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<RocUser> rocUsers = new ArrayList<>();
        try {
            RocUserMapper rocUserMapper = sqlSession.getMapper(RocUserMapper.class);
            RocUserExample example = new RocUserExample();
            RocUserExample.Criteria criteria = example.createCriteria();
            rocUsers = rocUserMapper.selectByExample(null);
        } finally {
            sqlSession.close();
        }
        return rocUsers;
    }

    public static void main(String[] args) {
        TestRocUserMapper mapper = new TestRocUserMapper();
        mapper.testAdd();
        List<RocUser> users = mapper.getUser();
        System.out.println(users);
    }
}
