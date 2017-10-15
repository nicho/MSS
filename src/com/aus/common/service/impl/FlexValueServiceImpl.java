package com.aus.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.dao.FlexValueDao;
import com.aus.common.model.BaseDictDto;
import com.aus.common.model.CityDto;
import com.aus.common.model.CodeNameDto;
import com.aus.common.model.FlexValueDto;
import com.aus.common.model.ProductItem;
import com.aus.common.model.QuartzProductItemDto;
import com.aus.common.model.QueryCodeNameDto;
import com.aus.common.service.FlexValueService;

@Service("FlexValueService")
public class FlexValueServiceImpl implements FlexValueService {

	@Autowired
	private FlexValueDao iflexvaluedao;

	@Override
	public List<FlexValueDto> getCityNameList(String query) {
		return iflexvaluedao.getCityNameList(query);
	}

	@Override
	public CityDto findCityIdAndProvinceId(String CityName) {
		List<CityDto> CityDtoList=iflexvaluedao.getCityAndProvince(CityName);
		if(CityDtoList!=null && CityDtoList.size()>0)
			return CityDtoList.get(0);
		return null;
	}

	@Override
	public List<FlexValueDto> getFlexValueList(String type) {
		return iflexvaluedao.getFlexValueList(type);
	}

	@Override
	public List<FlexValueDto> getLookupValueList(String type) {
		return iflexvaluedao.getLookupValueList(type);
	}

	@Override
	public List<FlexValueDto> getFlexValueList_ebs(String type, String language) {
		return iflexvaluedao.getFlexValueList_ebs(type, language);
	}

	@Override
	public List<FlexValueDto> getAllProvince() {
		return iflexvaluedao.getAllProvince();
	}

	@Override
	public List<FlexValueDto> getCityByProvince(String province) {
		return iflexvaluedao.getCityByProvince(province);
	}

	@Override
	public List<FlexValueDto> getTrxSubtypeBytrxType(String province) {
		return iflexvaluedao.getTrxSubtypeBytrxType(province);
	}

	@Override
	public List<FlexValueDto> getFlexValueByParent(FlexValueDto flexValueDto) {
			return iflexvaluedao.getFlexValueByParent(flexValueDto);
	}
	
	@Override
	public String getMemberSysOrgId(String orgid) {
		return iflexvaluedao.getMemberSysOrgId(orgid);
	}

	@Override
	public List<FlexValueDto> getProvinceNameList(String query) {
		return iflexvaluedao.getProvinceNameList(query);
	}

	@Override
	public FlexValueDto getProvinceByName(String name) {
		return iflexvaluedao.getProvinceByName(name);
	}

	@Override
	public List<CodeNameDto> getProvincesNew(QueryCodeNameDto dto) {
		return iflexvaluedao.getProvincesNew(dto);
	}

	@Override
	public List<CodeNameDto> getCitysNew(QueryCodeNameDto dto) {
		return iflexvaluedao.getCitysNew(dto);
	}

	@Override
	public List<CodeNameDto> getCountyareaNew(QueryCodeNameDto dto) {
		return iflexvaluedao.getCountyareaNew(dto);
	}

	@Override
	public List<FlexValueDto> getFlexValueListByParent(String type) {
		return iflexvaluedao.getFlexValueListByParent(type);
	}

	@Override
	public List<ProductItem> getProductItem(QuartzProductItemDto queryTrip) {
		return iflexvaluedao.getProductItem(queryTrip);
	}

	@Override
	public List<FlexValueDto> getFlexValueListOrderBy(String string) {
		return iflexvaluedao.getFlexValueListOrderBy(string);
	}

	@Override
	public List<BaseDictDto> getBaseDictValueList(String orgId, String useage_code) {
		return iflexvaluedao.getBaseDictValueList(orgId,useage_code);
		
	}

	@Override
	public List<FlexValueDto> getBaseDicttList(String type,String orgId) {
		return iflexvaluedao.getBaseDicttList(type, orgId);
	}
}
