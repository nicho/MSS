package com.aus.authority.model;

public class ResponsibilityInvOrgDto {

	private String invOrganizationId;
	
	private String invOrganizationName;
	
	private String responsibilityId;
	
	private String lastUpdatedBy;

	private String createdBy;
	
	private String lastUpdateLogin;
	
	private String isDelete;
	
	public String getInvOrganizationId() {
		return invOrganizationId;
	}

	public void setInvOrganizationId(String invOrganizationId) {
		this.invOrganizationId = invOrganizationId;
	}

	public String getInvOrganizationName() {
		return invOrganizationName;
	}

	public void setInvOrganizationName(String invOrganizationName) {
		this.invOrganizationName = invOrganizationName;
	}

	public String getResponsibilityId() {
		return responsibilityId;
	}

	public void setResponsibilityId(String responsibilityId) {
		this.responsibilityId = responsibilityId;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(String lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}
