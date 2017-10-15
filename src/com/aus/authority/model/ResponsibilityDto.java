package com.aus.authority.model;

/**
 * 职责
 * 
 * @author duzh
 *
 */
public class ResponsibilityDto {

	private String responsibilityId;
	private String responsibilityCode;
	private String responsibilityName;
	private String responsibilityDesc;
	private String beginDate;
	private String endDate;
	private String lastUpdateLogin;
	private String lastUpdatedBy;
	private String lastUpdateDate;
	private String createdBy;
	private String creationDate;
	private String orgId;
	private String isValid;
	private String isDisplay;
	private String[] roleIds;
	private String[] profileIds;
	private String[] profileVals;
	private String[] responsibilityInvOrgs;
	private String userId;
	private String url;
	public String getResponsibilityId() {
		return responsibilityId;
	}
	public void setResponsibilityId(String responsibilityId) {
		this.responsibilityId = responsibilityId;
	}
	public String getResponsibilityCode() {
		return responsibilityCode;
	}
	public void setResponsibilityCode(String responsibilityCode) {
		this.responsibilityCode = responsibilityCode;
	}
	public String getResponsibilityName() {
		return responsibilityName;
	}
	public void setResponsibilityName(String responsibilityName) {
		this.responsibilityName = responsibilityName;
	}
	public String getResponsibilityDesc() {
		return responsibilityDesc;
	}
	public void setResponsibilityDesc(String responsibilityDesc) {
		this.responsibilityDesc = responsibilityDesc;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLastUpdateLogin() {
		return lastUpdateLogin;
	}
	public void setLastUpdateLogin(String lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}	
	public String[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	public String[] getProfileIds() {
		return profileIds;
	}
	public void setProfileIds(String[] profileIds) {
		this.profileIds = profileIds;
	}
	public String[] getProfileVals() {
		return profileVals;
	}
	public void setProfileVals(String[] profileVals) {
		this.profileVals = profileVals;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String[] getResponsibilityInvOrgs() {
		return responsibilityInvOrgs;
	}
	public void setResponsibilityInvOrgs(String[] responsibilityInvOrgs) {
		this.responsibilityInvOrgs = responsibilityInvOrgs;
	}
	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
