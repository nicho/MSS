package com.aus.authority.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.Encodes;

import com.aus.authority.model.UserDto;
import com.aus.common.util.DateUtil;
import com.aus.common.util.LocaleUtil;
import com.aus.common.util.ReadProperties;
import com.aus.common.util.SpringContextHolder;
import com.aus.user.util.Constant;
import com.google.common.base.Objects;

/**
 * 
 * 用户认证数据源
 * 
 * @author duzh
 *
 */
public class ShiroDbRealm extends AuthorizingRealm {

	protected UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authcToken;

		if (StringUtils.isBlank(usernamePasswordToken.getUsername())) {
			throw new AuthenticationException("用户名不能为空值!");
		}

		if (ArrayUtils.isEmpty(usernamePasswordToken.getPassword())) {
			throw new AuthenticationException("密码不能为空值!");
		}

		usernamePasswordToken.setPassword(new String(Base64.decodeBase64(String.valueOf(usernamePasswordToken.getPassword()).getBytes())).toCharArray());

		usernamePasswordToken.setUsername(usernamePasswordToken.getUsername().replaceAll("——", "-").replaceAll("一", "-").trim());

		UserDto user = userService.findUserByUserName(usernamePasswordToken.getUsername());

		if (user == null) {
			throw new AuthenticationException("用户名密码错误");
		}

		if (DateUtil.compareDate(user.getBeginDate()) > 0) {
			throw new AuthenticationException("帐号未生效!");
		}

		if (DateUtil.compareDate(user.getEndDate()) <= 0) {
			throw new DisabledAccountException("帐号已失效!");
		}

		// 判断域名,和登录的用户类型
		String systemType = ReadProperties.getDomainMap().get("SYSTEMTYPE");

		byte[] salt = Encodes.decodeHex(user.getSalt());

		ShiroUser shiroUser = new ShiroUser();

		BeanUtils.copyProperties(user, shiroUser);

		shiroUser.setUserAccount(user.getCustAccountId());

		shiroUser.setName(user.getPersonName());

		return new SimpleAuthenticationInfo(shiroUser, user.getPassword(), ByteSource.Util.bytes(salt), getName());

	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用. 此项目不采用此方式
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Constant.HASH_ALGORITHM);
		matcher.setHashIterations(Constant.HASH_INTERATIONS);

		setCredentialsMatcher(matcher);
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;

		public String userId;
		public String personId;
		public String userAccount;
		public String userName;
		public String name;
		public String storeCode;
		public String storeName;
		public String language;
		public String orgId;
		public String subinvType;

		public ShiroUser(String userId, String userName, String name) {
			this.userId = userId;
			this.userName = userName;
			this.name = name;
		}

		public String getOrgId() {
			if (StringUtils.isEmpty(orgId)) {
				// 设置用户机构ID,提供给导购模块使用
				if (!StringUtils.isEmpty(personId)) {
					UserService userService = SpringContextHolder.getBean("userService");
					String orgId = userService.getPersonOrgId(personId);
					if (!StringUtils.isEmpty(orgId)) {
						this.orgId = orgId;
					}
				}
			}
			return orgId;
		}

		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public ShiroUser() {
		}

		public String getName() {
			return name;
		}

		public String getSubinvType() {
			return subinvType;
		}

		public void setSubinvType(String subinvType) {
			this.subinvType = subinvType;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPersonId() {
			return personId;
		}

		public void setPersonId(String personId) {
			this.personId = personId;
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

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStoreCode() {
			return storeCode;
		}

		public void setStoreCode(String storeCode) {
			this.storeCode = storeCode;
		}

		public String getLanguage() {
			return LocaleUtil.getLocaleLanguage();
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return userAccount;
		}

		/**
		 * 重载hashCode,只计算userAccount;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(userAccount);
		}

		/**
		 * 重载equals,只计算userAccount;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (userAccount == null) {
				if (other.userAccount != null) {
					return false;
				}
			} else if (!userAccount.equals(other.userAccount)) {
				return false;
			}
			return true;
		}

	}

}
