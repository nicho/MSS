package com.aus.common.model;

public class PersonDto extends BaseDto {

	private String userId;
	private String userAccount;
	private String userName;
	private String departmentCode;
	private String departmentName;
	private String orgId;
	private String channelType;
	private String channelName;
	private String personId;
	private String orgName;
	private String positionId;
	private String positionName;
	private String budgetPrivilege;
	private String url;
	private String userNameSelect;
	private String conditionSql;
	private String jobId;
	private String primaryFlag;
	
	private String emailAddress;
	private String sex;
	private String mobilePhone;
	private String jobIdStr;
	private String province;
	private String ignoreOrg;
	private String service_start_dateStr;
	
	private String hight_edu;
	private String age;
	private String sexName;
	private String home_adress;
	private String id_card_num;
	private String try_salary_level;
	private String ignoreValid;
	private String levelTypeUp;
	private String levelTypeDown;
	private String isInvalid;  //是否需要无效的用户
	private String month; //查询历史月份数据
	private String start_date; //查询历史数据开始时间
	private String end_date; //查询历史数据结束时间
	private String modelName; //用户绩效管理
	private String monthStart; //查询历史月份开始
	private String monthEnd; //查询历史月份结束
	private String isNotProivnce; //是否省区
	private String hr_organization_id;//部门ID
	private String nowUserid;
	private String nowUserType;
	private String ignorePositionRelation;
	private String displayUserAccount;
	private String hrOrganizationName;

	private String isBrrow;

	public String getNowUserType() {
		return nowUserType;
	}

	public void setNowUserType(String nowUserType) {
		this.nowUserType = nowUserType;
	}

	public String getIgnorePositionRelation() {
		return ignorePositionRelation;
	}

	public void setIgnorePositionRelation(String ignorePositionRelation) {
		this.ignorePositionRelation = ignorePositionRelation;
	}

	public String getDisplayUserAccount() {
		return displayUserAccount;
	}

	public void setDisplayUserAccount(String displayUserAccount) {
		this.displayUserAccount = displayUserAccount;
	}

	public String getNowUserid() {
		return nowUserid;
	}

	public void setNowUserid(String nowUserid) {
		this.nowUserid = nowUserid;
	}

	public String getIsNotProivnce() {
		return isNotProivnce;
	}

	public void setIsNotProivnce(String isNotProivnce) {
		this.isNotProivnce = isNotProivnce;
	}

	public String getMonthStart() {
		return monthStart;
	}

	public void setMonthStart(String monthStart) {
		this.monthStart = monthStart;
	}

	public String getMonthEnd() {
		return monthEnd;
	}

	public void setMonthEnd(String monthEnd) {
		this.monthEnd = monthEnd;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getIsInvalid() {
		return isInvalid;
	}

	public void setIsInvalid(String isInvalid) {
		this.isInvalid = isInvalid;
	}

	public String getLevelTypeUp() {
		return levelTypeUp;
	}

	public void setLevelTypeUp(String levelTypeUp) {
		this.levelTypeUp = levelTypeUp;
	}

	public String getLevelTypeDown() {
		return levelTypeDown;
	}

	public void setLevelTypeDown(String levelTypeDown) {
		this.levelTypeDown = levelTypeDown;
	}

	public String getIgnoreValid() {
		return ignoreValid;
	}

	public void setIgnoreValid(String ignoreValid) {
		this.ignoreValid = ignoreValid;
	}

	public String getTry_salary_level() {
		return try_salary_level;
	}

	public void setTry_salary_level(String try_salary_level) {
		this.try_salary_level = try_salary_level;
	}

	public String getHome_adress() {
		return home_adress;
	}

	public void setHome_adress(String home_adress) {
		this.home_adress = home_adress;
	}

	public String getId_card_num() {
		return id_card_num;
	}

	public void setId_card_num(String id_card_num) {
		this.id_card_num = id_card_num;
	}

	public String getHight_edu() {
		return hight_edu;
	}

	public void setHight_edu(String hight_edu) {
		this.hight_edu = hight_edu;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getService_start_dateStr() {
		return service_start_dateStr;
	}

	public void setService_start_dateStr(String service_start_dateStr) {
		this.service_start_dateStr = service_start_dateStr;
	}

	public String getIgnoreOrg() {
		return ignoreOrg;
	}

	public void setIgnoreOrg(String ignoreOrg) {
		this.ignoreOrg = ignoreOrg;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getJobIdStr() {
		return jobIdStr;
	}

	public void setJobIdStr(String jobIdStr) {
		this.jobIdStr = jobIdStr;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPrimaryFlag() {
		return primaryFlag;
	}

	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getBudgetPrivilege() {
		return budgetPrivilege;
	}

	public void setBudgetPrivilege(String budgetPrivilege) {
		this.budgetPrivilege = budgetPrivilege;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserNameSelect() {
		return userNameSelect;
	}

	public void setUserNameSelect(String userNameSelect) {
		this.userNameSelect = userNameSelect;
	}

	public String getConditionSql() {
		return conditionSql;
	}

	public void setConditionSql(String conditionSql) {
		this.conditionSql = conditionSql;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getHr_organization_id() {
		return hr_organization_id;
	}

	public void setHr_organization_id(String hr_organization_id) {
		this.hr_organization_id = hr_organization_id;
	}

	public String getHrOrganizationName() {
		return hrOrganizationName;
	}

	public void setHrOrganizationName(String hrOrganizationName) {
		this.hrOrganizationName = hrOrganizationName;
	}

	public String getIsBrrow() {
		return isBrrow;
	}

	public void setIsBrrow(String isBrrow) {
		this.isBrrow = isBrrow;
	}

	
	

}
