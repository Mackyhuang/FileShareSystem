package com.macky.fileShareSystem.enums.state;

import com.macky.fileShareSystem.enums.Statable;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/6 21:55
 */
public enum SharedState implements Statable {

    SUCCESS(Statable.SUCCESS_CODE, "操作成功"),
    LIST_FAILED(-1001, "获取列表失败"),
    LACK_KEY(-1002, "缺少主键"),
    LACK_STATE(-1002, "缺少参数"),
    UPDATE_FAULED(-1008, "状态修改失败"),
    FILE_DBWRITE_FAILED(-1010, "文件数据库写入错误"),
    FILE_UPLOAD_FAILED(-1011, "文件上传错误"),
    NEED_LOGIN(-1003, "用户未登录"),
    PASSWORD_ERROR(-1012, "用户名或密码错误"),
    NO_RECORD(-1004, "没有这条记录");

    private int code;
    private String msg = null;

    SharedState(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
