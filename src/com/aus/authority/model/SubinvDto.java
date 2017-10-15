package com.aus.authority.model;

public class SubinvDto {
	private String subinv_code;
	private String subinv_name;
	private String subinv_type;
	private String channel_type;
	private String account_name;
	private String cust_account_id;

	public String getCust_account_id() {
		return cust_account_id;
	}

	public void setCust_account_id(String cust_account_id) {
		this.cust_account_id = cust_account_id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}

	public String getSubinv_type() {
		return subinv_type;
	}

	public void setSubinv_type(String subinv_type) {
		this.subinv_type = subinv_type;
	}

	public String getSubinv_code() {
		return subinv_code;
	}

	public void setSubinv_code(String subinv_code) {
		this.subinv_code = subinv_code;
	}

	public String getSubinv_name() {
		return subinv_name;
	}

	public void setSubinv_name(String subinv_name) {
		this.subinv_name = subinv_name;
	}

}
