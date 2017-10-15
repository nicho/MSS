package com.aus.common.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.dao.ICustAccountDao;
import com.aus.common.dao.IPersonDao;
import com.aus.common.model.CustAccountDto;
import com.aus.common.model.Job;
import com.aus.common.model.PersonBasicInformation;
import com.aus.common.model.PersonDto;
import com.aus.common.model.PersonKpiDto;
import com.aus.common.service.PersonService;

@Service("PersonService")
public class PersonServiceImpl implements PersonService {
	private static Logger logger = Logger.getLogger(PersonServiceImpl.class);
	@Autowired
	private IPersonDao personDao;

	@Autowired
	private ICustAccountDao custAccountDao;

	public PersonDto getPersonPositionId(PersonDto personDto) {
		List<PersonDto> list = personDao.getPersonPositionId(personDto);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public String getUserChannelType(String userTypeProfile, String orgIdProfile, String channelTypeProfile, String userId) {
		if ("10".equals(userTypeProfile)) {
			return channelTypeProfile;
		} else if ("20".equals(userTypeProfile)) {
			PersonDto dto = new PersonDto();
			dto.setUserId(userId);
			dto.setChannelType(channelTypeProfile);
			List<PersonDto> list = personDao.getPersonOrganization(dto);
			if (CollectionUtils.isEmpty(list)) {
				return channelTypeProfile;
			} else {
				PersonDto personDto = list.get(0);
				return personDto.getChannelType();
			}

		} else if ("30".equals(userTypeProfile)) {
			CustAccountDto dto = new CustAccountDto();
			dto.setUserId(userId);
			dto.setOrgId(orgIdProfile);
			List<CustAccountDto> list = custAccountDao.getCustAccount(dto);
			if (CollectionUtils.isEmpty(list)) {
				return channelTypeProfile;
			} else {
				CustAccountDto custAccountDto = list.get(0);
				return custAccountDto.getChannelType();
			}
		}

		return channelTypeProfile;
	}

	public List<PersonDto> getPerson(PersonDto personDto) {
		return this.personDao.getPerson(personDto);
	}

	public IPersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

	public ICustAccountDao getCustAccountDao() {
		return custAccountDao;
	}

	public void setCustAccountDao(ICustAccountDao custAccountDao) {
		this.custAccountDao = custAccountDao;
	}

	@Override
	public List<PersonDto> getPersonOrganization(PersonDto personDto) {
		return personDao.getPersonOrganization(personDto);
	}

	@Override
	public String getPersonJobId(String personId, String hrOrgId) {
		return personDao.getPersonJobId(personId, hrOrgId);
	}

	@Override
	public PersonBasicInformation getPersonBasicInformation(String personId) {
		return personDao.getPersonBasicInformation(personId);
	}


	@Override
	public List<PersonDto> getPersonByPersonId(String personId,String orgId,String departmentCode,String channelType) {
		
		if( StringUtils.isNotBlank(departmentCode) ) {			
			try {
				//System.out.println(URLDecoder.decode(departmentCode));
				departmentCode = URLDecoder.decode(departmentCode,"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}			
		}		
		return personDao.getPersonByPersonId(personId,orgId,departmentCode,channelType);
	}


	@Override
	public List<PersonDto> getPersonOrganizationWeiXin(PersonDto personDto) {
		return personDao.getPersonOrganizationWeiXin(personDto);
	}

	@Override
	public List<PersonKpiDto> getPersonOrganizationByKpi(PersonKpiDto personDto) {
		return personDao.getPersonOrganizationByKpi(personDto);
	}

	@Override
	public List<Job> getAllJob() {
		return personDao.getAllJob();
	}

	@Override
	public List<PersonDto> getPersonByKpiVerification(PersonDto personDto) {
		return personDao.getPersonByKpiVerification(personDto);
	}

	@Override
	public List<PersonDto> getPersonOrganizationNotOrderBy(PersonDto personDto) { 
		return personDao.getPersonOrganizationNotOrderBy(personDto);
	}

	@Override
	public List<PersonDto> getPersonOrganizationIsShoppingGuideNotOrderBy(PersonDto personDto) {
		return personDao.getPersonOrganizationIsShoppingGuideNotOrderBy(personDto);
	}

	/**
	 * 获取前一天失效的用户
	 * @param personDto
	 * @return
	 */
	@Override
	public List<PersonDto> getInvalidUserBeforeDay(PersonDto personDto) {
		return personDao.getInvalidUserBeforeDay(personDto);
	}

	@Override
	public List<Map<String, String>> selectHrPayTplPosition(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return personDao.selectHrPayTplPosition(param);
	}

	@Override
	public List<PersonDto> getCommitPersonOrganizationNotOrderBy(
			PersonDto personDto) {
		// TODO Auto-generated method stub
		return personDao.getCommitPersonOrganizationNotOrderBy(personDto);
	}

	@Override
	public List<PersonDto> getPersonInBudget(PersonDto personDto) {
		return this.personDao.getPersonInBudget(personDto);
	}

	@Override
	public List<PersonDto> getPersonFunctionList(PersonDto personDto) {
		// TODO Auto-generated method stub
		return  this.personDao.getPersonFunctionList(personDto);
	}
}
