package com.aus.authority.service.impl;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.authority.dao.ExecutionStringSqlDao;
import com.aus.authority.dao.IResponsibilityDao;
import com.aus.authority.dao.IUserDao;
import com.aus.authority.model.LanguageDto;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.ResponsibilityUserDto;
import com.aus.authority.model.StoreDto;
import com.aus.authority.model.SubinvDto;
import com.aus.authority.model.UserDto;
import com.aus.authority.service.UserService;
import com.aus.authority.util.Constant;
import com.aus.common.service.PersonService;

/**
 * 用户
 * 
 * @author du
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IResponsibilityDao responsibilityDao;
	@Autowired
	private PersonService personService;

	public UserDto findUserById(String userId) {

		UserDto userdto = userDao.findUserById(Long.parseLong(userId));

		return userdto;
	}

	public UserDto findUserByUserName(String userName) {

		UserDto userDto = userDao.findUserByUserName(userName);

		return userDto;
	}

	public List<UserDto> getAllUser(UserDto userDto) {

		List<UserDto> v_userList = userDao.getAllUser(userDto);

		return v_userList;
	}

	public String registerUser(UserDto userDto) {

		userDao.insertUser(userDto);

		if (StringUtils.equals(userDto.getInternalUser().trim(), "Y")) {

			Map<String, String> map = new HashMap<String, String>();

			map.put("userId", userDto.getUserId());

			userDao.exeUserSyn(map);
		}

		return Constant.SAVE_SUCCESS;

	}

	public String updateUserById(UserDto userDto, String newpwd) {

		userDao.updateUser(userDto);

		Map<String, String> params = new HashMap<String, String>();

		params.put("userId", userDto.getUserId());

		userDao.exeUserModifySyn(params);

		if (StringUtils.equals(userDto.getInternalUser().trim(), "Y") && StringUtils.isNotBlank(newpwd) && StringUtils.equals(userDto.getUserType(), "20")) {

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("userName", userDto.getUserName());

			map.put("newPwd", newpwd);

			userDao.exePwdSyn(map);

		}

		return Constant.SAVE_SUCCESS;

	}

	public LanguageDto getLanguageByISO(String languageCode) {

		LanguageDto lang = userDao.getLanguageByISO(languageCode);

		return lang;
	}

	public void alterSessionLang(String nlsLanguage) throws Exception {

		ExecutionStringSqlDao dao = new ExecutionStringSqlDao();

		dao.executionSql(" ALTER SESSION SET NLS_LANGUAGE = '" + nlsLanguage + "'");

	}

	public String getOracleLang() {
		return userDao.getOracleLang();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveUserResponsibility(UserDto user) {
		Map returnMap = new HashMap<String, String>();

		/* userDao.deleteUserResponsibility(user.getUserId()); */
		String deleteUserResponsibility = user.getDeleteUserResponsibility();
		if (!StringUtils.isEmpty(deleteUserResponsibility)) {
			String[] splitStr = deleteUserResponsibility.split(",");
			for (String res : splitStr) {
				userDao.deleteUserResponsibilityById(user.getUserId(), res);
			}

		}

		List<ResponsibilityDto> userResponsibilitys = responsibilityDao.findResponsibilityByUserId(user.getUserId());
		ArrayList<String> existList = new ArrayList<String>();
		for (ResponsibilityDto dto : userResponsibilitys) {
			existList.add(dto.getResponsibilityId());
		}

		ArrayList<String> list = new ArrayList<String>();

		if (user.getResponsibilityIds() != null) {

			for (String responsibilityId : user.getResponsibilityIds()) {

				if (list.contains(responsibilityId)) {
					continue;
				} else {

					list.add(responsibilityId);
				}
			}
			for (String responsibilityId : list) {
				if (StringUtils.isNotBlank(responsibilityId)) {
					if (!existList.contains(responsibilityId)) { // 在数据库中不存在的数据才会新增到数据库去
						ResponsibilityUserDto dto = new ResponsibilityUserDto();

						dto.setResponsibilityId(responsibilityId);

						dto.setUserId(user.getUserId());

						userDao.saveUserResponsibility(dto);
					}
				}
			}
		}

		returnMap.put("success", "true");

		return returnMap;
	}

	public Long findUserByPersonId(String personId, String userId) {
		return userDao.findUserByPersonId(personId, userId);
	}

	public String getPersonEmail(String personId) {
		return userDao.getPersonEmail(personId);
	}

	@Override
	public String getPersonOrgId(String personId) {
		return userDao.getPersonOrgId(personId);
	}

	@Override
	public SubinvDto getSubinvDto(String userName, String orgId) {
		SubinvDto subinvDto = userDao.getSubinvDto_inv(userName, orgId);
		if (subinvDto == null) {
			subinvDto = userDao.getSubinvDto_customer(userName, orgId);
		}
		return subinvDto;
	}

	@Override
	public SubinvDto getSubinvDtoCustomer(String userName, String orgId) {
		return userDao.getSubinvDto_customer(userName, orgId);
	}

	public List<StoreDto> getStoreList(StoreDto storeDto) {

		return userDao.getStoreList(storeDto);
	}

	@Override
	public String deleteWeiXinUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addWeiXinUser(String userId, String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

}
