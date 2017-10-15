package com.aus.common.service;

import java.util.List;

import com.aus.common.model.BaseDictDto;
import com.aus.common.model.CityDto;
import com.aus.common.model.CodeNameDto;
import com.aus.common.model.FlexValueDto;
import com.aus.common.model.ProductItem;
import com.aus.common.model.QuartzProductItemDto;
import com.aus.common.model.QueryCodeNameDto;

public interface FlexValueService {
	List<FlexValueDto> getCityNameList(String query);
	List<FlexValueDto> getProvinceNameList(String query);
	
	CityDto findCityIdAndProvinceId(String CityName);
	FlexValueDto getProvinceByName(String name);
	
	List<FlexValueDto> getFlexValueList(String type);
	
	List<FlexValueDto> getBaseDicttList(String type,String orgId);
	
	List<FlexValueDto> getLookupValueList(String type);
	
	List<FlexValueDto> getFlexValueList_ebs(String type,String language);
	
	List<FlexValueDto> getAllProvince();
	
	List<FlexValueDto> getCityByProvince(String province);
	List<FlexValueDto> getTrxSubtypeBytrxType(String province);
	public List<FlexValueDto> getFlexValueListByParent(String type);
	
	String getMemberSysOrgId(String orgid);
	
	public List<FlexValueDto> getFlexValueByParent(FlexValueDto flexValueDto);
	
	//新版省市区
	public List<CodeNameDto> getProvincesNew(QueryCodeNameDto dto);
	public List<CodeNameDto> getCitysNew(QueryCodeNameDto dto);
	public List<CodeNameDto> getCountyareaNew(QueryCodeNameDto dto);
	
	// 产品品规
	List<ProductItem> getProductItem(QuartzProductItemDto queryTrip);

	List<FlexValueDto> getFlexValueListOrderBy(String string);
	List<BaseDictDto> getBaseDictValueList(String orgId, String useage_code);
}
