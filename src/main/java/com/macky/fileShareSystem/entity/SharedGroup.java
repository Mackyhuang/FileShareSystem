package com.macky.fileShareSystem.entity;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/10 19:26
 */
public class SharedGroup {

    private String gId;

    private String gName;

    private String gLeader;

    public String getgId() {
        return gId;
    }

    public void setgId(String gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgLeader() {
        return gLeader;
    }

    public void setgLeader(String gLeader) {
        this.gLeader = gLeader;
    }

    @Override
    public String toString() {
        return "SharedGroup{" +
                "gId='" + gId + '\'' +
                ", gName='" + gName + '\'' +
                ", gLeader='" + gLeader + '\'' +
                '}';
    }
}
