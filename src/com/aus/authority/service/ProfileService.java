package com.aus.authority.service;

import java.util.List;

import com.aus.authority.model.ProfileDto;
import com.aus.authority.model.ProfileTlDto;

public interface ProfileService {

	List<ProfileDto> getAllProfile(ProfileDto profileDto);

	int saveProfile(ProfileDto profileDto);

	ProfileDto getProfileById(String profileId);
	
	ProfileDto getProfileByCode(String profileCode);

	int saveProfileInfoByCurLang(ProfileTlDto t1);

	void updateProfile(ProfileDto profileDto);

	void updateProfileInfoByCurLang(ProfileTlDto t1);

	boolean checkSourceSql(String sourceSql);


}
