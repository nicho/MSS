package com.aus.authority.model;

public class UserDto {
	
	private String userId;
	private String userName;
	private String userType;
	private String userTypeName;
	private String personId;
	private String personName;
	private String custAccountId;
	private String custAccountName;
	private String storeCode;
	private String beginDate;
	private String endDate;
	private String password;
	private String internalUser;
	private String isEbsUser;
	private String lastUpdateLogin;
	private String lastUpdatedBy;
	private String lastUpdateDate;
	private String createdBy;
	private String creationDate;
	private String salt;
	private String pwdModDate;
	private String validataCode;
	private String outDate;
	private String orgId;
	private String email;
	private String phone;
	private String pageUserType;
	private String responsibilityId;
	private String person_user_type;
	private String org_status;//用于标识组织是否能够编辑、重置密码、分配职责
	private String isValid; //是否有效
	private String deleteUserResponsibility;//删除的职责
	public String getResponsibilityId() {
		return responsibilityId;
	}
	public void setResponsibilityId(String responsibilityId) {
		this.responsibilityId = responsibilityId;
	}
	public String getPageUserType() {
		return pageUserType;
	}
	public void setPageUserType(String pageUserType) {
		this.pageUserType = pageUserType;
	}
	private String[] responsibilityIds;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getCustAccountId() {
		return custAccountId;
	}
	public void setCustAccountId(String custAccountId) {
		this.custAccountId = custAccountId;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInternalUser() {
		return internalUser;
	}
	public void setInternalUser(String internalUser) {
		this.internalUser = internalUser;
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
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getCustAccountName() {
		return custAccountName;
	}
	public void setCustAccountName(String custAccountName) {
		this.custAccountName = custAccountName;
	}
	public String getIsEbsUser() {
		return isEbsUser;
	}
	public void setIsEbsUser(String isEbsUser) {
		this.isEbsUser = isEbsUser;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String[] getResponsibilityIds() {
		return responsibilityIds;
	}
	public void setResponsibilityIds(String[] responsibilityIds) {
		this.responsibilityIds = responsibilityIds;
	}
	public String getPwdModDate() {
		return pwdModDate;
	}
	public void setPwdModDate(String pwdModDate) {
		this.pwdModDate = pwdModDate;
	}
	public String getValidataCode() {
		return validataCode;
	}
	public void setValidataCode(String validataCode) {
		this.validataCode = validataCode;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOrg_status() {
		return org_status;
	}
	public void setOrg_status(String org_status) {
		this.org_status = org_status;
	}
	public String getPerson_user_type() {
		return person_user_type;
	}
	public void setPerson_user_type(String person_user_type) {
		this.person_user_type = person_user_type;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getDeleteUserResponsibility() {
		return deleteUserResponsibility;
	}
	public void setDeleteUserResponsibility(String deleteUserResponsibility) {
		this.deleteUserResponsibility = deleteUserResponsibility;
	}
	
	
	

}
