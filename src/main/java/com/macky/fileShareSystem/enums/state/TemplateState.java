package com.macky.fileShareSystem.enums.state;

import com.macky.fileShareSystem.enums.Statable;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/6 21:55
 */
public enum  TemplateState implements Statable {

    SUCCESS(Statable.SUCCESS_CODE, "操作成功"),
    LIST_FAILED(-1001, "获取模板列表失败"),
    LACK_KEY(-1002, "缺少主键"),
    LACK_STATE(-1002, "缺少状态"),
    LACK_FILENAME(-1012, "缺少文件名"),
    UPDATE_FAULED(-1008, "状态修改失败"),
    FILE_INFO_ERROR(-1009, "文件信息错误"),
    EXCEL_READ_ERROR(-1011, "Excel表格读取失败"),
    FILE_UPLOAD_FAILED(-1010, "文件上传错误"),
    NEED_LOGIN(-1003, "用户未登录"),
    NO_RECORD(-1004, "没有这条记录");

    private int code;
    private String msg = null;

    TemplateState(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
