package com.aus.authority.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.aus.authority.exception.AuthorityException;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.TreeDTO;
import com.aus.authority.service.AuthorityService;
import com.aus.authority.service.RoleService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.common.util.OAUtils;
import com.aus.common.util.SpringContextHolder;

/**
 * 权限核心API
 * @author duzh
 *
 */
public class AuthorityUtils {
	private static Logger logger = Logger.getLogger(AuthorityUtils.class);
	static AuthorityService authorityService;
	
	private static AuthorityService getAuthorityService() {
		if (authorityService == null) {
				authorityService = SpringContextHolder.getBean("authorityService");
		}
		return authorityService;
	}
	
	private static List<String> v_language_list;
	
//	private static Map<String,List<String>> dictdata;
	
       
    /**
     * 
     * 初始化系统变量,语言等
     * 
     */
	public static void initSysEnvironment() {   
    	
    	RoleService roleService = SpringContextHolder.getBean("roleService");
    	
    	v_language_list = roleService.findAllSysLanguage();
    	
//    	dictdata = getAuthorityService().buildResponsibilityAttrsMap();
    	
    }
    
    public static List<String> findSysLanguage() {   	
    	return v_language_list;
    }
    
	
	/**
	 * 登录
	 * 
	 * @param sessionId
	 *            会话ID
	 * @param userCode
	 *            用户编号
	 */
	public static void userLogin(String sessionId, String userCode) {
		getAuthorityService().userLogin(sessionId, userCode);
	}
	
	/**
	 * 用户退出
	 * 
	 * @param sessionId
	 *            会话ID
	 */
	public static void userLogout(String sessionId) {
		getAuthorityService().userLogout(sessionId);
	}
	
	/**
	 * 是否已登录
	 * 
	 * @param sessionId
	 *            TODO
	 * @return 登录结果
	 */
	public static boolean isLogonUser(String sessionId) {
		return getAuthorityService().isLogonUser(sessionId);
	}

	
	
	/**
	 * 根据单据提交的人员返回prifile值列表
	 * 
	 * @param personId
	 * 		             单据提交人员Id
	 * @param profileName
	 * 			 profile名称
	 * @param url
	 * 	                        当前访问的URL
	 * @return
	 */
	public static List<String> findUserProfileValList(String userId, String profileName, String url) {
		return getAuthorityService().findPersonProfileValList(userId,profileName,url);	
	}
	
	/**
	 * 根据访问的功能返回prifile值列表
	 * 
	 * @param sessionId
	 * 		             会话ID
	 * @param profileName
	 * 			 profile名称
	 * @param url
	 * 	                        当前访问的URL
	 * @return
	 */
	public static List<String> findProfileValList(String sessionId, String profileName, String url) {		
		return getAuthorityService().findProfileValList(sessionId,profileName,url);		
	}
	
	/**
	 * 根据访问的功能返回匹配的第一个prifile值
	 * 
	 * @param sessionId
	 * @param profileName
	 * @param url
	 * @return
	 */
	public static String findPrifileValFirst(String sessionId, String profileName, String url) {		
		return getAuthorityService().findPrifileValFirst(sessionId,profileName,url);		
	}	
	
	/**
	 * 是否可以操作
	 * 
	 * @param sessionId
	 *            用户编号
	 * @param resourceCode
	 *            资源编号
	 * @return 
	 */
	public static boolean isCanOperateResource(HttpServletRequest request, String resourceCode,String url) {
	 
		ShiroUser shiroUser = OAUtils.getUser();
		
		Map<String, String> urlResponsibilityMap =authorityService.getUrlResponsibilityMap(shiroUser.getUserId(), request);

		if(urlResponsibilityMap == null ) {
			return false;
		}
		
		String responsibilityId = urlResponsibilityMap.get(url);
		
		if ( StringUtils.isBlank(responsibilityId) ) {
			return false;
		}
		
		return getAuthorityService().isCanOperateResource(responsibilityId,request.getSession().getId(),resourceCode,url);
	}
	
	/**
	 * 获取用户导航
	 * 
	 * @param sessionId
	 *            用户编号
	 * @return 导航
	 */
	public static List<TreeDTO> findLogonNavigationTrees(String sessionId,String lang) {
		return getAuthorityService().findLogonNavigationTrees(sessionId,lang);
	}
		
	/**
	 * 
	 * 通过功能获取用户职责
	 * 
	 */
	public static List<ResponsibilityDto> findUserResponsibilitys(String sessionId,String url) {
		return getAuthorityService().findUserResponsibilitys(sessionId,url);
	}
	
	/**
	 * 
	 * 通过功能获取用户职责
	 * 
	 */
	public static List<ResponsibilityDto> findResponsibilitysByUserId(String userId,String url) {
		return getAuthorityService().findResponsibilitysByUserId(userId,url);
	}
		
