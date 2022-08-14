package com.today.mapstruct;

import org.mapstruct.Mapper;

/**
 * description：
 * author：roc.zou
 * 2021/5/18 6:45 下午
 */
@Mapper(componentModel = "spring")
public interface UserTransfer {

    UserVo userToUserVo(User user);

}
