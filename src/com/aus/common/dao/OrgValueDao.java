package com.aus.common.dao;

import java.util.List;

import com.aus.common.model.OrgDto;

public interface OrgValueDao {

	List<OrgDto> getOrgList();

	OrgDto getOrgById(Long id);
	
	List<OrgDto> getOrgByOrglevel(String orgId);
	
	List<OrgDto> getAccessOrgListByOrgId(String orgId);
	
	String checkIsProvinceByPositionId(String positionId);
}
