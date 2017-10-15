package com.aus.authority.dao;

import java.util.List;

import com.aus.authority.model.ProfileDto;
import com.aus.authority.model.ProfileTlDto;

public interface IProfileDao {

	List<ProfileDto> getAllProfile(ProfileDto profileDto);

	int saveProfile(ProfileDto profileDto);

	int saveProfileInfoByCurLang(ProfileTlDto t1);

	ProfileDto getProfileById(String profileId);

	void updateProfile(ProfileDto profileDto);

	void updateProfileInfoByCurLang(ProfileTlDto t1);

	ProfileDto getProfileByCode(String profileCode);

}
