package com.aus.authority.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.authority.dao.IRoleDao;
import com.aus.authority.model.RoleDto;
import com.aus.authority.model.RoleFuncDto;
import com.aus.authority.model.RoleResourceDto;
import com.aus.authority.model.RoleTlDto;
import com.aus.authority.service.RoleService;
import com.aus.authority.util.AuthorityUtils;

/**
 * 角色
 * 
 * @author duzh
 * 
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	private static Logger logger = Logger.getLogger(RoleServiceImpl.class);
	@Autowired
	private IRoleDao roleDao;

	public List<RoleDto> getAllRole(RoleDto roleDto) {
		return roleDao.getAllRole(roleDto);
	}
	
	public List<RoleDto> getRoleCountByRole(RoleDto roleDto) {
		return roleDao.getRoleCountByRole(roleDto);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveRole(RoleDto roleDto) {

		Map returnMap = new HashMap<String, String>();

		try {

			roleDao.saveRole(roleDto);

			List<String> v_language_list = AuthorityUtils.findSysLanguage();// 找到开启语言

			for (String lang : v_language_list) {
				RoleTlDto t1 = new RoleTlDto();
				BeanUtils.copyProperties(roleDto, t1);
				t1.setLanguage(lang);
				saveRoleTl(t1);
			}

			String funcStr = roleDto.getFuns();

			if (StringUtils.isNotBlank(funcStr)) {

				funcStr = StringUtils.substringBeforeLast(funcStr, ",");

				String[] funIds = StringUtils.split(funcStr, ',');

				for (String funId : funIds) {

					if(StringUtils.startsWith(funId, "R_")) {						
						RoleResourceDto roleResource = new RoleResourceDto();
						roleResource.setRoleId(roleDto.getRoleId());
						roleResource.setResourceId(StringUtils.remove(funId, "R_"));
						saveRoleResource(roleResource);						
					} else {
						RoleFuncDto roleFun = new RoleFuncDto();
						roleFun.setRoleId(roleDto.getRoleId());
						roleFun.setMenuNodeId(funId);
						saveRoleFunc(roleFun);
					}
			    
				}
			}

			returnMap.put("success", "true");

		} catch (Exception e) {

			returnMap.put("success", "false");

			returnMap.put("message", e.getMessage());

			logger.error(e);
		}

		return returnMap;
	}

	public void saveRoleResource(RoleResourceDto roleResource) {
		roleDao.saveRoleResource(roleResource);
		
	}

	public List<String> findAllSysLanguage() {
		return roleDao.findAllSysLanguage();
	}

	public int saveRoleTl(RoleTlDto roleTl) {
		return roleDao.saveRoleTl(roleTl);
	}

	public int saveRoleFunc(RoleFuncDto roleFun) {
		return roleDao.saveRoleFunc(roleFun);
	}

	public RoleDto getRoleById(String roleId) {
		return roleDao.getRoleById(roleId);
	}

	public List<RoleFuncDto> getRoleFuncById(String roleId) {
		return roleDao.getRoleFuncById(roleId);
	}

	public void deleteRoleFuncById(String roleId) {
		roleDao.deleteRoleFuncById(roleId);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> updateRole(RoleDto roleDto,String lang) {
		
		Map returnMap = new HashMap<String, String>();
		
		try {
			
			roleDao.updateRole(roleDto);//保存角色		
			
			RoleTlDto t1 = new RoleTlDto();
			
			BeanUtils.copyProperties(roleDto, t1);
			
			t1.setLanguage(lang);
			
			updateRoleTl(t1);
			
			deleteRoleFuncById(roleDto.getRoleId());
			
			deleteRoleResourceById(roleDto.getRoleId());
			
			String funcStr = roleDto.getFuns();

			if (StringUtils.isNotBlank(funcStr)) {

				funcStr = StringUtils.substringBeforeLast(funcStr, ",");

				String[] funIds = StringUtils.split(funcStr, ',');

				for (String funId : funIds) {

					if(StringUtils.startsWith(funId, "R_")) {						
						RoleResourceDto roleResource = new RoleResourceDto();
						roleResource.setRoleId(roleDto.getRoleId());
						roleResource.setResourceId(StringUtils.remove(funId, "R_"));
						saveRoleResource(roleResource);						
					} else {
						RoleFuncDto roleFun = new RoleFuncDto();
						roleFun.setRoleId(roleDto.getRoleId());
						roleFun.setMenuNodeId(funId);
						saveRoleFunc(roleFun);
					}
			    
				}
			}

			returnMap.put("success", "true");
			
		} catch (Exception e) {
			returnMap.put("success", "false");

			returnMap.put("message", e.getMessage());

			logger.error(e);
		}
		
		return returnMap;
	}

	private void deleteRoleResourceById(String roleId) {
		roleDao.deleteRoleResourceById(roleId);
	}

	public void updateRoleTl(RoleTlDto t1) {
		roleDao.updateRoleTl(t1);
	}

	public List<RoleResourceDto> getRoleResourceById(String roleId) {
		return roleDao.getRoleResourceById(roleId);
	}

	public boolean checkUsedRole(String roleId) {
		Long l = roleDao.checkUsedRole(roleId);
		if(l > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void deleteRoleById(String roleId) {
		roleDao.deleteRoleById(roleId);
		roleDao.deleteRoleTlById(roleId);
	}


}
