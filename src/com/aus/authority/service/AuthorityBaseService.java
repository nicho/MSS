package com.aus.authority.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.aus.authority.util.AuthorityUtils;
import com.aus.authority.util.Function;

import net.sf.ehcache.Element;

/**
 * 权限API
 * @author duzh
 *
 */
@SuppressWarnings("rawtypes")
public abstract class AuthorityBaseService implements AuthorityService {
	
	
	protected Cache accreditCache;

	protected CacheManager shiroEhcacheManager;

	protected Cache logonCache;

	protected Cache sessionCache;
	
	/**
	 * @param shiroEhcacheManager
	 */
	@Autowired
	public void setshiroEhcacheManager(CacheManager shiroEhcacheManager) {
		this.shiroEhcacheManager = shiroEhcacheManager;
		this.accreditCache =  this.shiroEhcacheManager.getCache("accreditCache");
		this.logonCache =  this.shiroEhcacheManager.getCache("logonCache");
		this.sessionCache =  this.shiroEhcacheManager.getCache("sessionCache");
	}
	
	
	public String findLogonUserCode(String sessionId) {
		
		Map<String, Object> key = this.getUserKey(sessionId);

		Object v = this.getCache(logonCache, key, null);

		return (String) v;
	}
	
	
	public void logonCacheFlush() {
		this.logonCache.clear();
	}

	
	public void sessionCacheFlush() {
		this.sessionCache.clear();
	}
	
	public void accreditFlush() {
		this.accreditCache.clear();
		AuthorityUtils.cleanMemcached();
	}
	
		
	/**
	 * 获取缓存
	 * 
	 * @param cache
	 *            缓存管理类
	 * @param key
	 *            缓存key
	 * @param function
	 *            委托
	 * @return 缓存值
	 */
	@SuppressWarnings("unchecked")
	protected Object getCache(Cache cache, Object key, Function<Object> function) {
		Element element = (Element) cache.get(key);

		Object o = null;

		if (element == null) {
			if (function != null) {
				o = function.execute();

				this.setCache(cache, key, o);
			}
		} else {
			o = element.getObjectValue();
		}

		return o;
	}
	
	/**
	 * 设置缓存
	 * 
	 * @param cache
	 *            缓存管理类
	 * @param key
	 *            缓存key
	 * @param value
	 *            缓存值
	 */
	@SuppressWarnings("unchecked")
	protected void setCache(Cache cache, Object key, Object value) {
		Element element = new Element(key, value);

		cache.put(key, element);
	}
	
	private Map<String, Object> getUserKey(String sessionId) {
		Map<String, Object> key = new HashMap<String, Object>();

		key.put("userCode", sessionId);

		return key;
	}
	
	public void userLogin(String sessionId, String userCode) {
		Map<String, Object> key = getUserKey(sessionId);

		this.setCache(logonCache, key, userCode);
	}

	
	@SuppressWarnings("unchecked")
	public void userLogout(String sessionId) {
		Map<String, Object> key = getUserKey(sessionId);

		this.logonCache.remove(key);
	}
	
	public boolean isLogonUser(String sessionId) {
		return StringUtils.isNotBlank(this.findLogonUserCode(sessionId));
	}


}
