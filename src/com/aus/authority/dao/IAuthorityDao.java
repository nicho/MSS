package com.aus.authority.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aus.authority.model.NavigationDto;
import com.aus.authority.model.ResponsibilityDto;


/**
 * 权限查询
 * @author duzh
 */
public interface IAuthorityDao {

	int isCanOperateResource(@Param(value="userId")String userId, @Param(value="resourceCode")String resourceCode,@Param(value="url")String url);

	List<String> findProfileValList(@Param(value="userId")String userId, @Param(value="profileCode")String profileName, @Param(value="url")String url);

	List<String> findUserRoles(String userId);

	List<String> findUserResources(@Param(value="responsilityId")String responsilityId,@Param(value="userId")String userId);

	List<ResponsibilityDto> findUserResponsibilitys(@Param(value="userId")String userId, @Param(value="url")String url);

	String findPrifileValFirst(@Param(value="userId")String userId, @Param(value="profileCode")String profileName, @Param(value="url")String url);

	List<NavigationDto> findLogonNavigationTrees(String sessionId);

	String findPrifileValByResponsibility(@Param(value="responsibilityId")String responsibilityId,@Param(value="profileName")String profileName);

	
	
}
