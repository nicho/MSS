package com.aus.common.model;

public class ToDoListDto {
	String startDate;
	String endDate;
	String flowtype;
	String flowstate;
	String billCode;
	String commitUserName;
	String subject;
	String hasNotice;
    String currentUserId;
    String statusType;
    String flowtypeName;
    String wpageNum;//为了保留分页时的参数
    String wpageSize;//分页参数
	public String getCommitUserName() {
		return commitUserName;
	}

	public void setCommitUserName(String commitUserName) {
		this.commitUserName = commitUserName;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFlowtype() {
		return flowtype;
	}

	public void setFlowtype(String flowtype) {
		this.flowtype = flowtype;
	}

	public String getFlowstate() {
		return flowstate;
	}

	public void setFlowstate(String flowstate) {
		this.flowstate = flowstate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHasNotice() {
		return hasNotice;
	}

	public void setHasNotice(String hasNotice) {
		this.hasNotice = hasNotice;
	}

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getFlowtypeName() {
		return flowtypeName;
	}

	public void setFlowtypeName(String flowtypeName) {
		this.flowtypeName = flowtypeName;
	}

	public String getWpageNum() {
		return wpageNum;
	}

	public void setWpageNum(String wpageNum) {
		this.wpageNum = wpageNum;
	}

	public String getWpageSize() {
		return wpageSize;
	}

	public void setWpageSize(String wpageSize) {
		this.wpageSize = wpageSize;
	}


	
	
}
