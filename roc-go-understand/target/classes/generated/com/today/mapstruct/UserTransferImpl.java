package com.today.mapstruct;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-28T11:54:17+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class UserTransferImpl implements UserTransfer {

    @Override
    public UserVo userToUserVo(User user) {
        if ( user == null ) {
            return null;
        }

        UserVo userVo = new UserVo();

        userVo.setId( user.getId() );
        userVo.setSex( user.getSex() );
        userVo.setName( user.getName() );
        userVo.setAssets( user.getAssets() );

        return userVo;
    }
}
