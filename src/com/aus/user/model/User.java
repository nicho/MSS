package com.aus.user.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class User {
	private String id;
	private String login_Name;
	private String name;
	private String password;
	private String salt;
	private String roles;
	private Date register_Date;

	public List<String> getRoleList() {
		// 角色列表在数据库中实际以逗号分隔字符串存储，因此返回不能修改的List.
		List<String> rolelist = new ArrayList<String>();
		if (!StringUtils.isEmpty(roles)) {
			String[] roleArray = StringUtils.split(roles, ",");
			if (roleArray != null) {
				for (int i = 0; i < roleArray.length; i++) {
					rolelist.add(roleArray[i]);
				}
			} else {
				rolelist.add(roles);
			}
		}
		return rolelist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getLogin_Name() {
		return login_Name;
	}

	public void setLogin_Name(String login_Name) {
		this.login_Name = login_Name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Date getRegister_Date() {
		return register_Date;
	}

	public String getRegisterDateStr() {
		String str = "";
		if (register_Date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str = sdf.format(register_Date);
		}
		return str;
	}

	public void setRegister_Date(Date register_Date) {
		this.register_Date = register_Date;
	}

}