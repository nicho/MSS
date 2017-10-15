package com.aus.authority.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aus.authority.annotation.AccreditAnnotation;
import com.aus.authority.model.ProfileDto;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.ResponsibilityInvOrgDto;
import com.aus.authority.model.ResponsibilityProfileDto;
import com.aus.authority.model.ResponsibilityRoleDto;
import com.aus.authority.model.RoleDto;
import com.aus.authority.service.ProfileService;
import com.aus.authority.service.ResponsibilityService;
import com.aus.authority.service.RoleService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.util.ExportUtil;
import com.aus.common.util.PageHelperUtil;
import com.github.pagehelper.PageInfo;

/**
 * 
 * 职责管理
 * 
 * @author duzh
 * 
 */
@Controller
public class ResponsibilityController {
	private static Logger logger = Logger.getLogger(ResponsibilityController.class);
	@Autowired
	private ResponsibilityService responsibilityService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private RoleService roleService;
		
	/**
	 * 
	 * 跳转到职责管理页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gotoResponsibilityList")
	@AccreditAnnotation(url = "gotoResponsibilityList.do", resourceCode = "")
	public String gotoResponsibilityList(HttpServletRequest request) {
		return "jsp/authority/responsibility/responsibility_list";
	}
	
	/**
	 * 
	 * 查询职责列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "viewResponsibilityIndex")
	@AccreditAnnotation(url = "gotoResponsibilityList.do", resourceCode = "")
	public String viewResponsibilityIndex(HttpServletRequest request,ResponsibilityDto responsibilityDto) {
		try {
			PageHelperUtil.PageHelperStartPage(request,responsibilityDto);
			
			List<ResponsibilityDto> v_responsibility_list = responsibilityService.getAllResponsibility(responsibilityDto);
			
			PageInfo<ResponsibilityDto> page = new PageInfo(v_responsibility_list);
			
			request.setAttribute("page", page);
			
			request.setAttribute("v_responsibility_list", v_responsibility_list); 
			
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
		
		return "jsp/authority/responsibility/responsibility_list";
	}
	
	/**
	 * 
	 * 导出职责列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "exportResponsibility")
	@AccreditAnnotation(url = "gotoResponsibilityList.do", resourceCode = "")
	public String ExportResponsibilityIndex(HttpServletRequest request,HttpServletResponse response,ResponsibilityDto responsibilityDto) {
		
		String columnPropertys="responsibilityName,responsibilityCode,responsibilityDesc,beginDate,endDate";

		List<ResponsibilityDto> v_responsibility_list = responsibilityService.getAllResponsibility(responsibilityDto);
		
		ExportUtil.exportFile(request, response, v_responsibility_list,"responsibilityList",columnPropertys);	
		
		return null;
	}
	
	
	
	
	/**
	 * 
	 * 跳转到职责新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gotoAddResponsibility")
	@AccreditAnnotation(url = "gotoResponsibilityList.do", resourceCode = "")
	public String gotoAddResponsibility(HttpServletRequest request) {
		
		ProfileDto dto = new ProfileDto();
		
		RoleDto roleDto = new RoleDto();
		
		List<ProfileDto> profiles =  profileService.getAllProfile(dto);
		
		List<ResponsibilityInvOrgDto> responsibilityInvList = responsibilityService.getOrganizationInvList();
		
		List<RoleDto> roles = roleService.getAllRole(roleDto);
		
		request.setAttribute("profiles", profiles);
		
		request.setAttribute("roles", roles);
		
		request.setAttribute("responsibilityInvList", responsibilityInvList);
				
		return "jsp/authority/responsibility/responsibility_add";
	}
	
	/**
	 * 
	 * 保存职责
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addResponsibility")
	@AccreditAnnotation(url = "gotoResponsibilityList.do", resourceCode = "")
	public String addResponsibility(HttpServletRequest request,ResponsibilityDto responsibilityDto,RedirectAttributes redirectAttributes) {
		
		ResponsibilityDto queryResponsibility = new ResponsibilityDto();
		
		queryResponsibility.setResponsibilityName(responsibilityDto.getResponsibilityName());
		
		if(responsibilityService.getResponsibilityByResponsibility(queryResponsibility).size() > 0) {
			redirectAttributes.addFlashAttribute("InfoMessage","此职责名称已存在");
			return "redirect:/gotoAddResponsibility.do";
		}
		queryResponsibility.setResponsibilityName("");
		queryResponsibility.setResponsibilityCode(responsibilityDto.getResponsibilityCode());
		if(responsibilityService.getResponsibilityByResponsibility(queryResponsibility).size() > 0) {
			redirectAttributes.addFlashAttribute("InfoMessage","此职责编码已存在");
			return "redirect:/gotoAddResponsibility.do";
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		
		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
		
		Map<String,String> resultMap  = responsibilityService.saveResponsibility(responsibilityDto, shiroUser.getUserId());
		
		if( StringUtils.equals(resultMap.get("success"),"true")) {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存成功");
		} else {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存失败");
		}
		
		return "redirect:/gotoUpdateResponsibility.do?responsibilityId="+resultMap.get("responsibilityId");
	}
	
	/**
	 * 
	 * 跳转到职责修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gotoUpdateResponsibility")
	@AccreditAnnotation(url = "gotoResponsibilityList.do", resourceCode = "")
	public String gotoUpdateResponsibility(String responsibilityId,HttpServletRequest request) {
		
		ResponsibilityDto responsibilityDto = responsibilityService.getResponsibilityById(responsibilityId);
		
		List<ResponsibilityRoleDto> Resroles = responsibilityService.getResponsibilityRoleById(responsibilityId);
		
		List<ResponsibilityProfileDto> Resprofiles = responsibilityService.getResponsibilityProfileById(responsibilityId);
		
		ProfileDto dto = new ProfileDto();
		
		RoleDto roleDto = new RoleDto();
		
		List<ProfileDto> profiles =  profileService.getAllProfile(dto);
		
		List<ResponsibilityInvOrgDto> responsibilityInvList = responsibilityService.getOrganizationInvList();
		
		List<ResponsibilityInvOrgDto> invList = responsibilityService.getOrganizationInvByResponsibilityId(responsibilityId);
		
		List<RoleDto> roles = roleService.getAllRole(roleDto);
		
		request.setAttribute("profiles", profiles);
		
		request.setAttribute("roles", roles);
		
		request.setAttribute("responsibilityDto", responsibilityDto);
		
		request.setAttribute("Resroles", Resroles);
		
		request.setAttribute("Resprofiles", Resprofiles);		

		request.setAttribute("responsibilityInvList", responsibilityInvList);
		
		request.setAttribute("invList", invList);
		
		return "jsp/authority/responsibility/responsibility_update";
	}
	
	/**
	 * 
	 * 修改职责
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateResponsibility")
	@AccreditAnnotation(url = "gotoResponsibilityList.do", resourceCode = "")
	public String updateResponsibility(HttpServletRequest request,ResponsibilityDto responsibilityDto,RedirectAttributes redirectAttributes) {
		
		/**验证职责名称是否已经存在**/
		ResponsibilityDto queryResponsibility = new ResponsibilityDto();
		queryResponsibility.setResponsibilityId(responsibilityDto.getResponsibilityId());
		List<ResponsibilityDto> responsibilityDtoList = responsibilityService.getResponsibilityByResponsibility(queryResponsibility);
		ResponsibilityDto oldResponsibilityDto = responsibilityDtoList.get(0);
		
