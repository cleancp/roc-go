package com.today.roc.go.spring.jdbc.service.impl;

import com.today.roc.go.spring.jdbc.mapper.RocUserMapper;
import com.today.roc.go.spring.jdbc.po.RocUser;
import com.today.roc.go.spring.jdbc.service.RocUserService;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;
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
 * @createTime 2020年09月10日 19:34*
 * log.info()
 */
public class RocUserServiceImpl implements RocUserService {

    private JdbcTemplate jdbcTemplate;

    //设置数据源
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveUser(RocUser rocUser) {
        int update = jdbcTemplate.update("insert into roc_user(`name` , `name_pinyin` , `birthday`) values (?,?,?) "
                , new Object[]{rocUser.getName(), rocUser.getNamePinyin(), rocUser.getBirthday()}
                ,new int[]{Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP});
        System.out.println(update);
    }

    @Override
    public List<RocUser> getRocUsers() {
        List<RocUser> rocUsers = jdbcTemplate.query("select * from roc_user ", new RocUserMapper());
        return rocUsers;
    }
}
