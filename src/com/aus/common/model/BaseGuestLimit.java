package com.aus.common.model;

import java.util.Date;

public class BaseGuestLimit {
private String user_id;
private String guser_limit_id;
private String fun_id;
private String is_delete;
private Date creation_date;
private String creation_by;
private Date last_update_date;
private String last_update_by;
private String [] departmemtIds;
private String hr_organization_id;
private String hr_organization_name;
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}


public String getGuser_limit_id() {
	return guser_limit_id;
}
public void setGuser_limit_id(String guser_limit_id) {
	this.guser_limit_id = guser_limit_id;
}
public String getFun_id() {
	return fun_id;
}
public void setFun_id(String fun_id) {
	this.fun_id = fun_id;
}
public String getIs_delete() {
	return is_delete;
}
public void setIs_delete(String is_delete) {
	this.is_delete = is_delete;
}
public Date getCreation_date() {
	return creation_date;
}
public void setCreation_date(Date creation_date) {
	this.creation_date = creation_date;
}
public String getCreation_by() {
	return creation_by;
}
public void setCreation_by(String creation_by) {
	this.creation_by = creation_by;
}
public Date getLast_update_date() {
	return last_update_date;
}
public void setLast_update_date(Date last_update_date) {
	this.last_update_date = last_update_date;
}
public String getLast_update_by() {
	return last_update_by;
}
public void setLast_update_by(String last_update_by) {
	this.last_update_by = last_update_by;
}
public String[] getDepartmemtIds() {
	return departmemtIds;
}
public void setDepartmemtIds(String[] departmemtIds) {
	this.departmemtIds = departmemtIds;
}
public String getHr_organization_id() {
	return hr_organization_id;
}
public void setHr_organization_id(String hr_organization_id) {
	this.hr_organization_id = hr_organization_id;
}
public String getHr_organization_name() {
	return hr_organization_name;
}
public void setHr_organization_name(String hr_organization_name) {
	this.hr_organization_name = hr_organization_name;
}


}
