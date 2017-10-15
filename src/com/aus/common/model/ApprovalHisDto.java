package com.aus.common.model;

import com.aus.common.util.GBKEncoded;

public class ApprovalHisDto {
	private String approvalDate;
	private String approvalUserName;
	private String statusName;
	private String actionName;
	private String docId;
	private String nextApprovalUserName;
	private String approvalComment;
	private String processCode;
	private String approvalHistId;
	private String operationCode;
	private String userId;
	public String getApprovalDate() {
		return approvalDate;
	}
	public String getApprovalDt_gbk() {
		return GBKEncoded.getEncodedRTFString(approvalDate);
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalUserName() {
		return approvalUserName;
	}
	public String getApprovalUserName_gbk() {
		return GBKEncoded.getEncodedRTFString(approvalUserName);
	}
	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public String getStatusName() {
		return statusName;
	}
	public String getStatusName_gbk() {
		return GBKEncoded.getEncodedRTFString(statusName);
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getActionName() {
		return actionName;
	}
	public String getActionName_gbk() {
		return GBKEncoded.getEncodedRTFString(actionName);
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getNextApprovalUserName() {
		return nextApprovalUserName;
	}

	public void setNextApprovalUserName(String nextApprovalUserName) {
		this.nextApprovalUserName = nextApprovalUserName;
	}

	public String getApprovalComment() {
		return approvalComment;
	}
	public String getApprovalComment_gbk() {
		return GBKEncoded.getEncodedRTFString(approvalComment);
	}
	public void setApprovalComment(String approvalComment) {
		this.approvalComment = approvalComment;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getApprovalHistId() {
		return approvalHistId;
	}

	public void setApprovalHistId(String approvalHistId) {
		this.approvalHistId = approvalHistId;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
