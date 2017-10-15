package com.aus.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.model.CuxProcessCodeOperate;
import com.aus.common.model.PersonDto;
import com.aus.common.service.CuxProcessCodeOperateService;
import com.aus.common.dao.CuxProcessCodeOperateDao;
@Service("CuxProcessCodeOperateService")
public class CuxProcessOperateServiceImpl implements CuxProcessCodeOperateService{
	@Autowired
	private CuxProcessCodeOperateDao cuxProcessCodeOperateDao;
	@Override
	public String addCuxProcessCodeOperate(CuxProcessCodeOperate operate) {
		if(cuxProcessCodeOperateDao.addCuxProcessCodeOperate(operate)==1){
			return "save_success";
		}
		return "save_failure";
	}

	@Override
	public String updateCuxProcessCodeOperate(CuxProcessCodeOperate operate) {
		if(cuxProcessCodeOperateDao.updateCuxProcessCodeOperate(operate)==1){
			return "save_success";
		}
		return "save_failure";
	}

	@Override
	public String deleteByIds(long[] ids) {
		if(cuxProcessCodeOperateDao.deleteByIds(ids)>0){
			return "delete_success";
		}
		return "delete_failure";
	}

	@Override
	public List<CuxProcessCodeOperate> getAllCuxProcessCodeOperate(
			CuxProcessCodeOperate operate) {
		return cuxProcessCodeOperateDao.getAllCuxProcessCodeOperate(operate);
	}

	@Override
	public CuxProcessCodeOperate findCuxProcessCodeOperate(
			CuxProcessCodeOperate operate) {
		// TODO Auto-generated method stub
		return cuxProcessCodeOperateDao.findCuxProcessCodeOperate(operate);
	}

	@Override
	public List<PersonDto> getUserIdByPersonId(String personId) {
		// TODO Auto-generated method stub
		return cuxProcessCodeOperateDao.getUserIdByPersonId(personId);
	}

	@Override
	public List<CuxProcessCodeOperate> getAllBeCuxProcessCodeOperate(
			CuxProcessCodeOperate operate) {
		// TODO Auto-generated method stub
		return cuxProcessCodeOperateDao.getAllBeCuxProcessCodeOperate(operate);
	}

}
