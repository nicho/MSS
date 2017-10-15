package com.aus.common.model;

public class SubinvInfo {
	String subinvCode;
	String subinvName;
	String parent_subinv_code;
	
	public String getParent_subinv_code() {
		return parent_subinv_code;
	}
	public void setParent_subinv_code(String parent_subinv_code) {
		this.parent_subinv_code = parent_subinv_code;
	}
	public String getSubinvCode() {
		return subinvCode;
	}
	public void setSubinvCode(String subinvCode) {
		this.subinvCode = subinvCode;
	}
	public String getSubinvName() {
		return subinvName;
	}
	public void setSubinvName(String subinvName) {
		this.subinvName = subinvName;
	} 
 
}
