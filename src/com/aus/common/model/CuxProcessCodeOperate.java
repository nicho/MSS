package com.aus.common.model;

import java.util.Date;

public class CuxProcessCodeOperate {

	private String process_id;//流程委托配置表ID
	
	private String process_code;//流程编码
	
	private String process_name;//流程名称
	
	private String cilent_user_id;//委托人ID
	
	private String be_client_user_id;//被委托人ID
	
	private String client_user_name;//委托人姓名
	
	private String be_client_user_name;//被委托人
	
	private String begin_date_str;//开始时间
	
	private Date begin_date; // 开始时间
	
	private String end_date_str; //结束时间
	
	private Date end_date; // 结束时间
	
	private String creation_date_str;
	
	private String creation_by;
	
	private String last_update_date_str;
	
	private String last_update_by;
	
	private String  last_update_login;
	
	private String user_id;//用户ID

	private String ou_organization_id; //组织
	
	private String cilent_person_id;//委托人人员ID
	
	private String be_client_person_id; //
	private String person_name;//委托人姓名
	
	private String be_user_account;//被委托人的userAccount;
	
	private String user_account;//委托人userAccount;
	
	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}

	public String getProcess_code() {
		return process_code;
	}

	public void setProcess_code(String process_code) {
		this.process_code = process_code;
	}

	public String getCilent_user_id() {
		return cilent_user_id;
	}

	public void setCilent_user_id(String cilent_user_id) {
		this.cilent_user_id = cilent_user_id;
	}

	public String getBegin_date_str() {
		return begin_date_str;
	}

	public void setBegin_date_str(String begin_date_str) {
		this.begin_date_str = begin_date_str;
	}

	public String getEnd_date_str() {
		return end_date_str;
	}

	public void setEnd_date_str(String end_date_str) {
		this.end_date_str = end_date_str;
	}

	public String getCreation_date_str() {
		return creation_date_str;
	}

	public void setCreation_date_str(String creation_date_str) {
		this.creation_date_str = creation_date_str;
	}

	public String getCreation_by() {
		return creation_by;
	}

	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}

	public String getLast_update_date_str() {
		return last_update_date_str;
	}

	public void setLast_update_date_str(String last_update_date_str) {
		this.last_update_date_str = last_update_date_str;
	}

	public String getLast_update_by() {
		return last_update_by;
	}

	public void setLast_update_by(String last_update_by) {
		this.last_update_by = last_update_by;
	}

	public String getLast_update_login() {
		return last_update_login;
	}

	public void setLast_update_login(String last_update_login) {
		this.last_update_login = last_update_login;
	}

	public String getBe_client_user_id() {
		return be_client_user_id;
	}

	public void setBe_client_user_id(String be_client_user_id) {
		this.be_client_user_id = be_client_user_id;
	}

	public String getClient_user_name() {
		return client_user_name;
	}

	public void setClient_user_name(String client_user_name) {
		this.client_user_name = client_user_name;
	}

	public String getBe_client_user_name() {
		return be_client_user_name;
	}

	public void setBe_client_user_name(String be_client_user_name) {
		this.be_client_user_name = be_client_user_name;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getOu_organization_id() {
		return ou_organization_id;
	}

	public void setOu_organization_id(String ou_organization_id) {
		this.ou_organization_id = ou_organization_id;
	}

	public String getCilent_person_id() {
		return cilent_person_id;
	}

	public void setCilent_person_id(String cilent_person_id) {
		this.cilent_person_id = cilent_person_id;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getBe_client_person_id() {
		return be_client_person_id;
	}

	public void setBe_client_person_id(String be_client_person_id) {
		this.be_client_person_id = be_client_person_id;
	}

	public String getBe_user_account() {
		return be_user_account;
	}

	public void setBe_user_account(String be_user_account) {
		this.be_user_account = be_user_account;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	
	
	
	
}
