package com.aus.common.model;

import java.util.Date;

public class CuxOaLoginLogDto {
private String login_id; //ID
private String user_id; //用户ID
private String ip;//IP
private Date login_date;//登录时间
private String user_agent;//浏览器及操作系统信息
private String url;//登录URL
public String getLogin_id() {
	return login_id;
}
public void setLogin_id(String login_id) {
	this.login_id = login_id;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public Date getLogin_date() {
	return login_date;
}
public void setLogin_date(Date login_date) {
	this.login_date = login_date;
}

public String getUser_agent() {
	return user_agent;
}
public void setUser_agent(String user_agent) {
	this.user_agent = user_agent;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}

}
