package com.aus.authority.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aus.authority.model.LanguageDto;
import com.aus.authority.model.ResponsibilityUserDto;
import com.aus.authority.model.StoreDto;
import com.aus.authority.model.SubinvDto;
import com.aus.authority.model.UserDto;

public interface IUserDao {

	UserDto findUserById(long userId);

	UserDto findUserByUserName(String userName);

	List<UserDto> getAllUser(UserDto userDto);

	int insertUser(UserDto userDto);

	int updateUser(UserDto userDto);

	LanguageDto getLanguageByISO(String languageCode);

	void alterSessionLang(String nlsLanguage);

	String getOracleLang();

	void deleteUserResponsibility(String userId);
	
	void deleteUserResponsibilityById(@Param(value="userId")String userId,@Param(value="responsibilityId")String responsibilityId);

	void saveUserResponsibility(ResponsibilityUserDto dto);
	
	void exeUserSyn(Map<String,String> map);
	
	void exePwdSyn(Map<String,Object> map);
	
	void exeUserModifySyn(Map<String,String> map);

	Long findUserByPersonId(@Param(value="personId")String personId,@Param(value="userId")String userId);

	String getPersonEmail(String personId);

	String getPersonOrgId(String personId);
	
	SubinvDto getSubinvDto_inv(@Param(value="userName")String userName,@Param(value="orgId")String orgId);

	SubinvDto getSubinvDto_customer(@Param(value="userName")String userName,@Param(value="orgId")String orgId);
	
	List<StoreDto> getStoreList(StoreDto storeDto );
}
