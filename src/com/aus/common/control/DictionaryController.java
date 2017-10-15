package com.aus.common.control;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aus.common.model.BudgetCityDto;
import com.aus.common.service.DictionaryService;
import com.aus.common.util.PageHelperUtil;
import com.github.pagehelper.PageInfo;

@Controller
public class DictionaryController {
	private static Logger logger = Logger.getLogger(DictionaryController.class);
	@Autowired
	private DictionaryService dictionaryService ;

	@RequestMapping(value = "/getCityList")
	public String getCityList(HttpServletRequest request,BudgetCityDto budgetCityDto) {
		try {
//			System.out.println(URLDecoder.decode(budgetCityDto.getProvince(),"UTF-8"));
			budgetCityDto.setProvince(URLDecoder.decode(budgetCityDto.getProvince(),"UTF-8"));
			//调用查询参数和分页
			PageHelperUtil.PageHelperStartPage(request,budgetCityDto);
			List<BudgetCityDto> cityList = dictionaryService.getCity(budgetCityDto);
			request.setAttribute("province", budgetCityDto.getProvince());
			PageInfo<BudgetCityDto> page = new PageInfo<BudgetCityDto>(cityList);
			request.setAttribute("page", page);
			request.setAttribute("cityList", cityList);
			
			return "jsp/common/city_list";
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
	}
	
	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	
	
}
