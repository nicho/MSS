package com.aus.common.service;

import java.util.List;

import com.aus.common.model.CuxProcessCodeOperate;
import com.aus.common.model.PersonDto;

public interface CuxProcessCodeOperateService {
	  //新增委托流程配置
		String addCuxProcessCodeOperate(CuxProcessCodeOperate operate);
		//修改委托流程配置
		String updateCuxProcessCodeOperate(CuxProcessCodeOperate operate);
		//删除委托流程配置
		String deleteByIds(long [] ids);
		//获取所有的流程配置（自己及下级）
		List<CuxProcessCodeOperate>getAllCuxProcessCodeOperate(CuxProcessCodeOperate operate);
		//查看委托流程配置
		CuxProcessCodeOperate  findCuxProcessCodeOperate(CuxProcessCodeOperate operate);
		//根据personId 查看userId
		List<PersonDto>getUserIdByPersonId(String personId);
		//获取被委托的配置
		List<CuxProcessCodeOperate>getAllBeCuxProcessCodeOperate(CuxProcessCodeOperate operate);
}