		if(oldResponsibilityDto != null && !oldResponsibilityDto.getResponsibilityName().equals(responsibilityDto.getResponsibilityName())) {
			queryResponsibility.setResponsibilityName(responsibilityDto.getResponsibilityName());
			queryResponsibility.setResponsibilityId("");
			if(responsibilityService.getResponsibilityByResponsibility(queryResponsibility).size() > 0) {
				redirectAttributes.addFlashAttribute("InfoMessage","此职责名称已存在");
				return "redirect:/gotoUpdateResponsibility.do?responsibilityId="+responsibilityDto.getResponsibilityId();
			}
		}
		/**验证职责名称是否已经存在**/
		
		Subject currentUser = SecurityUtils.getSubject();
		
		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();	
		
		Map<String,String> resultMap  = responsibilityService.updateResponsibility(responsibilityDto,shiroUser.getLanguage(), shiroUser.getUserId());
		
		if( StringUtils.equals(resultMap.get("success"),"true")) {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存成功");
		} else {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存失败");
		}
		
		AuthorityUtils.accreditFlush();
		
		return "redirect:/gotoUpdateResponsibility.do?responsibilityId="+responsibilityDto.getResponsibilityId();
	}
	
	/**
	 * 
	 * 跳转到职责查看页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gotoViewResponsibility")
	@AccreditAnnotation(url = "gotoResponsibilityList.do", resourceCode = "")
	public String gotoViewResponsibility(String responsibilityId,HttpServletRequest request) {
		ResponsibilityDto responsibilityDto = responsibilityService.getResponsibilityById(responsibilityId);
		
		List<ResponsibilityRoleDto> Resroles = responsibilityService.getResponsibilityRoleById(responsibilityId);
		
		List<ResponsibilityProfileDto> Resprofiles = responsibilityService.getResponsibilityProfileById(responsibilityId);

		List<ResponsibilityInvOrgDto> invList = responsibilityService.getOrganizationInvByResponsibilityId(responsibilityId);
		
		request.setAttribute("responsibilityDto", responsibilityDto);
		
		request.setAttribute("Resroles", Resroles);
		
		request.setAttribute("Resprofiles", Resprofiles);					

		request.setAttribute("invList", invList);
		
		return "jsp/authority/responsibility/responsibility_view";
	}
	
	
	/**
	 * 获取角色
	 *Title: getAllRoleInfo
	 *Description: getAllRoleInfo
	 *@author Gzg
	 * @throws Exception 
	 *@date 2017年9月29日 上午9:13:01
	 */
	@RequestMapping(value = "/getAllRoleInfo")
	public String getAllRoleInfo(HttpServletRequest request) throws Exception{
		RoleDto roleDto = new RoleDto();
		String roleName = request.getParameter("roleName");
		String roleCode = request.getParameter("roleCode");
		roleDto.setRoleName(roleName);
		roleDto.setRoleCode(roleCode);
		PageHelperUtil.PageHelperStartPage(request, roleDto);
		List<RoleDto> roles = roleService.getAllRole(roleDto);
		PageInfo<RoleDto> page = new PageInfo<RoleDto>(roles);
		request.setAttribute("list", roles);
		request.setAttribute("page", page);
		return "jsp/common/getAllRoleInfo";
		
	}
	

}
