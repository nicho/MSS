package com.aus.authority.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.authority.dao.INavigationDao;
import com.aus.authority.dao.IResourceDao;
import com.aus.authority.dao.IRoleDao;
import com.aus.authority.model.NavigationDto;
import com.aus.authority.model.NavigationTlDto;
import com.aus.authority.model.ResourceDto;
import com.aus.authority.model.RoleResourceDto;
import com.aus.authority.model.TreeDTO;
import com.aus.authority.service.NavigationService;
import com.aus.authority.util.AuthorityUtils;


/**
 * 导航
 * 
 * @author duzh
 *
 */
@Service("NavigationService")
public class NavigationServiceImpl implements NavigationService{
	private static Logger logger = Logger.getLogger(NavigationServiceImpl.class);
	@Autowired
	private INavigationDao navigationDao;
	
	@Autowired
	private IResourceDao resourceDao;
	
	@Autowired
	private IRoleDao roleDao;
	
	public void deleteNavigationById(String id) {
		
		navigationDao.deleteNavigationById(id);
		
		navigationDao.deleteNavigationTlById(id);
		
	}
	
	private void copyProperties(NavigationDto dto,TreeDTO node) { 
		
		node.setId(dto.getMenuNodeId());
		
		node.setParentId(dto.getParentNodeId());
		
		node.setText(dto.getMenuNodeName());
		
		node.setSort(dto.getOrderNum());
		
		node.setUrl(dto.getUrl());
		
		node.setOriginalSource(dto.getOriginalSource());
		
		node.setNodeType(dto.getMenuNodeType());
		
		node.setNodeCode(dto.getMenuNodeCode());
		
		node.setNodeDesc(dto.getMenuNodeDesc());
		
		node.setIcon(dto.getIcon());
		
	}
	
	private void copyProperties(ResourceDto dto,TreeDTO node) { 
		
		node.setId("R_"+dto.getResourceId());
		
		node.setParentId(dto.getMenuNodeId());
		
		node.setText(dto.getResourceName());
		
		node.setSort(dto.getOrderNum());
		
		node.setNodeType("30");
		
		node.setNodeCode(dto.getResourceCode());
		
	}
	

	@SuppressWarnings("unchecked")
	public Map<String,List> getNavigationAllTree(String[] checkedNodes) {
		
		Map<String,List> retMap = new HashMap<String,List>();
		
		List<NavigationDto> v_navigationList = navigationDao.getNavigationByParentId("0");
		
		List<TreeDTO> listTree = new ArrayList<TreeDTO>();
		
		List<Map> v_mapList = new ArrayList<Map>();
		
		for( NavigationDto dto : v_navigationList ) {
			
			TreeDTO node =  new TreeDTO();
					
			if( checkedNodes != null && ArrayUtils.contains(checkedNodes, dto.getMenuNodeId())) {				
				node.setIschecked("true");				
			}
			
			copyProperties(dto,node);
			
			node.setLevel("1");
			
			
			Map param = new HashMap();
			
			param.put("text", dto.getMenuNodeName());
			
			param.put("id", dto.getMenuNodeId());
			
			v_mapList.add(param);
			
			listTree.add(node);
			
			createNavigationTree(node,1,node.getText(),v_mapList,checkedNodes);
			
		}
		
		retMap.put("v_navigationList", listTree);
		
		retMap.put("v_mapList", v_mapList);

		return retMap;
	}
	
