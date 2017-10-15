package com.aus.authority.service;

import java.util.List;
import java.util.Map;

import com.aus.authority.model.LanguageDto;
import com.aus.authority.model.StoreDto;
import com.aus.authority.model.SubinvDto;
import com.aus.authority.model.UserDto;

/**
 * 用户服务
 * 
 * @author duzh
 *
 */
public interface UserService {

	UserDto findUserByUserName(String userName);

	List<UserDto> getAllUser(UserDto userDto);

	UserDto findUserById(String id);

	String updateUserById(UserDto userDto, String newpwd) throws Exception;

	String registerUser(UserDto userDto) throws Exception;

	LanguageDto getLanguageByISO(String language);

	void alterSessionLang(String nlsLanguage) throws Exception;

	String getOracleLang();

	Map<String, String> saveUserResponsibility(UserDto user);

	Long findUserByPersonId(String personId, String userId);

	String getPersonEmail(String personId);

	String getPersonOrgId(String personId);

	SubinvDto getSubinvDto(String userName,String orgId);
	
	SubinvDto getSubinvDtoCustomer(String userName,String orgId);
 
	List<StoreDto> getStoreList(StoreDto storeDto );
	
	String deleteWeiXinUser(String userName);
	String addWeiXinUser(String userId, String orgId);

}
