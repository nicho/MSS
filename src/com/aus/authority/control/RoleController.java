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
import com.aus.authority.model.NavigationDto;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.RoleDto;
import com.aus.authority.model.RoleFuncDto;
import com.aus.authority.model.RoleResourceDto;
import com.aus.authority.model.TreeDTO;
import com.aus.authority.service.NavigationService;
import com.aus.authority.service.RoleService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.util.ExportUtil;
import com.aus.common.util.OAUtils;
import com.aus.common.util.PageHelperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

/**
 * 角色管理
 * 
 * @author duzh
 *
 */
@Controller
public class RoleController {
	private static Logger logger = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private NavigationService navigationService;
	
	/**
	 * 跳转到角色管理界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoRoleList")
	@AccreditAnnotation(url="gotoRoleList.do", resourceCode = "")
	public String gotoRoleList(HttpServletRequest request) {		
		
		return "jsp/authority/role/role_list";
	}
	
	/**
	 * 查询角色列表
	 * 
	 * @param request
	 * @param userDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewRoleIndex")
	@AccreditAnnotation(url="gotoRoleList.do", resourceCode = "")
	public String viewRoleIndex(HttpServletRequest request,RoleDto roleDto) {
		try {
			PageHelperUtil.PageHelperStartPage(request,roleDto);
			
			List<RoleDto> v_roleList = roleService.getAllRole(roleDto);
			
			PageInfo<RoleDto> page = new PageInfo(v_roleList);
			
			request.setAttribute("page", page);
			
			request.setAttribute("v_roleList", v_roleList); 
			
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
		
		return "jsp/authority/role/role_list";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/exportRoleList")
	@AccreditAnnotation(url="gotoRoleList.do", resourceCode = "")
	public String exportRoleList (HttpServletRequest request,HttpServletResponse response,RoleDto roleDto) {
		
		String columnPropertys="roleName,roleCode,roleDesc";

		List<RoleDto> v_roleList = roleService.getAllRole(roleDto);
		
		ExportUtil.exportFile(request, response, v_roleList,"roleList",columnPropertys);	
		
		
		
		return null;
	}
	/**
	 * 跳转到角色新增页面
	 * 
	 * @param request
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("gotoAddRole")
	@AccreditAnnotation(url="gotoRoleList.do", resourceCode = "")
	public String gotoAddRole(HttpServletRequest request) throws JsonProcessingException {
		
		List<TreeDTO> v_menu_resource = navigationService.getMeunResourceTree(null);
		
		request.setAttribute("jsontree", new ObjectMapper().writeValueAsString(v_menu_resource));
		
		return "jsp/authority/role/role_add";
	}
	
	
	/**
	 * 保存新增角色
	 * 
	 * @param request
	 * @param userDto
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("saveRole")
	@AccreditAnnotation(url="gotoRoleList.do", resourceCode = "")
	public String saveRole(HttpServletRequest request,RoleDto roleDto,RedirectAttributes redirectAttributes) throws JsonProcessingException {				
		
		RoleDto queryRole = new RoleDto();
		
		queryRole.setRoleName(roleDto.getRoleName());
		
		if(roleService.getRoleCountByRole(queryRole).size() > 0) {
			redirectAttributes.addFlashAttribute("InfoMessage","此角色名称已存在");
			return "redirect:/gotoAddRole.do";
		}
		
		queryRole.setRoleName("");
		queryRole.setRoleCode(roleDto.getRoleCode());
		if(roleService.getRoleCountByRole(queryRole).size() > 0) {
			redirectAttributes.addFlashAttribute("InfoMessage","此角色编码已存在");
			return "redirect:/gotoAddRole.do";
		}
		
		Map<String,String> retMap = roleService.saveRole(roleDto);//保存角色	
		
		if(StringUtils.equals(retMap.get("success"), "true")) {
			redirectAttributes.addFlashAttribute("InfoMessage","保存成功");
		} else {
			redirectAttributes.addFlashAttribute("InfoMessage","保存失败");
		}
		return "redirect:/gotoAddRole.do";
	}
	
	/**
	 * 跳转到角色修改页面
	 * 
	 * @param tid
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoUpdateRole")
	@AccreditAnnotation(url="gotoRoleList.do", resourceCode = "")
	public String gotoUpdateRole(String roleId,HttpServletRequest request){		
		
		try {			
			RoleDto role = roleService.getRoleById(roleId);
			
			List<RoleFuncDto> v_rolefunc_list = roleService.getRoleFuncById(roleId);//得到角色分配的功能
			
			List<RoleResourceDto> v_roleresource_list = roleService.getRoleResourceById(roleId);//得到角色分配的资源
			
			int a = v_rolefunc_list.size();
			
			int b = v_roleresource_list.size();
			
			String[] checkedNodes = new String[a+b];
			
			for (int i = 0; i < v_rolefunc_list.size(); i++) { 				
				checkedNodes[i] = v_rolefunc_list.get(i).getMenuNodeId();				
			}
			
			for (int i = 0; i < v_roleresource_list.size(); i++ ) {				
				checkedNodes[i+a] = "R_"+v_roleresource_list.get(i).getResourceId();				
			}
			
			
			List<TreeDTO> v_navigationList = navigationService.getMeunResourceTree(checkedNodes);
			
			request.setAttribute("jsontree", new ObjectMapper().writeValueAsString(v_navigationList));
			
			request.setAttribute("role", role);
			
			return "jsp/authority/role/role_update";
			
			
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
	}
	
	/**
	 * 修改角色
	 * 
	 * @param request
	 * @param userDto
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("updateRole")
	@AccreditAnnotation(url="gotoRoleList.do", resourceCode = "")
	public String updateRole(HttpServletRequest request,RoleDto roleDto,RedirectAttributes redirectAttributes) throws JsonProcessingException {
		
		/**验证角色名称和角色编码是否存在**/
		RoleDto queryRole = new RoleDto();
		queryRole.setRoleId(roleDto.getRoleId());
		
