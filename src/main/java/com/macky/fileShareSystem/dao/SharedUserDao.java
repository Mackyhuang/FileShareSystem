package com.macky.fileShareSystem.dao;

import com.macky.fileShareSystem.entity.SharedUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/10 19:28
 */
public interface SharedUserDao {

    List<SharedUser> listUser();

    SharedUser getUser(String username);

    String getPasswordById(@Param("username") String username);

    Integer changePassword(@Param("username")String username, @Param("password")String password);

    Integer saveUser(SharedUser user);

    Integer countUser();
}
