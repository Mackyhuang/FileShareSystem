package com.macky.fileShareSystem.common.fileUtil;


import javax.servlet.http.HttpSession;

/**
 * Session 相关工具类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/12/06 22:48:57
 */
public class SessionUtil {

    // 从会话中获取文件保存路径
    public static String getBaseDirFromSession(HttpSession session) {
        return (String) session.getServletContext().getRealPath("/");
    }
}
