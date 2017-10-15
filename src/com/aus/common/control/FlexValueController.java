package com.aus.common.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aus.authority.model.SubinvDto;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.service.UserService;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.model.CityDto;
import com.aus.common.model.CodeNameDto;
import com.aus.common.model.FlexValueDto;
import com.aus.common.model.ProductItem;
import com.aus.common.model.QuartzProductItemDto;
import com.aus.common.model.QueryCodeNameDto;
import com.aus.common.service.FlexValueService;
import com.aus.common.util.Constant;
import com.aus.common.util.LocaleUtil;
import com.aus.common.util.PageHelperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

@Controller
public class FlexValueController { 
	private static Logger logger = Logger.getLogger(FlexValueController.class);
	@Autowired
	private FlexValueService flexValueService ;
	@Autowired
	private UserService userService ;
	
	/**
	 * Ajax请求获取城市。
	 */
	@RequestMapping(value = "findCity")
	@ResponseBody
	public String findCity(@RequestParam("query") String query) {
		List<FlexValueDto> list=flexValueService.getCityNameList(query); 
		ObjectMapper  objectMapper = new ObjectMapper();
		String jsonString="";
		try {
			jsonString = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) { 
			logger.error(e);
		}
		 return jsonString;
	}
	@RequestMapping(value = "checkProvinceName")
	@ResponseBody
	public String checkProvinceName(@RequestParam("scopeName") String scopeName) {
		FlexValueDto cityDto =flexValueService.getProvinceByName(scopeName); 
		if (cityDto != null) {
			return "true";
		} else {
			return "false";
		}
	}
	/**
	 * Ajax请求获取省份。
	 */
	@RequestMapping(value = "findProvince")
	@ResponseBody
	public String findProvince(@RequestParam("query") String query) {
		List<FlexValueDto> list=flexValueService.getProvinceNameList(query); 
		ObjectMapper  objectMapper = new ObjectMapper();
		String jsonString="";
		try {
			jsonString = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) { 
			logger.error(e);
		}
		 return jsonString;
	}
	/**
	 * Ajax请求获取城市和省的ID。
	 */
	@RequestMapping(value = "findCityIdAndProvinceId")
	@ResponseBody
	public String findCityIdAndProvinceId(@RequestParam("cityName") String cityName) {
		CityDto cityDto =flexValueService.findCityIdAndProvinceId(cityName); 
		ObjectMapper  objectMapper = new ObjectMapper();
		String jsonString="";
		try {
			jsonString = objectMapper.writeValueAsString(cityDto);
		} catch (JsonProcessingException e) { 
			logger.error(e);
		}
		 return jsonString;
	}
	