	/**
	 * 
	 * 通过功能获取用户职责
	 * 
	 */
	public static List<String> findUserResponsibilityArrays(String sessionId,String url) {
		
		List<String> responsibilitys = new ArrayList<String>();
		
		List<ResponsibilityDto> dtos =  getAuthorityService().findUserResponsibilitys(sessionId,url);
		
		for(ResponsibilityDto dto : dtos) {
			responsibilitys.add(dto.getResponsibilityId());
		}
		
		return responsibilitys;
	}
	
	
	/**
	 * 根据职责返回对应prifile值
	 * 
	 * @param sessionId
	 * @param profileName
	 * @param url
	 * @return
	 */
	public static String findPrifileValByResponsibility(HttpServletRequest request, String profileName, String url) {
		ShiroUser shiroUser = OAUtils.getUser();
		
		Map<String, String> urlResponsibilityMap =authorityService.getUrlResponsibilityMap(shiroUser.getUserId(), request);
		
		if( urlResponsibilityMap == null ) {
			return null;
		}

		String responsibilityId = urlResponsibilityMap.get(url);
		
		if( StringUtils.isBlank(responsibilityId)) {
			throw new AuthorityException("没有权限访问此功能");
		}
		
		if ( !isLogonUser(request.getSession().getId()) ) {
			return null;
		}
		
		List<String> responsibilitys = findUserResponsibilityArrays(request.getSession().getId(),url);
		
		if( !responsibilitys.contains(responsibilityId)) {
			return null;
		}
		
		return getAuthorityService().findPrifileValByResponsibility(responsibilityId,profileName);	
		
	}
	
		
	/**
	 * 
	 * 通过职责获取用户资源
	 * 
	 */
	public static List<String> findUserResources(String responsibilityId,String sessionId) {
		return getAuthorityService().findUserResources(responsibilityId,sessionId);
	}
				
	/**
	 * 返回用户角色
	 * 
	 * @param sessionId
	 * @return
	 */
	public static List<String> findUserRoles(String sessionId) {		
		return getAuthorityService().findUserRoles(sessionId);
	}
	
	/**
	 * 查询用户编号
	 * 
	 * @param sessionId
	 * @return
	 */
	public static String findLogonUserCode(String sessionId) {
		return getAuthorityService().findLogonUserCode(sessionId);
	}
		
	
	/**
	 * 
	 * 通过功能用户得到默认职责
	 * 
	 */
	public static String setResponsibiltyKey(HttpServletRequest request,String url ) {	
		ShiroUser shiroUser = OAUtils.getUser();
		
		Map<String, String> urlResponsibilityMap = authorityService.getUrlResponsibilityMap(shiroUser.getUserId(), request);

		if (urlResponsibilityMap == null) {
			urlResponsibilityMap = new HashMap<String, String>();

			request.getSession().setAttribute("urlResponsibilityMap", urlResponsibilityMap);
		}
 
		
		if( StringUtils.isNotBlank(urlResponsibilityMap.get(url))) {			
			return urlResponsibilityMap.get(url);			
		}		
		
		String responsibilityId =  getAuthorityService().findResponsibiltyKey(request.getSession().getId(),url);
		
		if( StringUtils.isBlank(responsibilityId)) {
			throw new AuthorityException("没有权限访问此功能");
		}
	
		urlResponsibilityMap.put(url, responsibilityId);
 
		authorityService.setUrlResponsibilityMap(shiroUser.getUserId(), urlResponsibilityMap, request);
		
		return responsibilityId;
		
	}
	
    /**
     * 得到当前功能职责ID
     * @param request
     * @param url
     * @return
     */
	public static String getResponsebilityId(HttpServletRequest request,String url) {    	
		ShiroUser shiroUser = OAUtils.getUser();
		
		Map<String, String> urlResponsibilityMap =authorityService.getUrlResponsibilityMap(shiroUser.getUserId(), request);

		if(urlResponsibilityMap == null ) {
			return null;
		}
		
    	return urlResponsibilityMap.get(url);
    }
	
	public static void accreditFlush() {
		 getAuthorityService().accreditFlush();
	}
	
    /**
     * 
     * 返回profile值列表
     * 
     * @param name
     * @return
     */
    public static List<Map<String,String>> getProDictByName(String name) throws Exception{
		return getAuthorityService().getProDictByName(name);
    }
	/**
	 * 清除Memcached缓存
	 */
	public static void cleanMemcached() { 
		try {
			SpyMemcachedClient client = SpringContextHolder.getBean("spyMemcachedClient");
			client.getMemcachedClient().flush(); 
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
	}

	public static void accreditFlushUserResponsibility(String userId) {
		 getAuthorityService().accreditFlushUserResponsibility(userId);
		
	}
}
