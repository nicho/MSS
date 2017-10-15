package com.aus.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aus.common.model.CustAccountDto;
import com.aus.common.model.CustAccountVo;

public interface ICustAccountDao {
	CustAccountVo getCustAccountAccountNumber(@Param("cust_account_id") String cust_account_id, @Param("org_id") String org_id);
	
	List<CustAccountDto> getCustAccountList(CustAccountDto custAccountDto);

	List<CustAccountDto> getCustAccount(CustAccountDto custAccountDto);

	List<CustAccountDto> getCustByPersonId(@Param("personId")String personId, @Param("orgId")String orgId, @Param("departmentCode") String departmentCode,@Param("channelType") String channelType);

	List<CustAccountDto> getBcCustByPersonId(@Param("personId")String personId, @Param("orgId")String orgId, @Param("departmentCode") String departmentCode,@Param("channelType") String channelType,@Param("accountNumber") String accountNumber,@Param("accountNameSelect") String accountNameSelect);
}