		List<RoleDto> oldRoleList = roleService.getRoleCountByRole(queryRole);
		RoleDto oldRole = oldRoleList.get(0);
		
		queryRole.setRoleId("");
		queryRole.setRoleName(roleDto.getRoleName());
		if(oldRole != null && !oldRole.getRoleName().equals(roleDto.getRoleName())) {
			if(roleService.getRoleCountByRole(queryRole).size() > 0) {
				redirectAttributes.addFlashAttribute("InfoMessage","此角色名称已存在");
				return "redirect:/gotoUpdateRole.do?roleId=" + roleDto.getRoleId();
			}
		}
		
		if(oldRole != null && !oldRole.getRoleCode().equals(roleDto.getRoleCode())) {
			queryRole.setRoleName("");
			queryRole.setRoleCode(roleDto.getRoleCode());
			if(roleService.getRoleCountByRole(queryRole).size() > 0) {
				redirectAttributes.addFlashAttribute("InfoMessage","此角色编码已存在");
				return "redirect:/gotoUpdateRole.do?roleId=" + roleDto.getRoleId();
			}
		}
		/**验证角色名称和角色编码是否存在 END**/
		
		Subject currentUser = SecurityUtils.getSubject();
		
		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();	
		
		Map<String,String> retMap = roleService.updateRole(roleDto,shiroUser.getLanguage());//保存角色	
		
		if(StringUtils.equals(retMap.get("success"), "true")) {
			redirectAttributes.addFlashAttribute("InfoMessage","修改成功");
		} else {
			redirectAttributes.addFlashAttribute("InfoMessage","修改失败");
		}
		
		AuthorityUtils.accreditFlush();
		
		return "redirect:/gotoUpdateRole.do?roleId="+roleDto.getRoleId();
		
	}
	
	@RequestMapping("deleteRole")
	@AccreditAnnotation(url="gotoRoleList.do", resourceCode = "")
	public String deleteRole(String roleId,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		try{
			if(roleService.checkUsedRole(roleId)){
				redirectAttributes.addFlashAttribute("InfoMessage","角色已被使用,不能删除");				
			} else {	
				roleService.deleteRoleById(roleId);
				redirectAttributes.addFlashAttribute("InfoMessage","删除成功");		
			}
		} catch(Exception e) {
			logger.error(e);
			redirectAttributes.addFlashAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
		}
		
		return "redirect:/gotoRoleList.do";
		
	}
	
	
	/**
	 * 跳转到角色查看页面
	 * 
	 * @param tid
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoViewRole")
	@AccreditAnnotation(url="gotoRoleList.do", resourceCode = "")
	public String gotoViewRole(String roleId,HttpServletRequest request){
		try {			
			List<NavigationDto>  v_navigationList = navigationService.getNavigationTreeByCheckRoleId(roleId);
			
			RoleDto role = roleService.getRoleById(roleId);
			
			request.setAttribute("v_navigationList_role", v_navigationList);
			
			request.setAttribute("role", role);
			
			return "jsp/authority/role/role_view"; 
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		} 
	}	
	

}
