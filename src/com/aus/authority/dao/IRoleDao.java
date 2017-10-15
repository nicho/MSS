package com.aus.authority.dao;

import java.util.List;

import com.aus.authority.model.RoleDto;
import com.aus.authority.model.RoleFuncDto;
import com.aus.authority.model.RoleResourceDto;
import com.aus.authority.model.RoleTlDto;

/**
 * 角色
 * 
 * @author duzh
 *
 */
public interface IRoleDao {

	public List<String> findAllSysLanguage();

	public int saveRoleTl(RoleTlDto roleTl);

	public int saveRole(RoleDto roleDto);

	public List<RoleDto> getAllRole(RoleDto roleDto);

	public int saveRoleFunc(RoleFuncDto roleFun);

	public RoleDto getRoleById(String roleId);

	public List<RoleFuncDto> getRoleFuncById(String roleId);

	public void deleteRoleFuncById(String roleId);

	public void updateRole(RoleDto roleDto);

	public void updateRoleTl(RoleTlDto t1);

	public List<RoleResourceDto> getRoleResourceById(String roleId);

	public void saveRoleResource(RoleResourceDto roleResource);

	public void deleteRoleResourceById(String roleId);
	
	public Long checkUsedRole(String roleId);

	public void deleteRoleById(String roleId);
	
	public void deleteRoleTlById(String roleId);
	
	public List<RoleDto> getRoleCountByRole(RoleDto roleDto);

}
