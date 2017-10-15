package com.aus.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aus.common.model.BaseDictDto;
import com.aus.common.model.CityDto;
import com.aus.common.model.CodeNameDto;
import com.aus.common.model.FlexValueDto;
import com.aus.common.model.ProductItem;
import com.aus.common.model.QuartzProductItemDto;
import com.aus.common.model.QueryCodeNameDto;

public interface FlexValueDao {

	List<FlexValueDto> getCityNameList(String query);
	List<FlexValueDto> getProvinceNameList(String query);
	List<FlexValueDto> getFlexValueList(String type);
	List<FlexValueDto> getLookupValueList(String type);
	List<CityDto> getCityAndProvince(String CityName);
	List<FlexValueDto> getFlexValueListByParent(@Param("type") String type);
	List<FlexValueDto> getFlexValueList_ebs(@Param("type") String type,@Param("language") String language);
	List<FlexValueDto> getAllProvince();
	List<FlexValueDto> getCityByProvince(@Param("province") String province);
	List<FlexValueDto> getTrxSubtypeBytrxType(@Param("province") String province);
	String getMemberSysOrgId(String orgId);
	List<FlexValueDto> getFlexValueByParent(FlexValueDto flexValueDto);
	FlexValueDto getProvinceByName(String name);
	
	//新版省市区
	public List<CodeNameDto> getProvincesNew(QueryCodeNameDto dto);
	public List<CodeNameDto> getCitysNew(QueryCodeNameDto dto);
	public List<CodeNameDto> getCountyareaNew(QueryCodeNameDto dto);
	
	// 产品品规
	List<ProductItem> getProductItem(QuartzProductItemDto queryTrip);
	List<FlexValueDto> getFlexValueListOrderBy(String type);
	List<BaseDictDto> getBaseDictValueList(@Param("orgId") String orgId, @Param("useage_code") String useage_code);
	List<FlexValueDto> getBaseDicttList(@Param("type") String type,@Param("orgId") String orgId);

}
