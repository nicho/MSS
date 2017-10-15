package com.aus.authority.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.authority.dao.IResponsibilityDao;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.ResponsibilityInvOrgDto;
import com.aus.authority.model.ResponsibilityProfileDto;
import com.aus.authority.model.ResponsibilityRoleDto;
import com.aus.authority.model.ResponsibilityTlDto;
import com.aus.authority.service.ResponsibilityService;
import com.aus.authority.util.AuthorityUtils;

@Service("responsibilityService")
public class ResponsibilityServiceImpl implements ResponsibilityService{
	private static Logger logger = Logger.getLogger(ResponsibilityServiceImpl.class);
	@Autowired
	private IResponsibilityDao responsibilityDao;

	public List<ResponsibilityDto> getAllResponsibility(ResponsibilityDto responsibilityDto) {
		return responsibilityDao.getAllResponsibility(responsibilityDto);
	}
	
	public List<ResponsibilityDto> getResponsibilityByResponsibility(ResponsibilityDto responsibilityDto) {
		return responsibilityDao.getResponsibilityByResponsibility(responsibilityDto);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveResponsibility(
			ResponsibilityDto responsibilityDto, String userId) {
		
		Map returnMap = new HashMap<String,String>();
		
		
		responsibilityDao.saveResponsibility(responsibilityDto);
		
		List<String> v_language_lit = AuthorityUtils.findSysLanguage();
		
		for( String v_lang : v_language_lit ) {				
			ResponsibilityTlDto tl = new ResponsibilityTlDto();
			BeanUtils.copyProperties(responsibilityDto, tl);
			tl.setLanguage(v_lang);
			responsibilityDao.saveResponsibilityTl(tl);				
		}

		if(responsibilityDto.getRoleIds()!= null) {
			for( String roleId : responsibilityDto.getRoleIds()) {
				if(StringUtils.isNotBlank(roleId)) {
					ResponsibilityRoleDto roleDto = new ResponsibilityRoleDto();
					roleDto.setResponsibilityId(responsibilityDto.getResponsibilityId());
					roleDto.setRoleId(roleId);
					responsibilityDao.saveResponsibilityRole(roleDto);
				}
			}
		}
		
		if(responsibilityDto.getProfileIds()!= null) {
			for (int i = 0; i < responsibilityDto.getProfileIds().length; i++) {
				if(StringUtils.isNotBlank(responsibilityDto.getProfileIds()[i])) {
					ResponsibilityProfileDto dto = new  ResponsibilityProfileDto();
					dto.setProfileId(responsibilityDto.getProfileIds()[i]);
					dto.setResponsibilityId(responsibilityDto.getResponsibilityId());
					dto.setProfileValue(responsibilityDto.getProfileVals()[i]);
					responsibilityDao.saveResponsibilityProfile(dto);
				}
			}
		}
		/**职责对应的库存组织保存**/
		if(responsibilityDto.getResponsibilityInvOrgs() != null) {
			for (int i = 0; i < responsibilityDto.getResponsibilityInvOrgs().length; i++) {
				if(StringUtils.isNotBlank(responsibilityDto.getResponsibilityInvOrgs()[i])) {
					ResponsibilityInvOrgDto invOrgDto = new ResponsibilityInvOrgDto();
					
					invOrgDto.setResponsibilityId(responsibilityDto.getResponsibilityId());
					invOrgDto.setInvOrganizationId(responsibilityDto.getResponsibilityInvOrgs()[i]);
					invOrgDto.setCreatedBy(userId);
					invOrgDto.setLastUpdatedBy(userId);
					invOrgDto.setLastUpdateLogin(userId);
					invOrgDto.setIsDelete("0");
					
					responsibilityDao.saveResponsibilityInvOrg(invOrgDto);
				}
			}
		}
		returnMap.put("responsibilityId", responsibilityDto.getResponsibilityId());
		returnMap.put("success","true");
			
		
		return returnMap;
	}

	public ResponsibilityDto getResponsibilityById(String responsibilityId) {
		return responsibilityDao.getResponsibilityById(responsibilityId);
	}


	public List<ResponsibilityProfileDto> getResponsibilityProfileById(
			String responsibilityId) {
		return responsibilityDao.getResponsibilityProfileById(responsibilityId);
	}

	public List<ResponsibilityRoleDto> getResponsibilityRoleById(
			String responsibilityId) {
		return responsibilityDao.getResponsibilityRoleById(responsibilityId);
	}

	public List<ResponsibilityDto> findResponsibilityByUserId(String userId) {	
		return responsibilityDao.findResponsibilityByUserId(userId);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> updateResponsibility(
			ResponsibilityDto responsibilityDto,String lang, String userId) {

		Map returnMap = new HashMap<String,String>();
		
		responsibilityDao.updateResponsibility(responsibilityDto);
						
		ResponsibilityTlDto tl = new ResponsibilityTlDto();
		
		BeanUtils.copyProperties(responsibilityDto, tl);
		
		tl.setLanguage(lang);
		
		responsibilityDao.updateResponsibilityTl(tl);	
		
		responsibilityDao.deleteResponsibilityRoleById(responsibilityDto.getResponsibilityId());
		
		responsibilityDao.deleteResponsibilityProfileById(responsibilityDto.getResponsibilityId());
		
		responsibilityDao.deleteResponsibilityInvOrgById(responsibilityDto.getResponsibilityId());
		
		if(responsibilityDto.getRoleIds()!= null) {
			for( String roleId : responsibilityDto.getRoleIds()) {
				if(StringUtils.isNotBlank(roleId)) {
					ResponsibilityRoleDto roleDto = new ResponsibilityRoleDto();
					roleDto.setResponsibilityId(responsibilityDto.getResponsibilityId());
					roleDto.setRoleId(roleId);
					responsibilityDao.saveResponsibilityRole(roleDto);
				}
			}
		}
		
		if(responsibilityDto.getProfileIds()!= null) {
			for (int i = 0; i < responsibilityDto.getProfileIds().length; i++) {
				if(StringUtils.isNotBlank(responsibilityDto.getProfileIds()[i])) {
					ResponsibilityProfileDto dto = new  ResponsibilityProfileDto();
					dto.setProfileId(responsibilityDto.getProfileIds()[i]);
					dto.setResponsibilityId(responsibilityDto.getResponsibilityId());
					dto.setProfileValue(responsibilityDto.getProfileVals()[i]);
					responsibilityDao.saveResponsibilityProfile(dto);
				}
			}
		}
		
		/**职责对应的库存组织保存**/
		if(responsibilityDto.getResponsibilityInvOrgs() != null) {
			for (int i = 0; i < responsibilityDto.getResponsibilityInvOrgs().length; i++) {
				if(StringUtils.isNotBlank(responsibilityDto.getResponsibilityInvOrgs()[i])) {
					ResponsibilityInvOrgDto invOrgDto = new ResponsibilityInvOrgDto();
					
					invOrgDto.setResponsibilityId(responsibilityDto.getResponsibilityId());
					invOrgDto.setInvOrganizationId(responsibilityDto.getResponsibilityInvOrgs()[i]);
					invOrgDto.setCreatedBy(userId);
					invOrgDto.setLastUpdatedBy(userId);
					invOrgDto.setLastUpdateLogin(userId);
					invOrgDto.setIsDelete("0");
					
					responsibilityDao.saveResponsibilityInvOrg(invOrgDto);
				}
			}
		}
		
		
		returnMap.put("success","true");
		
		return returnMap;
	}
	
	/**
	 * 查询库存组织列表
	 * @return
	 */
	public List<ResponsibilityInvOrgDto> getOrganizationInvList() {
		return responsibilityDao.getOrganizationInvList();
	}
	
	/**
	 * 查询当前职责下的库存组织列表
	 */
	public List<ResponsibilityInvOrgDto> getOrganizationInvByResponsibilityId(String responsibilityId) {
		return responsibilityDao.getOrganizationInvByResponsibilityId(responsibilityId);
	}

	@Override
	public List<ResponsibilityDto> getSelfAndChildOrgId(String orgId) {
		// TODO Auto-generated method stub
		return responsibilityDao.getSelfAndChildOrgId(orgId);
	}

	@Override
	public List<ResponsibilityDto> findResponsibilityByUserAndOrg(
			String userId, String orgId) {
		// TODO Auto-generated method stub
		return responsibilityDao.findResponsibilityByUserAndOrg(userId, orgId);
	}

	@Override
	public List<ResponsibilityDto> findResponsibilityByUserIdAndOther(ResponsibilityDto responsibilityDto) {
		// TODO Auto-generated method stub
		return responsibilityDao.findResponsibilityByUserIdAndOther(responsibilityDto);
	}

}
