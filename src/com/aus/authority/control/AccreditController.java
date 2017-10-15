package com.aus.authority.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aus.authority.model.ProfileDto;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.RoleDto;
import com.aus.authority.service.AuthorityService;
import com.aus.authority.service.ProfileService;
import com.aus.authority.service.ResponsibilityService;
import com.aus.authority.service.RoleService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.util.OAUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 通用权限控制
 * 
 * @author duzh
 *
 */
@Controller
public class AccreditController {
	private static Logger logger = Logger.getLogger(AccreditController.class);
	@Autowired
	private AuthorityService authorityService;
	/**
	 * 
	 * 职责切换
	 * 
	 * @param request
	 * @return
	 */ 
	@RequestMapping(value = "refreshResponsibility")
	@ResponseBody
	public String refreshResponsibility(HttpServletRequest request,
			@RequestParam("responsibilityId") String responsibilityId,
			@RequestParam("url") String url) {

		String sessionId = request.getSession().getId();

		List<String> responsibilitys = AuthorityUtils
				.findUserResponsibilityArrays(sessionId, url);

		if ( !responsibilitys.contains(responsibilityId) ) {
			return "false";
		}
		
		ShiroUser shiroUser = OAUtils.getUser();
		
		Map<String, String> urlResponsibilityMap = authorityService.getUrlResponsibilityMap(shiroUser.getUserId(), request);		

		urlResponsibilityMap.put(url, responsibilityId);

		authorityService.setUrlResponsibilityMap(shiroUser.getUserId(), urlResponsibilityMap,request);

		return "true";
	}
	
	@Autowired
	private RoleService roleService ;
	
	
	/**
	 * 返回角色信息
	 * 
	 * @param request
	 * @param roleId
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "findRoleInfo")
	@ResponseBody
	public String findRoleInfo(HttpServletRequest request,
			@RequestParam("roleId") String roleId) throws JsonProcessingException {
		
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			RoleDto dto = roleService.getRoleById(roleId);	
			
			map.put("roleCode",dto.getRoleCode());			
			map.put("roleDesc",dto.getRoleDesc());
			map.put("roleName",dto.getRoleName());
			
			map.put("success","true");			
				
		} catch (Exception e) {			
			map.put("success","false");		
			
			map.put("message",e.getMessage());
			
			logger.error(e);
		}
		
		return new ObjectMapper().writeValueAsString(map);
	}
	
	@Autowired
	private ProfileService profileService;
	
	
	/**
	 * 
	 * 返回职责信息
	 * @throws JsonProcessingException 
	 * 
	 */
	@RequestMapping(value = "findProfileInfo")
	@ResponseBody
	public String findProfileInfo(HttpServletRequest request,
			@RequestParam("profileId") String profileId) throws JsonProcessingException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			ProfileDto dto = profileService.getProfileById(profileId);
			
			List<Map<String,String>> v_map = AuthorityUtils.getProDictByName(dto.getProfileCode());

			map.put("profileCode",dto.getProfileCode());			
			map.put("profileName",dto.getProfileName());			
			map.put("profileDesc",dto.getProfileDesc());			
			map.put("profileVal",v_map);
			
			map.put("success", "true");		
		
		} catch (Exception e) {
			map.put("success","false");		
			
			map.put("message",e.getMessage());
			
			logger.error(e);
		}
		
		return new ObjectMapper().writeValueAsString(map);
	}
	
	@Autowired
	private ResponsibilityService responsibilityService;
	
	/**
	 * 
	 * 返回职责信息
	 * @throws JsonProcessingException 
	 * 
	 */
	@RequestMapping(value = "findResponsibilityInfo")
	@ResponseBody
	public String findResponsibilityInfo(HttpServletRequest request,
			@RequestParam("responsibilityId") String responsibilityId) throws JsonProcessingException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			ResponsibilityDto dto = responsibilityService.getResponsibilityById(responsibilityId);

			map.put("responsibilityCode",dto.getResponsibilityCode());			
			map.put("responsibilityName",dto.getResponsibilityName());			
			map.put("responsibilityDesc",dto.getResponsibilityDesc());			
			map.put("beginDate","");
			/*map.put("endDate",dto.getEndDate());*/
			
			map.put("success", "true");		
		
		} catch (Exception e) {
			map.put("success","false");		
			
			map.put("message",e.getMessage());
			
			logger.error(e);
		}
		
		return new ObjectMapper().writeValueAsString(map);
	}
	
}
