package com.today.roc.go.spring.jdbc.driver;

import java.sql.*;
import java.util.Objects;

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
 * @createTime 2020年09月09日 19:57*
 * log.info()
 */
public class MysqlCollection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class<?> driver = Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a", "root", "root");
            Statement statement = connection.createStatement();
//            int i = statement.executeUpdate("INSERT INTO cgb_stock_back_statistics(compose_key, statistics_date," +
//                    " week, packet_code, branch_company_id, case_stock, case_stock_money, back_money, back_rate) " +
//                    "VALUES " +
//                    "('2020-09-09-pk-000001-1000001', '2020-09-09 00:00:00', '星期三', 'pk-000001', 1000000, 1, 11.00, 11.00, 100.00)");
//            System.out.println(i);
            ResultSet resultSet = statement.executeQuery("SELECT * from cgb_stock_back_statistics ");
            System.out.println(resultSet.getString("compose_key"));
            resultSet.close();
        }catch (Exception e){
            throw e;
        }finally {
            if (Objects.nonNull(connection)){
                connection.close();
            }
        }
    }
}