	/**
	 * Ajax请求校验checkCityName是否存在。
	 */
	@RequestMapping(value = "checkCityName1")
	@ResponseBody
	public String checkCityName1(@RequestParam("leaveCitySName") String leaveCitySName) {
		CityDto cityDto =flexValueService.findCityIdAndProvinceId(leaveCitySName); 
		if (cityDto != null) {
			return "true";
		} else {
			return "false";
		}
	}
	@RequestMapping(value = "checkCityName2")
	@ResponseBody
	public String checkCityName2(@RequestParam("arrivalCitySName") String leaveCitySName) {
		CityDto cityDto =flexValueService.findCityIdAndProvinceId(leaveCitySName); 
		if (cityDto != null) {
			return "true";
		} else {
			return "false";
		}
	}
	@RequestMapping(value = "checkCityName3")
	@ResponseBody
	public String checkCityName3(@RequestParam("leaveCityName") String leaveCitySName) {
		CityDto cityDto =flexValueService.findCityIdAndProvinceId(leaveCitySName); 
		if (cityDto != null) {
			return "true";
		} else {
			return "false";
		}
	}
	@RequestMapping(value = "checkCityName4")
	@ResponseBody
	public String checkCityName4(@RequestParam("arrivalCityName") String leaveCitySName) {
		CityDto cityDto =flexValueService.findCityIdAndProvinceId(leaveCitySName); 
		if (cityDto != null) {
			return "true";
		} else {
			return "false";
		}
	}
	@RequestMapping(value = "checkCityName5")
	@ResponseBody
	public String checkCityName5(@RequestParam("checkInCityName") String leaveCitySName) {
		CityDto cityDto =flexValueService.findCityIdAndProvinceId(leaveCitySName); 
		if (cityDto != null) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * Ajax请求获取省所在的城市
	 */
	@RequestMapping(value = "getCityByProvince")
	@ResponseBody
	public String getCityByProvince(@RequestParam("province") String province) {
		List<FlexValueDto> list =flexValueService.getCityByProvince(province); 
		ObjectMapper  objectMapper = new ObjectMapper();
		String jsonString="";
		try {
			jsonString = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) { 
			logger.error(e);
		}
		return jsonString;
	}
	
	
	/**
	 * 新版选省市区
	 * @param province
	 * @return
	 */
	@RequestMapping(value = "getProvincesNew")
	@ResponseBody
	public String getProvincesNew() {
		QueryCodeNameDto dto=new QueryCodeNameDto();
		List<CodeNameDto> list = flexValueService.getProvincesNew(dto);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return jsonString;
	}
	
	/**
	 * 新版选省市区
	 * @param province
	 * @return
	 */
	@RequestMapping(value = "getCitysNew")
	@ResponseBody
	public String getCitysNew(@RequestParam("parentId") String parentId) {
		QueryCodeNameDto dto=new QueryCodeNameDto();
		dto.setParentId(parentId);
		List<CodeNameDto> list = flexValueService.getCitysNew(dto);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return jsonString;
	}
	
	/**
	 * 新版选省市区
	 * @param province
	 * @return
	 */
	@RequestMapping(value = "getCountyareaNew")
	@ResponseBody
	public String getCountyareaNew(@RequestParam("parentId") String parentId) {
		QueryCodeNameDto dto=new QueryCodeNameDto();
		dto.setParentId(parentId);
		List<CodeNameDto> list = flexValueService.getCountyareaNew(dto);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return jsonString;
	}
	
	/**
	 * 得到产品品规,入口
	 * 
	 * @Title: getProductItemView
	 * @time 2015年10月22日 下午4:14:26
	 * @user "LiangYi"
	 */
	@RequestMapping(value = "/getProductItemView")
	public String getProductItemView(HttpServletRequest request) throws Exception {
		String language = LocaleUtil.getLocaleLanguage();
		// 产品系列
		List<FlexValueDto> cuxItemSeriesList = flexValueService.getFlexValueList_ebs("CUX_ITEM_SERIES", language);
		request.setAttribute("cuxItemSeriesList", cuxItemSeriesList);

		// 产品段位
		List<FlexValueDto> cuxItemLevelList = flexValueService.getFlexValueList_ebs("CUX_ITEM_LEVEL", language);
		request.setAttribute("cuxItemLevelList", cuxItemLevelList);
		return "jsp/report/productItem_list";
	}

	/**
	 * 得到产品品规
	 * 
	 * @param request
	 * @param queryTrip
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getProductItem")
	public String getProductItem(HttpServletRequest request, QuartzProductItemDto queryTrip) throws Exception {
		String language = LocaleUtil.getLocaleLanguage();
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		
		//userService.getSubinvDtoCustomer(userName, orgId)
		
		String orgId = AuthorityUtils.findPrifileValByResponsibility(request, Constant.ORG_ID, queryTrip.getUrl());
		queryTrip.setOrgId(orgId);
		
		SubinvDto dto=userService.getSubinvDtoCustomer(shiroUser.getUserName(), orgId);
		if (dto != null && !StringUtils.isEmpty(dto.getChannel_type())) {
			queryTrip.setChannel_type(dto.getChannel_type());
		}
		PageHelperUtil.PageHelperStartPage(request, queryTrip);
		List<ProductItem> productItemList = flexValueService.getProductItem(queryTrip);

		PageInfo<ProductItem> page = new PageInfo<ProductItem>(productItemList);
		request.setAttribute("page", page);
		request.setAttribute("productItemList", productItemList);

		// 产品系列
		List<FlexValueDto> cuxItemSeriesList = flexValueService.getFlexValueList_ebs("CUX_ITEM_SERIES", language);
		request.setAttribute("cuxItemSeriesList", cuxItemSeriesList);

		// 产品段位
		List<FlexValueDto> cuxItemLevelList = flexValueService.getFlexValueList_ebs("CUX_ITEM_LEVEL", language);
		request.setAttribute("cuxItemLevelList", cuxItemLevelList);
		return "jsp/report/productItem_list";
	}

}
