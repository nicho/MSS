package com.aus.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aus.common.model.CustAccountDto;
import com.aus.common.model.CustAccountVo;

public interface CustAccountService {
	List<CustAccountDto> getCustAccountList(CustAccountDto custAccountDto) ;

	List<CustAccountDto> getCustByPersonId(String personId, String orgId, String departmentCode,String channelType);
	
	List<CustAccountDto> getBcCustByPersonId(String personId, String orgId,String departmentCode,String channelType, String accountNumber,String accountNameSelect);
	
	CustAccountVo getCustAccountAccountNumber(String cust_account_id, String org_id);
}
