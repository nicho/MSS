package com.aus.common.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.dao.ICustAccountDao;
import com.aus.common.model.CustAccountDto;
import com.aus.common.model.CustAccountVo;
import com.aus.common.service.CustAccountService;

@Service("CustAccountService")
public class CustAccountServiceImpl implements CustAccountService {
	private static Logger logger = Logger.getLogger(CustAccountServiceImpl.class);
	@Autowired
	private ICustAccountDao custAccountDao;
	
	public List<CustAccountDto> getCustAccountList(CustAccountDto custAccountDto) {
		return custAccountDao.getCustAccountList(custAccountDto);
	}

	public ICustAccountDao getCustAccountDao() {
		return custAccountDao;
	}

	public void setCustAccountDao(ICustAccountDao custAccountDao) {
		this.custAccountDao = custAccountDao;
	}


	public List<CustAccountDto> getCustByPersonId(String personId,String orgId,String departmentCode,String channelType) {
		if( StringUtils.isNotBlank(departmentCode) ) {			
			try {
				departmentCode = URLDecoder.decode(departmentCode,"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}			
		}				
		return custAccountDao.getCustByPersonId(personId,orgId,departmentCode,channelType);
	}
	@Override
	public CustAccountVo getCustAccountAccountNumber(String cust_account_id, String org_id) {
		return custAccountDao.getCustAccountAccountNumber(cust_account_id, org_id);
	}

	@Override
	public List<CustAccountDto> getBcCustByPersonId(String personId,
			String orgId, String departmentCode, String channelType,
			String accountNumber, String accountNameSelect) {
		if( StringUtils.isNotBlank(departmentCode) ) {			
			try {
				departmentCode = URLDecoder.decode(departmentCode,"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}			
		}				
		return custAccountDao.getBcCustByPersonId(personId,orgId,departmentCode,channelType,accountNumber, accountNameSelect);
	}
}
