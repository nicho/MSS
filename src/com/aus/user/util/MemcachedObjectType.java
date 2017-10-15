package com.aus.user.util;

/**
 * 统一定义Memcached中存储的各种对象的Key前缀和超时时间.
 * 
 */
public enum MemcachedObjectType {
	USER("user:", 60 * 60 * 1),
	WEIXIN("weixin:", 7000),//缓存(秒) 用于微信
	PCEMAIL("pcemail:", 400),  //缓存(秒) 用于邮箱
	USERINDEX("userindex:", 3600*8),  //缓存用于首页
	FINDLOGONNAVIGATIONTREES("findLogonNavigationTrees:", 3600*8),  //用于权限模块缓存
	FINDUSERROLES("findUserRoles:", 3600*8),  
	FINDPROFILEVALLIST("findProfileValList:", 3600*8),  
	FINDUSERRESOURCES("findUserResources:", 3600*8),  
	FINDUSERRESPONSIBILITYS("findUserResponsibilitys:", 3600*8),  
	FINDPRIFILEVALFIRST("findPrifileValFirst:", 3600*8),  
	FINDPERSONPROFILEVALLIST("findPersonProfileValList:", 3600*8),  
	FINDPRIFILEVALBYRESPONSIBILITY("findPersonProfileValList:", 3600*8),  
	FINDRESPONSIBILITYSBYUSERID("findResponsibilitysByUserId:", 3600*8),  
	FLUSHUSERRESPONSIBILITY("flushUserResponsibility:", 3600*8),  
	ISCANOPERATERESOURCE("isCanOperateResource:", 3600*8);  
	private String prefix;
	private int expiredTime;

	MemcachedObjectType(String prefix, int expiredTime) {
		this.prefix = prefix;
		this.expiredTime = expiredTime;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiredTime() {
		return expiredTime;
	}

}
