package com.aus.common.service;

import java.util.List;

import com.aus.common.model.OrgDto;

public interface OrgService {

	List<OrgDto> getOrgList();

	OrgDto getOrgById(Long id);
	List<OrgDto> getOrgByOrglevel(String orgId);
	List<OrgDto> getAccessOrgListByOrgId(String orgId);
	/**
	 * 
	 * 检查职位对应是省区的还是部门的
	 * **/
	String checkIsProvinceByPositionId(String positionId);
}
