package com.aus.common.model;


public class PersonKpiDto { 
	private String task_object_type; 
	private String person_id; 
	private String person_name; 
	private String department_code; 
	private String department_name; 
	private String org_id; 
	private String org_name; 
	private String objectType; 
	private String position_id; 
	private String position_name; 
	private String task_type; 
	private String period_type; 
	private String period_type_name; 
	private String start_date; 
	private String end_date; 
	private String task_value; 
	private String task_unit;
	private String province;
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPeriod_type_name() {
		return period_type_name;
	}
	public void setPeriod_type_name(String period_type_name) {
		this.period_type_name = period_type_name;
	}
	private String[] idsArr;
	
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getTask_object_type() {
		return task_object_type;
	}
	public void setTask_object_type(String task_object_type) {
		this.task_object_type = task_object_type;
	}
	public String[] getIdsArr() {
		return idsArr;
	}
	public void setIdsArr(String[] idsArr) {
		this.idsArr = idsArr;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getPosition_id() {
		return position_id;
	}
	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public String getPeriod_type() {
		return period_type;
	}
	public void setPeriod_type(String period_type) {
		this.period_type = period_type;
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
	public String getTask_value() {
		return task_value;
	}
	public void setTask_value(String task_value) {
		this.task_value = task_value;
	}
	public String getTask_unit() {
		return task_unit;
	}
	public void setTask_unit(String task_unit) {
		this.task_unit = task_unit;
	} 
	
}
