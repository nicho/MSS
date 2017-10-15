package com.aus.authority.service;

import java.util.List;
import java.util.Map;

import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.ResponsibilityInvOrgDto;
import com.aus.authority.model.ResponsibilityProfileDto;
import com.aus.authority.model.ResponsibilityRoleDto;

/**
 * 
 * 职责服务
 * 
 * @author duzh
 *
 */
public interface ResponsibilityService {

	List<ResponsibilityDto> getAllResponsibility(ResponsibilityDto responsibilityDto);

	Map<String, String> saveResponsibility(ResponsibilityDto responsibilityDto, String userId);

	ResponsibilityDto getResponsibilityById(String responsibilityId);

	List<ResponsibilityRoleDto> getResponsibilityRoleById(
			String responsibilityId);

	List<ResponsibilityProfileDto> getResponsibilityProfileById(
			String responsibilityId);

	List<ResponsibilityDto> findResponsibilityByUserId(String userId);
	
	/**
	 * 查询职责控件
	 * */
	List<ResponsibilityDto>findResponsibilityByUserIdAndOther(ResponsibilityDto responsibilityDto);
	
	List<ResponsibilityDto> findResponsibilityByUserAndOrg(String userId,String orgId);

	Map<String, String> updateResponsibility(ResponsibilityDto responsibilityDto, String lang, String userId);

	public List<ResponsibilityDto> getResponsibilityByResponsibility(ResponsibilityDto responsibilityDto);
	
	/**
	 * 查询库存组织列表
	 * @return
	 */
	public List<ResponsibilityInvOrgDto> getOrganizationInvList();
	
	/**查询当前职责下的库存组织列表**/
	List<ResponsibilityInvOrgDto> getOrganizationInvByResponsibilityId(String responsibilityId);
	
	public List<ResponsibilityDto> getSelfAndChildOrgId(String orgId);
}
