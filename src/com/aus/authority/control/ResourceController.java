package com.aus.authority.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.aus.authority.model.ResourceDto;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.service.NavigationService;
import com.aus.authority.service.ResourceService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.util.PageHelperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

/**
 * 资源管理
 * 
 * @author duzh
 *
 */
@Controller
public class ResourceController {
	private static Logger logger = Logger.getLogger(ResourceController.class);
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private NavigationService navigationService ;
	
	/**
	 * 
	 * 跳转到资源管理页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gotoResourceList")
	@AccreditAnnotation(url = "gotoResourceList.do", resourceCode = "")
	public String gotoResourceList(HttpServletRequest request) {
		return "jsp/authority/resource/resource_list";
	}
	
	/**
	 * 
	 * 查看资源列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "viewResourceIndex")
	@AccreditAnnotation(url = "gotoResourceList.do", resourceCode = "")
	public String viewResourceIndex(HttpServletRequest request,ResourceDto resourceDto) {
		try {
			PageHelperUtil.PageHelperStartPage(request,resourceDto);
			
			List<ResourceDto> v_resource_list = resourceService.findAllResource(resourceDto);
			
			PageInfo<ResponsibilityDto> page = new PageInfo(v_resource_list);
			
			request.setAttribute("page", page);
			
			request.setAttribute("v_resource_list", v_resource_list); 
			
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
 		
		return "jsp/authority/resource/resource_list";
	}
	
	/**
	 * 
	 * 跳转到资源新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gotoAddResource")
	@AccreditAnnotation(url = "gotoResourceList.do", resourceCode = "")
	public String gotoAddResource(HttpServletRequest request) {
 
		List<NavigationDto>	functions =  navigationService.getAllFunctionsByResoure();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String functionsStr = "";
		try {
			functionsStr = objectMapper.writeValueAsString(functions);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		
		request.setAttribute("functionsStr", functionsStr);
		
		return "jsp/authority/resource/resource_add";
	}
	
	/**
	 * 
	 * 保存资源
	 * 
	 * @param request
	 * @param resourceDto
	 * @return
	 */
	@RequestMapping(value = "AddResource")
	@AccreditAnnotation(url = "gotoResourceList.do", resourceCode = "")
	public String AddResource(HttpServletRequest request,ResourceDto resourceDto) {
		
		Map<String,String> retMap = resourceService.saveResource(resourceDto);
		
		if( StringUtils.equals(retMap.get("success"),"true")) {
			request.setAttribute("InfoMessage", "保存成功");
		}
		
		return this.gotoAddResource(request);
	}
	
	/**
	 * 跳转到资源修改界面
	 * 
	 * @param resourceId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gotoUpdateResource")
	@AccreditAnnotation(url = "gotoResourceList.do", resourceCode = "")
	public String gotoUpdateResource(String resourceId,HttpServletRequest request) {
		
		ResourceDto dto = resourceService.findResourceById(resourceId);
		
		List<NavigationDto>	functions =  navigationService.getAllFunctionsByResoure();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String functionsStr = "";
		try {
			functionsStr = objectMapper.writeValueAsString(functions);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		
		request.setAttribute("functionsStr", functionsStr);
		
		request.setAttribute("resource", dto);
		
		return "jsp/authority/resource/resource_update";
	}
	
	/**
	 * 修改资源
	 * 
	 * @param resourceId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateResource")
	@AccreditAnnotation(url = "gotoResourceList.do", resourceCode = "")
	public String updateResource(HttpServletRequest request,ResourceDto resourceDto,RedirectAttributes redirectAttributes) {
		
		Subject currentUser = SecurityUtils.getSubject();
		
		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
		
		Map<String,String> retMap = resourceService.updateResource(resourceDto,shiroUser.getLanguage());
		
		if(StringUtils.equals(retMap.get("success"), "true")) {
			redirectAttributes.addFlashAttribute("InfoMessage","保存成功");
		} else {
			redirectAttributes.addFlashAttribute("InfoMessage","保存失败");
		}
		
		AuthorityUtils.accreditFlush();
		
		return "redirect:/gotoUpdateResource.do?resourceId="+resourceDto.getResourceId();
		
	}	
	
	/**
	 * 跳转到资源查看界面
	 * 
	 * @param resourceId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gotoViewResource")
	@AccreditAnnotation(url = "gotoResourceList.do", resourceCode = "")
	public String gotoViewResource(String resourceId,HttpServletRequest request) {
		
		ResourceDto dto =  resourceService.findResourceById(resourceId);
		
		request.setAttribute("resource", dto);
		
		return "jsp/authority/resource/resource_view";
	}
	
	/**
	 * 删除资源
	 * 
	 * @param resourceId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteResource")
	@AccreditAnnotation(url = "gotoResourceList.do", resourceCode = "")
	public String deleteResource(String resourceId,HttpServletRequest request) {
		
		resourceService.deleteResourceById(resourceId);
		
		return "redirect:/gotoResourceList.do";
	}

}
