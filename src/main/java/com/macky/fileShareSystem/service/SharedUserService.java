package com.macky.fileShareSystem.service;

import com.macky.fileShareSystem.dto.ServerResponse;
import com.macky.fileShareSystem.entity.SharedUser;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/10 20:27
 */
public interface SharedUserService {

    /**
     * 获取所有用户
     * @return
     */
    ServerResponse listUser();

     /**
     * 通过用户id获取密码
     * @param username
     * @param password
     * @return
     */
    ServerResponse loginCheck(String username, String password);

    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    ServerResponse changePassword(String username, String password);

    /**
     * 添加用户
     * @param user
     * @return
     */
    ServerResponse saveUser(SharedUser user);

    /**
     * 获取单个用户
     * @param username
     * @return
     */
    ServerResponse getUser(String username);

    /**
     * 用户数量
     * @return
     */
    ServerResponse countUser();
}
