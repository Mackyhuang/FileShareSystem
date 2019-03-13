package com.macky.fileShareSystem.vo;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/6 17:14
 * 用于上传excel文件的参数接收类
 */
public class TemplateRecordVO {

    private static final long serialVersionUID = 1L;
    private String tid; //ID
    private String tfileId; //模板附件ID
    private java.util.Date ttime; //上传时间
    private String tuser; //上传人
    private String tremark; //备注
    private String tstate; //状态：0无效，1有效，2处理中，3已完成
    private String enclosureId; //
    private String businessId; //业务ID
    private String busiConId; //业务二级ID
    private String enclosureName; //附件中文名称
    private String enclosurePath; //附件文件路径
    private String enclosureOrder; //排序
    private Integer fileSize; //附件大小
    private String extName; //扩展名


    public String getEnclosureId() {
        return enclosureId;
    }
    public void setEnclosureId(String enclosureId) {
        this.enclosureId = enclosureId;
    }


    public String getBusinessId() {
        return businessId;
    }
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusiConId() {
        return busiConId;
    }
    public void setBusiConId(String busiConId) {
        this.busiConId = busiConId;
    }

    public String getEnclosureName() {
        return enclosureName;
    }
    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    public String getEnclosurePath() {
        return enclosurePath;
    }
    public void setEnclosurePath(String enclosurePath) {
        this.enclosurePath = enclosurePath;
    }

    public String getEnclosureOrder() {
        return enclosureOrder;
    }
    public void setEnclosureOrder(String enclosureOrder) {
        this.enclosureOrder = enclosureOrder;
    }

    public Integer getFileSize() {
        return fileSize;
    }
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getExtName() {
        return extName;
    }
    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }


    public String getTfileId() {
        return tfileId;
    }
    public void setTfileId(String tfileId) {
        this.tfileId = tfileId;
    }

    public java.util.Date getTtime() {
        return ttime;
    }
    public void setTtime(java.util.Date ttime) {
        this.ttime = ttime;
    }

    public String getTuser() {
        return tuser;
    }
    public void setTuser(String tuser) {
        this.tuser = tuser;
    }

    public String getTremark() {
        return tremark;
    }
    public void setTremark(String tremark) {
        this.tremark = tremark;
    }

    public String getTstate() {
        return tstate;
    }
    public void setTstate(String tstate) {
        this.tstate = tstate;
    }

    @Override
    public String toString() {
        return "TemplateRecordVO{" +
                "tid='" + tid + '\'' +
                ", tfileId='" + tfileId + '\'' +
                ", ttime=" + ttime +
                ", tuser='" + tuser + '\'' +
                ", tremark='" + tremark + '\'' +
                ", tstate='" + tstate + '\'' +
                ", enclosureId='" + enclosureId + '\'' +
                ", businessId='" + businessId + '\'' +
                ", busiConId='" + busiConId + '\'' +
                ", enclosureName='" + enclosureName + '\'' +
                ", enclosurePath='" + enclosurePath + '\'' +
                ", enclosureOrder='" + enclosureOrder + '\'' +
                ", fileSize=" + fileSize +
                ", extName='" + extName + '\'' +
                '}';
    }
}
