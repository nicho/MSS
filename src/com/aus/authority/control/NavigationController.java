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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aus.authority.annotation.AccreditAnnotation;
import com.aus.authority.model.NavigationDto;
import com.aus.authority.model.TreeDTO;
import com.aus.authority.service.NavigationService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 导航
 * 
 * @author duzh
 * 
 */
@Controller
public class NavigationController {
	private static Logger logger = Logger.getLogger(NavigationController.class);

	@Autowired
	private NavigationService navigationService;

	/**
	 * 跳转到导航管理
	 * 
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gotoUpdateNavigation")
	@AccreditAnnotation(url = "gotoUpdateNavigation.do", resourceCode = "")
	public String gotoUpdateNavigation(HttpServletRequest request)
			throws JsonProcessingException {

		Map v_map = navigationService.getNavigationAllTree(null);

		List<TreeDTO> v_navigationList = (List<TreeDTO>) v_map
				.get("v_navigationList");

		List<Map> v_mapList = (List<Map>) v_map.get("v_mapList");

		request.setAttribute("jsontree", new ObjectMapper()
				.writeValueAsString(v_navigationList));

		request.setAttribute("v_mapList", v_mapList);

		return "jsp/authority/navigation/navigation_main";
	}

	/**
	 * 删除导航
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "deleteNavigation")
	@ResponseBody
	@AccreditAnnotation(url = "gotoUpdateNavigation.do", resourceCode = "")
	public String deleteNavigation(HttpServletRequest request) {

		try {
			String navigationId = request.getParameter("menuNodeId");

			//System.out.println(" navigationId = " + navigationId);

			navigationService.deleteNavigationById(navigationId);

			AuthorityUtils.accreditFlush();

			return "{\"state\":1}";

		} catch (Exception e) {

			logger.error(e);

		}

		return "{\"state\":2}";
	}

	/**
	 * 保存导航
	 * 
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/saveNavigation")
	@AccreditAnnotation(url = "gotoUpdateNavigation.do", resourceCode = "")
	public String saveNavigation(HttpServletRequest request,
			NavigationDto navigationDto,RedirectAttributes redirectAttributes) throws JsonProcessingException {

		Map<String, String> retMap = navigationService
				.saveNavigation(navigationDto);

		if (StringUtils.equals(retMap.get("success"), "true")) {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存成功");
		} else {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存失败");
		}

		return "redirect:/gotoUpdateNavigation.do";
	}

	/**
	 * 
	 * 修改导航
	 * 
	 * @throws JsonProcessingException
	 * 
	 */
	@RequestMapping(value = "/modifyNavigation")
	@AccreditAnnotation(url = "gotoUpdateNavigation.do", resourceCode = "")
	public String modifyNavigation(HttpServletRequest request,
			NavigationDto navigationDto, RedirectAttributes redirectAttributes)
			throws JsonProcessingException {

		Subject currentUser = SecurityUtils.getSubject();

		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals()
				.getPrimaryPrincipal();

		Map<String, String> retMap = navigationService.updateNavigation(
				navigationDto, shiroUser.getLanguage());

		if (StringUtils.equals(retMap.get("success"), "true")) {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存成功");
		} else {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存失败");
		}

		AuthorityUtils.accreditFlush();

		return "redirect:/gotoUpdateNavigation.do";
	}

}
