package com.aus.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aus.common.model.Job;
import com.aus.common.model.PersonBasicInformation;
import com.aus.common.model.PersonDto;
import com.aus.common.model.PersonKpiDto;

public interface IPersonDao {

	List<PersonDto> getPerson(PersonDto personDto);
	
	List<PersonDto>getPersonFunctionList(PersonDto personDto);
	
	List<PersonDto>getPersonInBudget(PersonDto personDto);

	List<PersonDto> getPersonPositionId(PersonDto personDto);

	List<PersonDto> getPersonOrganization(PersonDto personDto);

	List<PersonDto> getPersonOrganizationNotOrderBy(PersonDto personDto);
	
	List<PersonDto>  getCommitPersonOrganizationNotOrderBy(PersonDto personDto);

	List<PersonDto> getPersonOrganizationIsShoppingGuideNotOrderBy(PersonDto personDto);

	List<PersonDto> getPersonOrganizationWeiXin(PersonDto personDto);

	List<PersonKpiDto> getPersonOrganizationByKpi(PersonKpiDto personDto);

	List<PersonDto> getPersonOrganizationNoCall(PersonDto personDto);

	String getPersonJobId(@Param("personId") String personId, @Param("hrOrgId") String hrOrgId);// base_hr_position_assign_v

	PersonBasicInformation getPersonBasicInformation(@Param("personId") String personId);
	
	List<PersonDto> getPersonIdByPersonName(@Param("personName") String personName, @Param("orgId")String orgId);

	List<PersonDto> getPersonByPersonId(@Param("personId") String personId, @Param("orgId") String orgId,
			@Param("departmentCode") String departmentCode,@Param("channelType") String channelType);

	List<Job> getAllJob();

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
