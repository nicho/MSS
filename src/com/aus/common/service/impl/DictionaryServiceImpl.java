package com.aus.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.model.BudgetAbTypeDto;
import com.aus.common.model.BudgetCityDto;
import com.aus.common.model.DepartmemtOrganizationDto;
import com.aus.common.model.DictionaryDto;
import com.aus.common.model.OrgChannelTypeDto;
import com.aus.common.dao.IDictionaryDao;
import com.aus.common.service.DictionaryService;

@Service("DictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private IDictionaryDao dictionaryDao;
	
	/***
	 * param  orgId  组织
	 * 
	 * 查询组织对应的部门
	 */
	public List<DepartmemtOrganizationDto> getDepartmemtOrganization(Map<String, String> map) {
		return dictionaryDao.getDepartmemtOrganization(map);
	}
	
	public List<BudgetCityDto> getCity(BudgetCityDto budgetCityDto) {
		return this.dictionaryDao.getCity(budgetCityDto);
	}
	/****
	 * conditionSql
	 * @param map
	 * @return
	 */
	public List<OrgChannelTypeDto> getOrgChannelType(Map<String,String> map) {
		return dictionaryDao.getOrgChannelType(map);
	}
	
	/****
	 * 报销单据状态
	 */
	public List<DictionaryDto> getReimburseStatus() {
		return dictionaryDao.getReimburseStatus();
	}
	/****
	 * 按权限获取渠道
	 */
	@Override
	public List<DictionaryDto> getChannelType(Map<String,String> map) {
		// TODO Auto-generated method stub
		return this.dictionaryDao.getChannelType(map);
	}
	
	public List<DictionaryDto> getOrg(Map<String,String> map) {
		return this.dictionaryDao.getOrg(map);
	}
	/****
	 * 金额类型、限定范围 组合  
	 */
	public List<BudgetAbTypeDto> getBudgetABType() {
		return this.dictionaryDao.getBudgetABType();
	}
	/***
	 * 费用类型
	 */
	public List<DictionaryDto> getExpenseType() {
		return this.dictionaryDao.getExpenseType();
	}
	
	/***
	 * 图书类型
	 */
	public List<DictionaryDto> getBookType() {
		return this.dictionaryDao.getBookType();
	}
	
	/***
	 * 图书版面类型
	 */
	public List<DictionaryDto> getBookBindingType() {
		return this.dictionaryDao.getBookBindingType();
	}
	
	/***
	 * 查询字典类型
	 */
	public List<DictionaryDto> getDictionaryType(String typename) {
		return this.dictionaryDao.getDictionaryType(typename);
	}	
	
	/****
	 * 费用申请状态
	 */
	public List<DictionaryDto> getEncumbranceStatus() {
		return this.dictionaryDao.getEncumbranceStatus();
	}
	/***
	 * 部门
	 * map orgId - 必需   userId - 非必需
	 */
	public List<DictionaryDto> getDepartment(Map<String,String> map) {
		return this.dictionaryDao.getDepartment(map);
	}
	
	/***
	 * 单据类型
	 */
	public List<DictionaryDto> getExpenseDocType() {
		return dictionaryDao.getExpenseDocType();
	}

	/***
	 * 是否需要申请
	 */
	public List<DictionaryDto> getNeedEncumbrance() {
		return dictionaryDao.getNeedEncumbrance();
	}

	/***
	 * 科目主体
	 */
	public List<DictionaryDto> getGlAccount() {
		return dictionaryDao.getGlAccount();
	}

	/***
	 * 业务类型
	 */
	public List<DictionaryDto> getBusinessType() {
		return dictionaryDao.getBusinessType();
	}

	/****
	 * 科目状态
	 */
	public List<DictionaryDto> getExpenseAccountStatus() {
		return dictionaryDao.getExpenseAccountStatus();
	}

	/***
	 * 查询金额类型
	 */
	public List<DictionaryDto> getAmountType() {
		return dictionaryDao.getAmountType();
	}

	/***
	 * 获取年份
	 */
	public List<DictionaryDto> getYear() {
		return dictionaryDao.getYear();
	}

	/***
	 * 查询限定范围
	 */
	public List<DictionaryDto> getBoundaryType() {
		return dictionaryDao.getBoundaryType();
	}
	/****
	 * 获取区间-可填写
	 */
	public List<DictionaryDto> getPeriodModify() {
		return dictionaryDao.getPeriodModify();
	}
	/****
	 * 获取区间-可查询
	 */
	public List<DictionaryDto> getPeriod() {
		return dictionaryDao.getPeriod();
	}
	
	public IDictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}

	public void setDictionaryDao(IDictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}
	
	public List<DictionaryDto> getQuarter() {
		return this.dictionaryDao.getQuarter();
	}

	public List<DictionaryDto> getOrgNew(Map<String, String> map) {
		return this.dictionaryDao.getOrgNew(map);
	}

	/***
	 * 获取组织名称
	 */
	public String getOrgName(String orgId) {
		return this.dictionaryDao.getOrgName(orgId);
	}

	@Override
	public List<DictionaryDto> getSeriesNameByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return this.dictionaryDao.getSeriesNameByOrgId(orgId);
	}

	/**
	 * 微学堂-讲师信息字典 
	 */
	@Override
	public List<DictionaryDto> getHrLectorType() {
		return dictionaryDao.getHrLectorType();
	}
}
