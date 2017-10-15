package com.aus.authority.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.authority.dao.ExecutionStringSqlDao;
import com.aus.authority.dao.IProfileDao;
import com.aus.authority.model.ProfileDto;
import com.aus.authority.model.ProfileTlDto;
import com.aus.authority.service.ProfileService;

/**
 * 
 * profile服务
 * @author duzh
 *
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService{
	
	@Autowired
	private IProfileDao profileDao;

	public ProfileDto getProfileById(String profileId) {
		return profileDao.getProfileById(profileId);
	}
	
	public ProfileDto getProfileByCode(String profileCode) {
		return profileDao.getProfileByCode(profileCode);
	}

	public List<ProfileDto> getAllProfile(ProfileDto profileDto) {
		return profileDao.getAllProfile(profileDto);
	}

	public int saveProfile(ProfileDto profileDto) {
		return profileDao.saveProfile(profileDto);
	}

	public int saveProfileInfoByCurLang(ProfileTlDto t1) {
		return profileDao.saveProfileInfoByCurLang(t1);		
	}

	public void updateProfile(ProfileDto profileDto) {
		 profileDao.updateProfile(profileDto);
	}

	public void updateProfileInfoByCurLang(ProfileTlDto t1) {
		profileDao.updateProfileInfoByCurLang(t1);
	}

	public boolean checkSourceSql(String sourceSql) {
		
		ExecutionStringSqlDao dao = new ExecutionStringSqlDao();
		
		sourceSql = StringUtils.upperCase(sourceSql).trim();
		
		if(!StringUtils.contains(sourceSql, "FROM")) {
			return false;
		}
		
		int endIndex = sourceSql.indexOf("FROM");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE OR REPLACE FUNCTION CHECK_SOURCE_SQL ");
		
		sb.append(" RETURN VARCHAR IS ");
		sb.append("  V_NAME  VARCHAR(50); ");
		sb.append("  V_CODE  VARCHAR(50); ");
		sb.append("  BEGIN ");
		
		
		sb.append(sourceSql.substring(0,endIndex));
		
		sb.append(" INTO V_NAME,V_CODE ");
		
		sb.append(sourceSql.substring(endIndex,sourceSql.length()));
		
		if(!StringUtils.contains(sourceSql, "WHERE")) {
			sb.append(" WHERE 1 = 1 "); 
		}
		
	    sb.append(" AND ROWNUM =1; ");
	    sb.append(" RETURN V_NAME||V_CODE; ");
	    sb.append(" END;");
		
		try {
			dao.checkProSql(sb.toString());
		}catch(Exception e) {
			return false;
		}		
		return true;
	}



}
