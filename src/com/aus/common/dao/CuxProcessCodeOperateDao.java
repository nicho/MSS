package com.aus.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aus.common.model.CuxProcessCodeOperate;
import com.aus.common.model.PersonDto;

public interface CuxProcessCodeOperateDao {
     //新增委托流程配置
	int addCuxProcessCodeOperate(CuxProcessCodeOperate operate);
	//修改委托流程配置
	int updateCuxProcessCodeOperate(CuxProcessCodeOperate operate);
	//删除委托流程配置
	int deleteByIds(long [] ids);
	//获取所有的流程配置（自己及下级）
	List<CuxProcessCodeOperate>getAllCuxProcessCodeOperate(CuxProcessCodeOperate operate);
	//查看委托流程配置
	CuxProcessCodeOperate  findCuxProcessCodeOperate(CuxProcessCodeOperate operate);
	//根据personId 查看userId
	List<PersonDto>getUserIdByPersonId(@Param("personId") String personId);
	//我被委托的配置
	List<CuxProcessCodeOperate>getAllBeCuxProcessCodeOperate(CuxProcessCodeOperate operate);
	
}
