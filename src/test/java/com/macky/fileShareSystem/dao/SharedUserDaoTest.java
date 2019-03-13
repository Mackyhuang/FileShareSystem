package com.macky.fileShareSystem.dao;

import com.macky.fileShareSystem.common.CommUtil;
import com.macky.fileShareSystem.entity.SharedUser;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/10 19:41
 */
public class SharedUserDaoTest extends BaseTest{

    @Resource
    private SharedUserDao sharedUserDao;

    @Test
    public void listUser() {
        List<SharedUser> sharedUsers = sharedUserDao.listUser();
        for (SharedUser user: sharedUsers) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void getPasswordById() {
        String id = "714D745CF96A4DC0B7525329D404E271";
        String passwordById = sharedUserDao.getPasswordById(id);
        System.out.println(passwordById);
    }

    @Test
    public void changePassword() {
        String id = "714D745CF96A4DC0B7525329D404E271";
        sharedUserDao.changePassword(id, "123");
    }

    @Test
    public void saveUser() {
        SharedUser user = new SharedUser();
        user.setuId(CommUtil.getUUID());
        user.setuUsername("macky");
        user.setuPassword("123");
        user.setuName("Mackyhuang");
        user.setCreateTime(new Date());
        System.out.println(user);
        Integer result = sharedUserDao.saveUser(user);
        System.out.println(result);
    }

    @Test
    public void count() {
        sharedUserDao.countUser();
    }

}