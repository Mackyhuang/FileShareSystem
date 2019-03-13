package com.macky.fileShareSystem.common;

import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/6 22:41
 * 工具类
 */
public class CommUtil {
    /**
     * 获取UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        uuid = uuid.toUpperCase();
        return uuid;
    }

    public static String getMD5(String password){
        String newPassword = "8023" + password;
        return DigestUtils.md5DigestAsHex(newPassword.getBytes());
    }
}
