package com.aus.common.model;

public class DepartmemtOrganizationDto extends BaseDto {

	/****
	 * 部门编号
	 */
	private String hrOrganizationId;
	/***
	 * 部门名称
	 */
	private String hrOrganizationName;
	private String hrOrganizationNameAbbrev;
	private String ouOrganizationId;
	private String parentHrOrganizationId;

	public String getHrOrganizationId() {
		return hrOrganizationId;
	}

	public void setHrOrganizationId(String hrOrganizationId) {
		this.hrOrganizationId = hrOrganizationId;
	}

	public String getHrOrganizationName() {
		return hrOrganizationName;
	}

	public void setHrOrganizationName(String hrOrganizationName) {
		this.hrOrganizationName = hrOrganizationName;
	}

	public String getHrOrganizationNameAbbrev() {
		return hrOrganizationNameAbbrev;
	}

	public void setHrOrganizationNameAbbrev(String hrOrganizationNameAbbrev) {
		this.hrOrganizationNameAbbrev = hrOrganizationNameAbbrev;
	}

	public String getOuOrganizationId() {
		return ouOrganizationId;
	}

	public void setOuOrganizationId(String ouOrganizationId) {
		this.ouOrganizationId = ouOrganizationId;
	}

	public String getParentHrOrganizationId() {
		return parentHrOrganizationId;
	}

	public void setParentHrOrganizationId(String parentHrOrganizationId) {
		this.parentHrOrganizationId = parentHrOrganizationId;
	}

}
