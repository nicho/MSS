package com.aus.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aus.common.model.BudgetAbTypeDto;
import com.aus.common.model.BudgetCityDto;
import com.aus.common.model.DepartmemtOrganizationDto;
import com.aus.common.model.DictionaryDto;
import com.aus.common.model.OrgChannelTypeDto;

public interface DictionaryService {
	
	
	List<DepartmemtOrganizationDto> getDepartmemtOrganization(Map<String, String> map);
	
	List<BudgetCityDto> getCity(BudgetCityDto budgetCityDto);
	
	List<BudgetAbTypeDto> getBudgetABType();
	
	List<DictionaryDto> getExpenseType();
	
	List<DictionaryDto> getBookType();
	
	List<DictionaryDto> getBookBindingType();
	
	List<DictionaryDto> getDictionaryType(String typename);
	
	List<DictionaryDto> getExpenseDocType();

	List<DictionaryDto> getNeedEncumbrance();

	List<DictionaryDto> getGlAccount();

	List<DictionaryDto> getBusinessType();

	List<DictionaryDto> getExpenseAccountStatus();

	List<DictionaryDto> getAmountType();

	List<DictionaryDto> getYear();

	List<DictionaryDto> getBoundaryType();

	List<DictionaryDto> getDepartment(Map<String, String> map);

	List<DictionaryDto> getEncumbranceStatus();

	List<DictionaryDto> getPeriod();
	
	List<DictionaryDto> getPeriodModify();
	
	List<DictionaryDto> getOrg(Map<String,String> map);
	
	List<DictionaryDto> getOrgNew(Map<String,String> map);
	
	List<DictionaryDto> getChannelType(Map<String,String> map);
	
	List<DictionaryDto> getReimburseStatus();
	/****
	 * conditionSql
	 * @param map
	 * @return
	 */
	List<OrgChannelTypeDto> getOrgChannelType(Map<String,String> map);
	
	List<DictionaryDto> getQuarter();
	
	String getOrgName(String orgId);
	
	List<DictionaryDto> getSeriesNameByOrgId(String orgId);
	
	List<DictionaryDto> getHrLectorType();
}
