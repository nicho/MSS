package com.aus.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.dao.OrgValueDao;
import com.aus.common.model.OrgDto;
import com.aus.common.service.OrgService;

@Service("OrgService")
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgValueDao orgValueDao;

	@Override
	public List<OrgDto> getOrgList() {
		return orgValueDao.getOrgList();
	}

	@Override
	public OrgDto getOrgById(Long id) {
		return orgValueDao.getOrgById(id);
	}
	@Override
	public List<OrgDto> getOrgByOrglevel(String orgId){
		return orgValueDao.getOrgByOrglevel(orgId);
		
	}
	@Override
	public List<OrgDto> getAccessOrgListByOrgId(String orgId){
		return orgValueDao.getAccessOrgListByOrgId(orgId);	
	}

	@Override
	public String checkIsProvinceByPositionId(String positionId) {
		// TODO Auto-generated method stub
		return orgValueDao.checkIsProvinceByPositionId(positionId);
	}
 
}
