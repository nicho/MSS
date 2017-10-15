package com.aus.common.service;

import java.util.List;
import java.util.Map;

import com.aus.common.model.Job;
import com.aus.common.model.PersonBasicInformation;
import com.aus.common.model.PersonDto;
import com.aus.common.model.PersonKpiDto;

public interface PersonService {

	List<PersonDto> getPerson(PersonDto personDto);
	
	List<PersonDto> getPersonFunctionList(PersonDto personDto);
	
	List<PersonDto>getPersonInBudget(PersonDto personDto);

	String getUserChannelType(String userTypeProfile, String orgIdProfile, String channelTypeProfile, String userId);

	PersonDto getPersonPositionId(PersonDto personDto);

	List<PersonDto> getPersonOrganization(PersonDto personDto);

	List<PersonDto> getPersonOrganizationNotOrderBy(PersonDto personDto);
	
	List<PersonDto>  getCommitPersonOrganizationNotOrderBy(PersonDto personDto);

	List<PersonDto> getPersonOrganizationIsShoppingGuideNotOrderBy(PersonDto personDto);

	List<PersonKpiDto> getPersonOrganizationByKpi(PersonKpiDto personDto);

	List<PersonDto> getPersonOrganizationWeiXin(PersonDto personDto);

	String getPersonJobId(String personId, String hrOrgId);// base_hr_position_assign_v

	PersonBasicInformation getPersonBasicInformation(String personId);

	List<PersonDto> getPersonByPersonId(String personId, String orgId, String departmentCode,String channelType);

	public List<Job> getAllJob();

	List<PersonDto> getPersonByKpiVerification(PersonDto personDto);
	
	/**
	 * 获取前一天失效的用户
	 * @param personDto
	 * @return
	 */
	List<PersonDto> getInvalidUserBeforeDay(PersonDto personDto);
	
	/**
	 * 获取本级及下级所有职位
	 * */
	List<Map<String,String>> selectHrPayTplPosition(Map<String,Object> param);
}
