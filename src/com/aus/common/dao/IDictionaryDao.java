package com.aus.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aus.common.model.BudgetAbTypeDto;
import com.aus.common.model.BudgetCityDto;
import com.aus.common.model.DepartmemtOrganizationDto;
import com.aus.common.model.DictionaryDto;
import com.aus.common.model.OrgChannelTypeDto;

public interface IDictionaryDao {

	List<DepartmemtOrganizationDto> getDepartmemtOrganization(Map<String, String> map);
	
	List<BudgetCityDto> getCity(BudgetCityDto budgetCityDto);
	
	List<String> getCityByProvince(@Param(value="province")String province);

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

	List<DictionaryDto> getReimburseStatus();

	List<DictionaryDto> getQuarter();
	/****
	 * orgId conditionSql
	 * 
	 * @param map
	 * @return
	 */
	List<DictionaryDto> getOrg(Map<String, String> map);

	/****
	 * channelType conditionSql
	 * 
	 * @param map
	 * @return
	 */
	List<DictionaryDto> getChannelType(Map<String, String> map);

	/****
	 * conditionSql
	 * 
	 * @param map
	 * @return
	 */
	List<OrgChannelTypeDto> getOrgChannelType(Map<String, String> map);

	List<DictionaryDto> getOrgNew(Map<String, String> map);
	
	String getOrgName(@Param(value="orgId")String orgId);
	
	List<DictionaryDto> getSeriesNameByOrgId(@Param(value="orgId")String orgId);
	
	List<DictionaryDto> getHrLectorType();
}
