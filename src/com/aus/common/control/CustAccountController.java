package com.aus.common.control;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.exception.ProfileException;
import com.aus.common.model.CustAccountDto;
import com.aus.common.model.DepartmemtOrganizationDto;
import com.aus.common.model.DictionaryDto;
import com.aus.common.service.CustAccountService;
import com.aus.common.service.DictionaryService;
import com.aus.common.util.Constant;
import com.aus.common.util.PageHelperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

@Controller
public class CustAccountController {
	private static Logger logger = Logger.getLogger(CustAccountController.class);
	@Autowired
	private CustAccountService custAccountService ;
	@Autowired
	private DictionaryService dictionaryService ;
	
	/**
	 * 
	 * 根据人员ID返回当前及下级人员
	 * @throws JsonProcessingException 
	 * 
	 */
	@RequestMapping(value = "findCustsByPersonId")
	@ResponseBody
	public String findCustsByPersonId(HttpServletRequest request,
			@RequestParam("personId") String personId,
			@RequestParam("orgId") String orgId,
			@RequestParam("departmentCode") String departmentCode,
			@RequestParam("channelType") String channelType)
			throws JsonProcessingException {

		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			List<CustAccountDto> allCanChoiceCustList = custAccountService.getCustByPersonId(personId,orgId,departmentCode,channelType);

			map.put("custs",allCanChoiceCustList);
		
		} catch (Exception e) {
			map.put("success","false");		
			
			map.put("message",e.getMessage());
			
			logger.error(e);
		}
		
		return new ObjectMapper().writeValueAsString(map);
	}
	
	
	@RequestMapping(value = "/gotoCustAccountList")
	public String gotoCustAccountList(HttpServletRequest request,CustAccountDto custAccountDto) throws Exception {
		try {
			String orgId = AuthorityUtils.findPrifileValByResponsibility(request, Constant.ORG_ID,  custAccountDto.getUrl());
			String channelType = AuthorityUtils.findPrifileValByResponsibility(request, Constant.CHANNEL,  custAccountDto.getUrl());
			String userType = AuthorityUtils.findPrifileValByResponsibility(request, Constant.USER_TYPE,  custAccountDto.getUrl());

//			String departmentCode = request.getParameter("departmentCode");
			
			if(StringUtils.isNotEmpty(custAccountDto.getOrgId())  && !"undefined".equals(custAccountDto.getOrgId())) {
				orgId = custAccountDto.getOrgId();	
			}
			
			if(StringUtils.isNotEmpty(custAccountDto.getChannelType())  && !"undefined".equals(custAccountDto.getChannelType())) {
				channelType = custAccountDto.getChannelType();	
			}
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("orgId", orgId);
			map.put("levelTypeDown", "Y");
			map.put("channelType", channelType);
//			map.put("departmentCode", departmentCode);
			
			if("20".equals(userType) || "30".equals(userType) ) {
				Subject currentUser = SecurityUtils.getSubject();
				ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
				map.put("userId", shiroUser.getUserId());	
			}
//			List<DictionaryDto> departmemtList = dictionaryService.getDepartment(map);
			List<DepartmemtOrganizationDto> departmemtOrganizationList = dictionaryService.getDepartmemtOrganization(map);
			
			request.setAttribute("departmemtList", departmemtOrganizationList);
			return "jsp/common/custAccount_list";
		} catch (ProfileException e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
	}
	
	@RequestMapping(value = "/viewCustAccountList")
	public String viewCustAccountList(HttpServletRequest request,CustAccountDto custAccountDto){
		try {
			//调用查询参数和分页
			PageHelperUtil.PageHelperStartPage(request,custAccountDto);
			
			String orgId = AuthorityUtils.findPrifileValByResponsibility(request, Constant.ORG_ID,  custAccountDto.getUrl());
			String channelType = AuthorityUtils.findPrifileValByResponsibility(request, Constant.CHANNEL,  custAccountDto.getUrl());
			String userType = AuthorityUtils.findPrifileValByResponsibility(request, Constant.USER_TYPE,  custAccountDto.getUrl());
			if(StringUtils.isNotEmpty(custAccountDto.getOrgId())  && !"undefined".equals(custAccountDto.getOrgId())) {
				orgId = custAccountDto.getOrgId();	
			}
			
			if(StringUtils.isNotEmpty(custAccountDto.getChannelType())   && !"undefined".equals(custAccountDto.getChannelType())) {
				channelType = custAccountDto.getChannelType();	
			}
			
			if(StringUtils.isEmpty(custAccountDto.getOrgId()) || "undefined".equals(custAccountDto.getOrgId())) {
				custAccountDto.setOrgId(orgId);
			}
			
			if(StringUtils.isEmpty(custAccountDto.getChannelType()) || "undefined".equals(custAccountDto.getChannelType())) {
				custAccountDto.setChannelType(channelType);
			}
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("orgId", orgId);
			map.put("channelType", channelType);
			
			if("20".equals(userType)) {
				Subject currentUser = SecurityUtils.getSubject();
				ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
				map.put("userId", shiroUser.getUserId());
				
				StringBuilder custwhere = new StringBuilder();
				
                custwhere.append(" AND T.CUST_ACCOUNT_ID in (select ccab.CUST_ACCOUNT_ID from ");
                custwhere.append(" apps.cux_cdm_access_basedata ccab where ccab.access_type = '2' and ccab.ORG_ID = '");
                custwhere.append(orgId).append("' and CCAB.OA_USER_ID = ' ");
                custwhere.append(shiroUser.getUserId()).append("' ) " );
                
                custAccountDto.setConditionSql(custwhere.toString());
			}else if("30".equals(userType)) {
				Subject currentUser = SecurityUtils.getSubject();
				ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
				map.put("userId", shiroUser.getUserId());
				StringBuilder custwhere = new StringBuilder();
				custwhere.append("AND T.CUST_ACCOUNT_ID in (SELECT A.CUST_ACCOUNT_ID FROM BASE_USER A WHERE A.USER_ID = '");
				custwhere.append(shiroUser.getUserId()).append("') " );
			}
			
			List<CustAccountDto>  custAccountList = custAccountService.getCustAccountList(custAccountDto);
			PageInfo<CustAccountDto> page = new PageInfo<CustAccountDto>(custAccountList);
			request.setAttribute("page", page);
			request.setAttribute("custAccountList", custAccountList);
			
//			List<DictionaryDto> departmemtList = dictionaryService.getDepartment(map);
//			request.setAttribute("departmemtList", departmemtList);
			
			List<DepartmemtOrganizationDto> departmemtOrganizationList = dictionaryService.getDepartmemtOrganization(map);
			
			request.setAttribute("departmemtList", departmemtOrganizationList);
			
			return "jsp/common/custAccount_list";
		} catch (ProfileException e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
		
	}

	public CustAccountService getCustAccountService() {
		return custAccountService;
	}

	public void setCustAccountService(CustAccountService custAccountService) {
		this.custAccountService = custAccountService;
	}

}
