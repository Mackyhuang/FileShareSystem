package com.macky.fileShareSystem.entity;

public class DesignTemplate {
	private static final long serialVersionUID = 1L;
	private String tid; //ID
	private String tfileId; //模板附件ID
	private java.util.Date ttime; //上传时间
	private String tuser; //上传人
	private String tremark; //备注
	private String tstate; //状态：0无效，1有效，2处理中，3已完成


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
	
}
