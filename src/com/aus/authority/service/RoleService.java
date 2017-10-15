package com.aus.authority.service;

import java.util.List;
import java.util.Map;

import com.aus.authority.model.RoleDto;
import com.aus.authority.model.RoleFuncDto;
import com.aus.authority.model.RoleTlDto;
import com.aus.authority.model.RoleResourceDto;


/**
 * 角色服务
 * 
 * @author duzh
 *
 */
public interface RoleService {

	List<RoleDto> getAllRole(RoleDto userDto);

	Map<String, String> saveRole(RoleDto roleDto);

	int saveRoleTl(RoleTlDto role);

	List<String> findAllSysLanguage();

	int saveRoleFunc(RoleFuncDto roleFun);

	RoleDto getRoleById(String roleId);

	List<RoleFuncDto> getRoleFuncById(String roleId);

	void deleteRoleFuncById(String roleId);

	Map<String, String> updateRole(RoleDto roleDto, String string);

	void updateRoleTl(RoleTlDto t1);

	List<RoleResourceDto> getRoleResourceById(String roleId);
	
	boolean checkUsedRole(String roleId);

	void deleteRoleById(String roleId);
	
	public List<RoleDto> getRoleCountByRole(RoleDto roleDto);

}
