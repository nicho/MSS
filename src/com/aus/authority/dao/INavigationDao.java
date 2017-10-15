package com.aus.authority.dao;

import java.util.List;

import com.aus.authority.model.NavigationDto;
import com.aus.authority.model.NavigationTlDto;

/**
 * 导航
 * @author duzh
 *
 */
public interface INavigationDao {

	void deleteNavigationById(String id);
	
	List<NavigationDto> getNavigationByParentId(String parentId);

	int insertNavigation(NavigationDto navigation);

	int updateNavigation(NavigationDto navigation);

	List<NavigationDto> getNavigationTreeByRoleId(String roleId);

	List<NavigationDto> getAllFunctions();
	
	List<NavigationDto> getAllFunctionsByResoure();

	void deleteNavigationTlById(String id);

	void insertNavigationTl(NavigationTlDto t1);

	void updateNavigationTl(NavigationTlDto t1);
}
