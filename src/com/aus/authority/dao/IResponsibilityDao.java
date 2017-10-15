package com.aus.authority.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.ResponsibilityInvOrgDto;
import com.aus.authority.model.ResponsibilityProfileDto;
import com.aus.authority.model.ResponsibilityRoleDto;
import com.aus.authority.model.ResponsibilityTlDto;

public interface IResponsibilityDao {

	void saveResponsibility(ResponsibilityDto responsibilityDto);

	void saveResponsibilityRole(ResponsibilityRoleDto roleDto);

	void saveResponsibilityProfile(ResponsibilityProfileDto dto);

	void saveResponsibilityTl(ResponsibilityTlDto tl);

	List<ResponsibilityDto> getAllResponsibility(
			ResponsibilityDto responsibilityDto);

	ResponsibilityDto getResponsibilityById(String responsibilityId);

	List<ResponsibilityProfileDto> getResponsibilityProfileById(
			String responsibilityId);

	List<ResponsibilityRoleDto> getResponsibilityRoleById(
			String responsibilityId);

	List<ResponsibilityDto> findResponsibilityByUserId(String userId);
	/**
	 * 查询职责控件
	 * */
	List<ResponsibilityDto>findResponsibilityByUserIdAndOther(ResponsibilityDto responsibilityDto);
	
	List<ResponsibilityDto> findResponsibilityByUserAndOrg(@Param(value="userId")String userId, @Param(value="orgId")String orgId);

	void updateResponsibility(ResponsibilityDto responsibilityDto);

	void deleteResponsibilityRoleById(String responsibilityId);

	void deleteResponsibilityProfileById(String responsibilityId);

	void updateResponsibilityTl(ResponsibilityTlDto tl);

	
	List<ResponsibilityDto> getResponsibilityByResponsibility(ResponsibilityDto responsibilityDto);
	
	/**查询库存组织列表**/
	List<ResponsibilityInvOrgDto> getOrganizationInvList();
	
	/**查询当前职责下的库存组织列表**/
	List<ResponsibilityInvOrgDto> getOrganizationInvByResponsibilityId(String responsibilityId);
	
	/**插入职责对应的库存组织**/
	void saveResponsibilityInvOrg(ResponsibilityInvOrgDto dto);
	
	/**删除职责对应的库存组织，修改时先删除再修改**/
	void deleteResponsibilityInvOrgById(String responsibilityId);
	
	List<ResponsibilityDto> getSelfAndChildOrgId(String orgId);
}
