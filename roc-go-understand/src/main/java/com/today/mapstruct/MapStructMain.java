package com.today.mapstruct;

import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

/**
 * description：
 * author：roc.zou
 * 2021/5/18 6:48 下午
 */
public class MapStructMain {

    public static void main(String[] args) {
        User user = new User();
        user.setAssets(BigDecimal.valueOf(100902193.213));
        user.setId(1L);
        user.setName("java");
        UserTransfer transfer = Mappers.getMapper(UserTransfer.class);
        UserVo userVo = transfer.userToUserVo(user);
        System.out.println(userVo);

    }

}
