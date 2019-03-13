package com.macky.fileShareSystem.entity;



public class DesignEnclosure {
	private static final long serialVersionUID = 1L;
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
	
}