	@SuppressWarnings("unchecked")
	public void createNavigationTree(TreeDTO tree,int level,String parentName,List v_mapList,String[] checkedNodes) {
		
		List<NavigationDto> v_navigationList = navigationDao.getNavigationByParentId(tree.getId());
		
		if ( v_navigationList == null || v_navigationList.size() == 0) {			
			return;
		}
		
		List<TreeDTO> listTree = new ArrayList<TreeDTO>();
		
		for( NavigationDto dto : v_navigationList ) {
			
			TreeDTO node =  new TreeDTO();
			
			if( checkedNodes != null && ArrayUtils.contains(checkedNodes, dto.getMenuNodeId())) {				
				node.setIschecked("true");				
			}
			
			copyProperties(dto,node);
			
			node.setLevel(level+1+"");
			
			listTree.add(node);
			
			if( level == 1) {
								
				Map param = new HashMap();
				
				param.put("text", parentName +"/" +dto.getMenuNodeName());
				
				param.put("id", dto.getMenuNodeId());
				
				v_mapList.add(param);
				
			}
			
			createNavigationTree(node,level+1,null,null,checkedNodes);
			
			tree.setChildren(listTree);
		}		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> updateNavigation(NavigationDto navigation,String lang) {
		Map returnMap = new HashMap<String, String>();

		try {

			navigationDao.updateNavigation(navigation);

			NavigationTlDto t1 = new NavigationTlDto();
				
			BeanUtils.copyProperties(navigation, t1);
			
			t1.setLanguage(lang);

			updateNavigationTl(t1);
			
			returnMap.put("success", "true");

		} catch (Exception e) {

			returnMap.put("success", "false");

			returnMap.put("message", e.getMessage());

			logger.error(e);
		}

		return returnMap;
		
	}
	

	private void updateNavigationTl(NavigationTlDto t1) {
		navigationDao.updateNavigationTl(t1);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveNavigation(NavigationDto navigation) {
		Map returnMap = new HashMap<String, String>();

		try {

			navigationDao.insertNavigation(navigation);

			List<String> v_language_list = AuthorityUtils.findSysLanguage();// 找到开启语言

			for (String lang : v_language_list) {
				NavigationTlDto t1 = new NavigationTlDto();
				BeanUtils.copyProperties(navigation, t1);
				t1.setLanguage(lang);
				insertNavigationTl(t1);
			}

			returnMap.put("success", "true");

		} catch (Exception e) {

			returnMap.put("success", "false");

			returnMap.put("message", e.getMessage());

			logger.error(e);
		}

		return returnMap;
		
	}

	private void insertNavigationTl(NavigationTlDto t1) {
		navigationDao.insertNavigationTl(t1);
	}

	public List<NavigationDto> getNavigationTreeByRoleId(String roleId) {
		
		List<NavigationDto> v_navigation = navigationDao.getNavigationTreeByRoleId(roleId);
		
		if( v_navigation == null || v_navigation.size() == 0) {
			return v_navigation;
		}
		
		List<NavigationDto> v_ret = new ArrayList<NavigationDto>();
		
		for(NavigationDto dto : v_navigation) {
			v_ret.add(dto);
			
			List<ResourceDto> v_resource = resourceDao.getResourceByMeunId(dto.getMenuNodeId());
			
			for(ResourceDto resource : v_resource) {
				NavigationDto node = new NavigationDto();
				node.setMenuNodeId("9999"+resource.getResourceId());
				node.setParentNodeId(dto.getMenuNodeId());
				node.setMenuNodeName(resource.getResourceName());
				v_ret.add(node);
			}			
		}

		return v_ret;
	}

	public List<NavigationDto> getNavigationTree(NavigationDto navigation) {
		return null;
	}

	public List<NavigationDto> getAllFunctions() {
		return navigationDao.getAllFunctions();
	}

	
	
	public List<TreeDTO> getMeunResourceTree(String[] checkedNodes) {
		
		List<NavigationDto> v_navigationList = navigationDao.getNavigationByParentId("0");
		
		List<TreeDTO> listTree = new ArrayList<TreeDTO>();
			
		for( NavigationDto dto : v_navigationList ) {
			
			TreeDTO node =  new TreeDTO();
					
			if( checkedNodes != null && ArrayUtils.contains(checkedNodes, dto.getMenuNodeId())) {				
				node.setIschecked("true");				
			}
			
			copyProperties(dto,node);
			
			listTree.add(node);
			
			if( StringUtils.equals(dto.getMenuNodeType(), "20")) {
				
				List<ResourceDto> v_resource = resourceDao.getResourceByMeunId(dto.getMenuNodeId());
				
				for(ResourceDto resource : v_resource ) {
					
					TreeDTO resourceNode =  new TreeDTO();
					
					copyProperties(resource,resourceNode);
					
					if( checkedNodes != null && ArrayUtils.contains(checkedNodes, resourceNode.getId())) {				
						resourceNode.setIschecked("true");				
					}
					
					node.getChildren().add(resourceNode);
					
				}
				
			}
			
			createMeunResourceTree(node,checkedNodes);
			
		}

		return listTree;
	}
	
	public void createMeunResourceTree(TreeDTO tree,String[] checkedNodes) {
		
		List<NavigationDto> v_navigationList = navigationDao.getNavigationByParentId(tree.getId());
		
		if ( v_navigationList == null || v_navigationList.size() == 0) {			
			return;
		}
		
		List<TreeDTO> listTree = new ArrayList<TreeDTO>();
		
		for( NavigationDto dto : v_navigationList ) {
			
			TreeDTO node =  new TreeDTO();
			
			if( checkedNodes != null && ArrayUtils.contains(checkedNodes, dto.getMenuNodeId())) {				
				node.setIschecked("true");				
			}
			
			copyProperties(dto,node);
			
			listTree.add(node);	
			
			if( StringUtils.equals(dto.getMenuNodeType(), "20")) {
				
				List<ResourceDto> v_resource = resourceDao.getResourceByMeunId(dto.getMenuNodeId());
				
				for(ResourceDto resource : v_resource ) {
					
					TreeDTO resourceNode =  new TreeDTO();
					
					copyProperties(resource,resourceNode);
					
					if( checkedNodes != null && ArrayUtils.contains(checkedNodes, resourceNode.getId())) {				
						resourceNode.setIschecked("true");				
					}
					
					node.getChildren().add(resourceNode);
					
				}
				
			}			
			
			createMeunResourceTree(node,checkedNodes);
			
			tree.setChildren(listTree);
		}		
	}
	
	/**
	 * 资源管理中查询功能树
	 */
	public List<NavigationDto> getAllFunctionsByResoure() {
		return navigationDao.getAllFunctionsByResoure();
	}

	@Override
	public List<NavigationDto> getNavigationTreeByCheckRoleId(String roleId) {
     List<NavigationDto> v_navigation = navigationDao.getNavigationTreeByRoleId(roleId);
		
		if( v_navigation == null || v_navigation.size() == 0) {
			return v_navigation;
		}
		
		List<NavigationDto> v_ret = new ArrayList<NavigationDto>();
		
		for(NavigationDto dto : v_navigation) {
			v_ret.add(dto);
			
			List<ResourceDto> v_resource = resourceDao.getResourceByMeunId(dto.getMenuNodeId());
			
			List<ResourceDto> nv_resource = new ArrayList<ResourceDto>();
			
			List<RoleResourceDto> v_roleresource_list = roleDao.getRoleResourceById(roleId);//得到角色分配的资源
			for(ResourceDto resource:v_resource){
				for(RoleResourceDto role:v_roleresource_list){
				if(resource.getResourceId().equals(role.getResourceId())){
					nv_resource.add(resource);
				}
				}
				
			}
			for(ResourceDto resource : nv_resource) {
				NavigationDto node = new NavigationDto();
				node.setMenuNodeId("9999"+resource.getResourceId());
				node.setParentNodeId(dto.getMenuNodeId());
				node.setMenuNodeName(resource.getResourceName());
				v_ret.add(node);
			}			
		}

		return v_ret;
	}
	
}
