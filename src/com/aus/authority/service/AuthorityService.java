package com.aus.authority.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.TreeDTO;

public interface AuthorityService {
	
	/**
	 * 清除授权缓存
	 */
	public void accreditFlush();
	
	/**
	 * 登录
	 * 
	 * @param sessionId
	 *            TODO
	 * @param userCode
	 *            用户编号
	 */
	public void userLogin(String sessionId, String userCode);
	
	
	/**
	 * 用户退出
	 * 
	 * @param sessionId
	 */
	public void userLogout(String sessionId);
		
	/**
	 * 是否已登录
	 * 
	 * @param sessionId
	 *            TODO
	 * @return 登录结果
	 */
	public boolean isLogonUser(String sessionId);
	
	
	/**
	 * 清除登录缓存
	 */
	public void logonCacheFlush();

	/**
	 * 清除session缓存
	 */
	public void sessionCacheFlush();

	
	/**
	 * 是否用户资源
	 * 
	 * @param sessionId
	 *            用户编号
	 * @param resourceCode
	 *            资源编号
	 * @return 是否有权限查看
	 */
	public boolean isLogonResource(String sessionId, String resourceCode);
	
	
	/**
	 * 得到用户导航树
	 * @param sessionId
	 * @return
	 */
	public List<TreeDTO> findLogonNavigationTrees(String sessionId,String lang);

	/**
	 * 查询用户编号
	 * 
	 * @param sessionId
	 * @return
	 */	
	public String findLogonUserCode(String sessionId);

	/**
	 * 是否可以操作/显示
	 * 
	 * @param sessionId
	 * @param resourceCode
	 * @param url
	 * @param url2 
	 * @return
	 */
	public boolean isCanOperateResource(String responsibiityId,String sessionId, String resourceCode,
			String url);

	/**
	 * 
	 * 根据访问的功能返回prifile值列表
	 * @param sessionId
	 * @param profileName
	 * @param url
	 * @return
	 */
	public List<String> findProfileValList(String sessionId,
			String profileName, String url);

	/**
	 * 
	 * 得到用户角色
	 * 
	 * @param sessionId
	 * @return
	 */
	public List<String> findUserRoles(String sessionId);

	
	/**
	 * 得到用户资源
	 * 
	 * @param sessionId
	 * @return
	 */
	public List<String> findUserResources(String responsibilityId,String sessionId);

	/**
	 * 
	 * 根据功能得到用户职责
	 * @param sessionId
	 * @param url 
	 * @return
	 */
	public List<ResponsibilityDto> findUserResponsibilitys(String sessionId, String url);

	
	/**
	 * 
	 * 根据访问的功能返回匹配的第一个prifile值
	 * @param sessionId
	 * @param profileName
	 * @param url
	 * @return
	 */
	public String findPrifileValFirst(String sessionId, String profileName,
			String url);

	
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
	public List<String> findPersonProfileValList(String userId,
			String profileName, String url);

	/**
	 * 
	 * 通过功能用户得到默认职责
	 * 
	 */
	public String findResponsibiltyKey(String sessionId, String url);

	/**
	 * 根据职责返回对应prifile值
	 * 
	 * @param sessionId
	 * @param profileName
	 * @param url
	 * @return
	 */
	public String findPrifileValByResponsibility(String responsibilityId,String profileName);

	/**
	 * 
	 * 初始化profile数据
	 * 
	 * @return
	 */
	public Map<String, List<String>> buildResponsibilityAttrsMap();

	
	public List<ResponsibilityDto> findResponsibilitysByUserId(String userId,
			String url);

	/**
	 * 
	 * 得到profile值集
	 * 
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String,String>> getProDictByName(String name)  throws Exception;



	/**
	 * 获取UrlResponsibility
	 * @param userId
	 * @param request
	 * @return
	 */
	public Map<String, String> getUrlResponsibilityMap(String userId,HttpServletRequest request) ;
	
	/**
	 * 设置UrlResponsibility
	 * @param userId
	 * @param urlResponsibilityMap
	 * @param request
	 */
	public void setUrlResponsibilityMap(String userId,Map<String, String> urlResponsibilityMap,HttpServletRequest request);

	/**
	 * 清除授权缓存(用户职责)
	* @Title: accreditFlushUserResponsibility 
	* @time 2016年1月11日 下午5:02:19  
	* @user "LiangYi"
	 */
	public void accreditFlushUserResponsibility(String userId);



}
