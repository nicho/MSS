package com.aus.common.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.model.FlexValueDto;
import com.aus.common.model.PersonDto;
import com.aus.common.service.FlexValueService;
import com.aus.common.service.PersonService;
import com.github.pagehelper.PageInfo;

/**
 * 
 * 
 * @author duzh
 *
 */
public class OAUtils {
	private static Logger logger = Logger.getLogger(OAUtils.class);

	/**
	 * 返回唯一的结果
	 * 
	 * @param <V>
	 * @param sourceList
	 * @return
	 */
	public static <V> V uniqueResult(List<V> sourceList) {

		if (sourceList.size() > 1 || sourceList.size() == 0) {
			return null;// 不是唯一结果，则返回空
		} else {
			return (V) sourceList.get(0);
		}
	}

	/**
	 * 返回当前用户
	 * 
	 * @param <V>
	 * @param sourceList
	 * @return
	 */
	public static ShiroUser getUser() {

		Subject currentUser = SecurityUtils.getSubject();

		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();

		return shiroUser;
	}

	/**
	 * 分页
	 * 
	 * @param <V>
	 * @param list
	 * @param request
	 * @param object
	 * @throws Exception
	 */
	public static <V> void pagination(List<V> list, HttpServletRequest request, V object) throws Exception {

		PageHelperUtil.PageHelperStartPage(request, object);

		PageInfo<V> page = new PageInfo<V>(list);

		request.setAttribute("page", page);

		request.setAttribute("vList", list);

	}

	/**
	 * 得到当前OU
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public static String getOrg(HttpServletRequest request) {

		String url = (String) request.getAttribute("initUrl");

		return AuthorityUtils.findPrifileValByResponsibility(request, Constant.ORG_ID, url);

	}

	/**
	 * 得到当前用户类型
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public static String getUserType(HttpServletRequest request) {

		String url = (String) request.getAttribute("initUrl");

		return AuthorityUtils.findPrifileValByResponsibility(request, Constant.USER_TYPE, url);

	}

	/**
	 * 得到当前渠道
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public static String getChannelType(HttpServletRequest request) {

		String url = (String) request.getAttribute("initUrl");

		return AuthorityUtils.findPrifileValByResponsibility(request, Constant.CHANNEL, url);

	}

	/**
	 * 得到当前功能职责ID
	 * 
	 * @param request
	 * @param url
	 * @return
	 */

	public static String getResponsebilityId(HttpServletRequest request, String url) {
		return AuthorityUtils.getResponsebilityId(request, url);
	}

	/**
	 * 得到当前功能职责ID
	 * 
	 * @param request
	 * @param url
	 * @return
	 */

	public static String getResponsebilityId(HttpServletRequest request) {
		String url = (String) request.getAttribute("initUrl");

		return AuthorityUtils.getResponsebilityId(request, url);
	}

	/**
	 * 初始化用户环境
	 * 
	 * @return
	 */
	public static void initUserAttribute(HttpServletRequest request) {
		ShiroUser shiroUser = OAUtils.getUser();

		String orgId = OAUtils.getOrg(request);

		request.setAttribute("loginPersonName", shiroUser.getName());

		request.setAttribute("loginPersonId", shiroUser.getPersonId());

		request.setAttribute("ouOrganizationId", orgId);

		PersonDto personDto = new PersonDto();

		personDto.setPersonId(shiroUser.getPersonId());

		personDto.setOrgId(orgId);

		PersonService personService = SpringContextHolder.getBean("PersonService");

		personDto = OAUtils.uniqueResult(personService.getPersonOrganization(personDto));

		if (personDto != null) {
			request.setAttribute("hrOrganizationId", personDto.getDepartmentCode());
			request.setAttribute("hrOrganizationName", personDto.getDepartmentName());
			request.setAttribute("positionId", personDto.getPositionId());
		} else {
			request.setAttribute("loginPersonId", "");
			request.setAttribute("loginPersonName", "");

		}
	}

	/**
	 * 获取经销商机构
	 * 
	 * @Title: getOrgId
	 * @time 2015年12月3日 下午3:14:17
	 * @user "LiangYi"
	 */
	public static String getOrgId(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
		Iterator<Entry<String, String>> it = ReadProperties.getDomainMap().entrySet().iterator();
		String orgId = "81";
		while (it.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) it.next();
			if (tempContextUrl.equals(entry.getValue())) {
				orgId = entry.getKey();
				if ("default2".equals(entry.getKey())) {
					orgId = "81";
				}
				if ("default3".equals(entry.getKey())) {
					orgId = "82";
				}
				if ("default4".equals(entry.getKey())) {
					orgId = "182";
				}
				if ("default5".equals(entry.getKey()) || "default5_1".equals(entry.getKey())) {
					orgId = "181";
				}
				if ("default6".equals(entry.getKey())) {
					orgId = "221";
				}
				if ("default7".equals(entry.getKey())) {
					orgId = "543";
				}

			}
		}
		return orgId;
	}

	/**
	 * 得到所有省份
	 * 
	 * @return
	 */
	public static List<FlexValueDto> getAllProvince() {

		FlexValueService flexValueService = SpringContextHolder.getBean("FlexValueService");

		List<FlexValueDto> cuxProvinceList = flexValueService.getAllProvince();

		return cuxProvinceList;
	}

}
