package com.aus.authority.service;

import java.util.List;
import java.util.Map;

import com.aus.authority.model.NavigationDto;
import com.aus.authority.model.TreeDTO;


/**
 * 导航服务
 * @author duzh
 *
 */
public interface NavigationService {
	
	/**
	 * 根据ID删除导航
	 * 
	 * @param id
	 * 		导航id
	 */
	void deleteNavigationById(String id);
	
	
	/**
	 * 
	 * 保存导航
	 * 
	 */
	Map<String, String> saveNavigation(NavigationDto navigation);
	
	
	/**
	 * 
	 * 修改导航
	 * @param lang 
	 * 
	 */
	Map<String, String> updateNavigation(NavigationDto navigation, String lang);
	
	
	/**
	 * 
	 * 得到导航树
	 * 
	 */
	List<NavigationDto> getNavigationTree(NavigationDto navigation);
	
	/**
	 * 
	 * 得到已选择的导航树
	 * 
	 */
	public List<NavigationDto> getNavigationTreeByCheckRoleId(String roleId);

	
	
	/**
	 * 得到整颗导航树,并初始化选择
	 * 
	 * @param navigation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Map<String, List> getNavigationAllTree(String[] checkedNodes);
	
	
	/**
	 * 创建导航树
	 * 
	 * 
	 * @param tree
	 */
	@SuppressWarnings("unchecked")
	void createNavigationTree(TreeDTO tree, int level ,String parentName,List v_mapList,String[] checkedNodes);

	/**
	 * 
	 * 根据角色得到导航列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<NavigationDto> getNavigationTreeByRoleId(String roleId);


	/**
	 * 
	 * 找出所有的功能
	 * 
	 * @return
	 */
	public List<NavigationDto> getAllFunctions();


	/**
	 * 得到菜单资源树
	 * 
	 * @return
	 */
	List<TreeDTO> getMeunResourceTree(String[] checkedNodes);
	
	
	/**
	 * 资源管理中查询功能树
	 */
	List<NavigationDto> getAllFunctionsByResoure();
}
