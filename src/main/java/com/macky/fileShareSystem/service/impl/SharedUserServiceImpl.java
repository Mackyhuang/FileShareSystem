package com.macky.fileShareSystem.service.impl;

import com.macky.fileShareSystem.common.CommUtil;
import com.macky.fileShareSystem.common.PageUtil;
import com.macky.fileShareSystem.dao.SharedUserDao;
import com.macky.fileShareSystem.dto.ServerResponse;
import com.macky.fileShareSystem.entity.SharedUser;
import com.macky.fileShareSystem.enums.state.SharedState;
import com.macky.fileShareSystem.service.SharedUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/10 20:28
 */
@Service
public class SharedUserServiceImpl implements SharedUserService {

    @Resource
    private SharedUserDao sharedUserDao;


    /**
     * 获取所有用户
     *
     * @return
     */
    @Override
    public ServerResponse listUser() {
        List<SharedUser> sharedUsers = sharedUserDao.listUser();
        if (sharedUsers == null || sharedUsers.size() <= 0){
            return ServerResponse.response(SharedState.NO_RECORD);
        }
//        PageUtil.toPage(page);
        return ServerResponse.response(SharedState.SUCCESS, PageUtil.pageInfo(sharedUsers));
    }

    /**
     * 通过用户id获取密码
     *
     * @param username
     * @return
     */
    @Override
    public ServerResponse loginCheck(String username, String password) {
        if (username == null || "".equals(username) || password == null || "".equals(password)){
            return ServerResponse.response(SharedState.LACK_STATE);
        }
        String realPassword = sharedUserDao.getPasswordById(username);
        if (realPassword == null || "".equals(realPassword)){
            return ServerResponse.response(SharedState.NO_RECORD);
        }
        if (!realPassword.equals(CommUtil.getMD5(password))){
            return ServerResponse.response(SharedState.PASSWORD_ERROR);
        }
        return ServerResponse.response(SharedState.SUCCESS, password);
    }


    /**
     * 修改密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse changePassword(String username, String password) {
        if (username == null || "".equals(username) || password == null || "".equals(password)){
            return ServerResponse.response(SharedState.LACK_STATE);
        }
        Integer result = sharedUserDao.changePassword(username, password);
        if (result <= 0){
            return ServerResponse.response(SharedState.UPDATE_FAULED);
        }
        return ServerResponse.response(SharedState.SUCCESS);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse saveUser(SharedUser user) {
        if (user == null || user.getuId() == null || user.getCreateTime() == null || user.getuName() == null ||
                user.getuPassword() == null || user.getuUsername() == null ||
                "".equals(user.getuName()) || "".equals(user.getuUsername()) ||
                "".equals(user.getuPassword())){
            return ServerResponse.response(SharedState.LACK_STATE);
        }
        Integer result = sharedUserDao.saveUser(user);
        if (result <= 0){
            return ServerResponse.response(SharedState.UPDATE_FAULED);
        }
        return ServerResponse.response(SharedState.SUCCESS);
    }

    /**
     * 获取单个用户
     *
     * @param username
     * @return
     */
    @Override
    public ServerResponse getUser(String username) {
        if (username == null || "".equals(username)){
            return ServerResponse.response(SharedState.LACK_KEY);
        }
        return ServerResponse.response(SharedState.SUCCESS, sharedUserDao.getUser(username));
    }

    /**
     * 用户数量
     *
     * @return
     */
    @Override
    public ServerResponse countUser() {
        Integer integer = sharedUserDao.countUser();
        return ServerResponse.response(SharedState.SUCCESS, integer);
    }
}
