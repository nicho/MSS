package com.aus.authority.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.aus.authority.dao.ExecutionStringSqlDao;
import com.aus.authority.dao.IAuthorityDao;
import com.aus.authority.model.NavigationDto;
import com.aus.authority.model.ProfileDto;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.TreeDTO;
import com.aus.authority.service.AuthorityBaseService;
import com.aus.authority.service.AuthorityService;
import com.aus.authority.service.ProfileService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.common.util.OAUtils;
import com.aus.common.util.SpringContextHolder;
import com.aus.user.util.MemcachedObjectType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 权限API
 * 
 * @author duzh
 *
 */
@Service("authorityService")
public class AuthorityServiceImpl extends AuthorityBaseService implements AuthorityService {
	private static Logger logger = Logger.getLogger(AuthorityServiceImpl.class);
	@Autowired
	private IAuthorityDao dao;
	@Autowired(required = false)
	private SpyMemcachedClient memcachedClient;

	public void authorityCacheFlush() {

	}

	public boolean isLogonResource(String sessionId, String resourceCode) {
		return false;
	}

	/**
	 * 
	 * 根据当前用户返回菜单树
	 * 
	 */
	public List<TreeDTO> findLogonNavigationTrees(final String sessionId, final String lang) {

		if (!this.isLogonUser(sessionId)) {
			return null;
		}
	
		ShiroUser shiroUser = OAUtils.getUser();

		String key = MemcachedObjectType.FINDLOGONNAVIGATIONTREES.getPrefix() + shiroUser.getUserId() + "_" + lang;

		String navigationtreesToken =null;
		try {
			navigationtreesToken=memcachedClient.get(key); 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();

		List<TreeDTO> treeDtoList = null;
		if (StringUtils.isEmpty(navigationtreesToken)) {
			//从数据库取数据
			 
			List<NavigationDto> v_navigation_list = dao.findLogonNavigationTrees(shiroUser.getUserId());

			treeDtoList = createTreeDto(v_navigation_list);
			try {
				navigationtreesToken = mapper.writeValueAsString(treeDtoList); 
				memcachedClient.set(key, MemcachedObjectType.FINDLOGONNAVIGATIONTREES.getExpiredTime(), navigationtreesToken);
				putUserKey(key);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据 
			try {
				treeDtoList = mapper.readValue(navigationtreesToken, new TypeReference<List<TreeDTO>>() {});
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return treeDtoList;
	}

	private List<TreeDTO> createTreeDto(List<NavigationDto> v_navigation_list) {

		Map<String, TreeDTO> map = new HashMap<String, TreeDTO>();

		List<String> roots = new ArrayList<String>();

		List<TreeDTO> tree = new ArrayList<TreeDTO>();

		// 第一次遍历生成所有节点
		for (NavigationDto navigation : v_navigation_list) {
			TreeDTO node = new TreeDTO();

			copyProperties(navigation, node);

			map.put(node.getId(), node);

			if (StringUtils.equals(node.getParentId(), "0")) {

				roots.add(node.getId());
			}
		}

		// 第二次遍历根据节点关系建立菜单节点树
		for (NavigationDto navigation : v_navigation_list) {

			TreeDTO parentNode = map.get(navigation.getParentNodeId());

			if (parentNode != null) {
				parentNode.getChildren().add(map.get(navigation.getMenuNodeId()));
			}

		}
		// 返回所有第一级节点
		for (String id : roots) {

			tree.add(map.get(id));
		}

		return tree;
	}

	private void copyProperties(NavigationDto dto, TreeDTO node) {

		node.setId(dto.getMenuNodeId());

		node.setParentId(dto.getParentNodeId());

		node.setText(dto.getMenuNodeName());

		node.setSort(dto.getOrderNum());

		node.setUrl(dto.getUrl());

		node.setNodeType(dto.getMenuNodeType());

		node.setNodeCode(dto.getMenuNodeCode());

		node.setIcon(dto.getIcon());

	}

	public boolean isCanOperateResource(final String responsilityId, final String sessionId, final String resourceCode, final String url) {

		if (!this.isLogonUser(sessionId)) {
			return false;
		}
		ShiroUser shiroUser = OAUtils.getUser();

		String key = MemcachedObjectType.ISCANOPERATERESOURCE.getPrefix() + shiroUser.getUserId() + "_" + responsilityId;

		String iscanoperateresource =null;
		try {
			 iscanoperateresource=memcachedClient.get(key);   
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<String> resources=null;
		if (StringUtils.isEmpty(iscanoperateresource)) {
			//从数据库取数据
			resources= findUserResources(responsilityId, sessionId);

			try {
				iscanoperateresource = mapper.writeValueAsString(resources); 
				memcachedClient.set(key, MemcachedObjectType.ISCANOPERATERESOURCE.getExpiredTime(), iscanoperateresource);
				putUserKey(key);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据 
			try {
				resources = mapper.readValue(iscanoperateresource, new TypeReference<List<String>>() {});
			} catch (Exception e) {
				logger.error(e);
			}
		}
		Boolean v=false;
		if(resources!=null && !StringUtils.isEmpty(resourceCode))
		{
			v=resources.contains(resourceCode); 
		}
 
		return Boolean.TRUE.equals(v);
	}
 
	public List<String> findProfileValList(String sessionId, final String profileName, final String url) {

		if (!this.isLogonUser(sessionId)) {
			return null;
		}
		ShiroUser shiroUser = OAUtils.getUser();
		
		String key = MemcachedObjectType.FINDPROFILEVALLIST.getPrefix() + shiroUser.getUserId() + "_" + profileName + "_" + url;

		String accreditcache =null;
		try {
			accreditcache=memcachedClient.get(key); 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<String> v=null;
		if (StringUtils.isEmpty(accreditcache)) {
			
			
			//从数据库取数据
			v= dao.findProfileValList(shiroUser.getUserId(), profileName, url);

			try {
				accreditcache = mapper.writeValueAsString(v); 
				memcachedClient.set(key, MemcachedObjectType.FINDPROFILEVALLIST.getExpiredTime(), accreditcache);
				putUserKey(key);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据 
			try {
				v = mapper.readValue(accreditcache, new TypeReference<List<String>>() {});
			} catch (Exception e) {
				logger.error(e);
			}
		}
	 
		return (List<String>) v;
	}

	/**
	 * 得到用户角色
	 * 
	 */ 
	public List<String> findUserRoles(String sessionId) {

		if (!this.isLogonUser(sessionId)) {
			return null;
		}
		ShiroUser shiroUser = OAUtils.getUser();
		
		String key = MemcachedObjectType.FINDUSERROLES.getPrefix() + shiroUser.getUserId() + "_" ;

		String userRoles =null;
		try {
			userRoles=memcachedClient.get(key); 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<String> v=null;
		if (StringUtils.isEmpty(userRoles)) {
			
			
			//从数据库取数据
			v= dao.findUserRoles(shiroUser.getUserId());

			try {
				userRoles = mapper.writeValueAsString(v); 
				memcachedClient.set(key, MemcachedObjectType.FINDUSERROLES.getExpiredTime(), userRoles);
				putUserKey(key);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据 
			try {
				v = mapper.readValue(userRoles, new TypeReference<List<String>>() {});
			} catch (Exception e) {
				logger.error(e);
			}
		}
		  
		return (List<String>) v;
	}

	/**
	 * 
	 * 得到用户资源
	 * 
	 */
	public List<String> findUserResources(final String responsilityId, String sessionId) {

		if (!this.isLogonUser(sessionId)) {
			return null;
		}
		ShiroUser shiroUser = OAUtils.getUser();
		
		String key = MemcachedObjectType.FINDUSERRESOURCES.getPrefix() + shiroUser.getUserId() + "_" +responsilityId;

		String userResources =null;
		try {
			userResources=memcachedClient.get(key); 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<String> v=null;
		if (StringUtils.isEmpty(userResources)) { 
			
			//从数据库取数据
			v= dao.findUserResources(responsilityId, shiroUser.getUserId());

			try {
				userResources = mapper.writeValueAsString(v); 
				memcachedClient.set(key, MemcachedObjectType.FINDUSERRESOURCES.getExpiredTime(), userResources);
				putUserKey(key);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据 
			try {
				v = mapper.readValue(userResources, new TypeReference<List<String>>() {});
			} catch (Exception e) {
				logger.error(e);
			}
		}
		 
		return (List<String>) v;
	}

	/**
	 * 
	 * 得到用户职责
	 * 
	 */
	public List<ResponsibilityDto> findUserResponsibilitys(String sessionId, final String url) {

		if (!this.isLogonUser(sessionId)) {
			return null;
		}

		ShiroUser shiroUser = OAUtils.getUser();
		
		String key = MemcachedObjectType.FINDUSERRESPONSIBILITYS.getPrefix() + shiroUser.getUserId() + "_" +url;

		String userResponsibilitys =null;
		try {
			userResponsibilitys=memcachedClient.get(key); 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<ResponsibilityDto> v=null;
		if (StringUtils.isEmpty(userResponsibilitys)) { 
			
			//从数据库取数据
			v= dao.findUserResponsibilitys(shiroUser.getUserId(), url);

			try {
				userResponsibilitys = mapper.writeValueAsString(v); 
				memcachedClient.set(key, MemcachedObjectType.FINDUSERRESPONSIBILITYS.getExpiredTime(), userResponsibilitys);
				putUserKey(key);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据 
			try {
				v = mapper.readValue(userResponsibilitys, new TypeReference<List<ResponsibilityDto>>() {});
			} catch (Exception e) {
				logger.error(e);
			}
		}
 
		return (List<ResponsibilityDto>) v;
	}

	/**
	 * 
	 * 根据访问的功能返回匹配的第一个prifile值
	 * 
	 */
	public String findPrifileValFirst(String sessionId, final String profileName, final String url) {

		if (!this.isLogonUser(sessionId)) {
			return null;
		}

		ShiroUser shiroUser = OAUtils.getUser();
		
		String key = MemcachedObjectType.FINDPRIFILEVALFIRST.getPrefix() + shiroUser.getUserId() + "_" + profileName + "_" + url;

		String prifileValFirst =null;
		try {
			prifileValFirst=memcachedClient.get(key); 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String v=null;
		if (StringUtils.isEmpty(prifileValFirst)) { 
			
			//从数据库取数据
			v= dao.findPrifileValFirst(shiroUser.getUserId(), profileName, url);

			try {
				prifileValFirst = mapper.writeValueAsString(v); 
				memcachedClient.set(key, MemcachedObjectType.FINDPRIFILEVALFIRST.getExpiredTime(), prifileValFirst);
				putUserKey(key);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据 
			try {
				v = mapper.readValue(prifileValFirst, java.lang.String.class);
			} catch (Exception e) {
				logger.error(e);
			}
		} 

		return (String) v;

	}

	/**
	 * 根据单据提交的人员返回prifile值列表
	 * 
	 * @param personId
	 *            单据提交人员Id
	 * @param profileName
	 *            profile名称
	 * @param url
	 *            当前访问的URL
	 * @return
	 */
	public List<String> findPersonProfileValList(String personId, final String profileName, final String url) {

		String key = MemcachedObjectType.FINDPERSONPROFILEVALLIST.getPrefix() + personId + "_" + profileName + "_" + url;

		String personProfileValList =null;
		try {
			personProfileValList=memcachedClient.get(key); 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<String> v=null;
		if (StringUtils.isEmpty(personProfileValList)) { 
			
			//从数据库取数据
			v= dao.findProfileValList(personId, profileName, url);

			try {
				personProfileValList = mapper.writeValueAsString(v); 
				memcachedClient.set(key, MemcachedObjectType.FINDPERSONPROFILEVALLIST.getExpiredTime(), personProfileValList);
				putUserKey(key);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据 
			try {
				v = mapper.readValue(personProfileValList,  new TypeReference<List<String>>() {});
			} catch (Exception e) {
				logger.error(e);
			}
		} 
	  
		return (List<String>) v;
	}

	/**
	 * 通过功能用户得到默认职责
	 * 
	 */
	public String findResponsibiltyKey(String sessionId, String url) {

		List<ResponsibilityDto> responsibilitys = findUserResponsibilitys(sessionId, url);
		if (responsibilitys != null && responsibilitys.size()>0)
			return responsibilitys.get(0).getResponsibilityId();
		else
			return null;
	}

	/**
	 * 根据职责返回对应prifile值
	 * 
	 * @param sessionId
	 * @param profileName
	 * @param url
	 * @return
	 */
	public String findPrifileValByResponsibility(final String responsibilityId, final String profileName) {
	 	
		String key = MemcachedObjectType.FINDPRIFILEVALBYRESPONSIBILITY.getPrefix() + responsibilityId + "_" +profileName;

		String findPrifileValByResponsibility =null;
		try {
			findPrifileValByResponsibility=memcachedClient.get(key); 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String v=null;
		if (StringUtils.isEmpty(findPrifileValByResponsibility)) { 
			
			//从数据库取数据
			v= dao.findPrifileValByResponsibility(responsibilityId, profileName);

			try {
				findPrifileValByResponsibility = mapper.writeValueAsString(v); 
				memcachedClient.set(key, MemcachedObjectType.FINDPRIFILEVALBYRESPONSIBILITY.getExpiredTime(), findPrifileValByResponsibility);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据
			try {
				v = mapper.readValue(findPrifileValByResponsibility,   java.lang.String.class);
			} catch (Exception e) {
				logger.error(e);
			}
		} 
	 
		return (String) v;

	}

	/**
	 * 通过用户ID查找功能职责
	 * 
	 */
	public List<ResponsibilityDto> findResponsibilitysByUserId(final String userId, final String url) {
		
		String key = MemcachedObjectType.FINDRESPONSIBILITYSBYUSERID.getPrefix() + userId + "_" +url;

		String findPrifileValByResponsibility =null;
		try {
			findPrifileValByResponsibility=memcachedClient.get(key); 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<ResponsibilityDto> v=null;
		if (StringUtils.isEmpty(findPrifileValByResponsibility)) { 
			
			//从数据库取数据
			v= dao.findUserResponsibilitys(userId, url);

			try {
				findPrifileValByResponsibility = mapper.writeValueAsString(v); 
				memcachedClient.set(key, MemcachedObjectType.FINDRESPONSIBILITYSBYUSERID.getExpiredTime(), findPrifileValByResponsibility);
				putUserKey(key);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			//从分布式缓存取数据 
			try {
				v = mapper.readValue(findPrifileValByResponsibility,  new TypeReference<List<ResponsibilityDto>>() {});
			} catch (Exception e) {
				logger.error(e);
			}
		} 
	 

		return (List<ResponsibilityDto>) v;
	}

	/**
	 * 
	 * 初始化profile数据
	 * 
	 * @return
	 */
	public Map<String, List<String>> buildResponsibilityAttrsMap() {

		// ExecutionStringSqlDao dao = new ExecutionStringSqlDao();
		//
		// for( String lang : AuthorityUtils.findSysLanguage()){
		//
		// Map<String, List<ProDict>> dictMap = new HashMap<String,
		// List<ProDict>>();
		//
		// map.put(lang, value)
		//
		// dao.executionSql(sql)
		//
		// }

		return null;
	}

	/**
	 * 
	 * 得到profile值集
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<Map<String, String>> getProDictByName(String name) throws Exception {

		ProfileService profileService = SpringContextHolder.getBean("profileService");

		ExecutionStringSqlDao dao = new ExecutionStringSqlDao();

		ProfileDto dto = profileService.getProfileByCode(name);

		if (dto == null) {
			return null;
		}

		try {
			return dao.getProMap(dto.getSourceSql());
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 获取UrlResponsibilityMap
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getUrlResponsibilityMap(String userId, HttpServletRequest request) {
		Map<String, String> urlResponsibilityMap = null;
		String key = MemcachedObjectType.USER.getPrefix() + userId + "UrlResponsibilityMap";

		if (request.getSession().getAttribute("urlResponsibilityMap") != null) {
			urlResponsibilityMap = (Map<String, String>) request.getSession().getAttribute("urlResponsibilityMap");
		} else {

			String urlResponsibilityMapJson = memcachedClient.get(key);
			if (!StringUtils.isEmpty(urlResponsibilityMapJson)) {
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					urlResponsibilityMap = objectMapper.readValue(urlResponsibilityMapJson, java.util.Map.class);
				} catch (Exception e) {
					logger.error(e);
				}

			}
		}

		return urlResponsibilityMap;
	}

	/**
	 * 切换职责,主动设置UrlResponsibilityMap
	 */
	@Override
	public void setUrlResponsibilityMap(String userId, Map<String, String> urlResponsibilityMap, HttpServletRequest request) {

		String key = MemcachedObjectType.USER.getPrefix() + userId + "UrlResponsibilityMap";

		String urlResponsibilityMapJson = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			urlResponsibilityMapJson = objectMapper.writeValueAsString(urlResponsibilityMap);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}

		memcachedClient.set(key, MemcachedObjectType.USER.getExpiredTime(), urlResponsibilityMapJson);


		request.getSession().setAttribute("urlResponsibilityMap", urlResponsibilityMap);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void accreditFlushUserResponsibility(String userId) {
		String key = MemcachedObjectType.FLUSHUSERRESPONSIBILITY.getPrefix() + userId;
		
		List<String> flushKeysList=new ArrayList<String>();
		String flushKeysJson = memcachedClient.get(key);
		if (!StringUtils.isEmpty(flushKeysJson)) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				flushKeysList = objectMapper.readValue(flushKeysJson, java.util.List.class);
			} catch (Exception e) {
				logger.error(e);
			}

		}
		
		if(flushKeysList!=null && flushKeysList.size()>0)
		{
			SpyMemcachedClient client = SpringContextHolder.getBean("spyMemcachedClient");
			for (int i = 0; i < flushKeysList.size(); i++) { 
				client.getMemcachedClient().delete(flushKeysList.get(i)); 
			}
			client.getMemcachedClient().delete(key); 
		}
  
	}
	
	@SuppressWarnings("unchecked")
	public void putUserKey(String qkey) {
		try {
	    ObjectMapper objectMapper = new ObjectMapper();
	    ShiroUser shiroUser = OAUtils.getUser();
		String key = MemcachedObjectType.FLUSHUSERRESPONSIBILITY.getPrefix() + shiroUser.getUserId();
		String flushKeysJson = memcachedClient.get(key);
		List<String> flushKeysList=new ArrayList<String>();
		if (!StringUtils.isEmpty(flushKeysJson)) { 
				flushKeysList = objectMapper.readValue(flushKeysJson, java.util.List.class);  
				if(!flushKeysList.contains(qkey))
				{
					flushKeysList.add(qkey); 
					String json = objectMapper.writeValueAsString(flushKeysList);
					memcachedClient.set(key, MemcachedObjectType.FLUSHUSERRESPONSIBILITY.getExpiredTime(), json);
				}
		}
		else
		{
			flushKeysList.add(qkey); 
			String json = objectMapper.writeValueAsString(flushKeysList);
			memcachedClient.set(key, MemcachedObjectType.FLUSHUSERRESPONSIBILITY.getExpiredTime(), json);
		}
		
		
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

}
