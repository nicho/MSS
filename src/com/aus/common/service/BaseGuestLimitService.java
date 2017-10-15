package com.aus.common.service;

import java.util.HashMap;
import java.util.List;

import com.aus.common.model.BaseGuestLimit;
import com.aus.common.model.BaseGuestLimitObj;

public interface BaseGuestLimitService {
/**
 * 新增权限配置主表记录
 * **/	
public int  addBaseGuestLimit(BaseGuestLimit baseGuestLimit);
/**
 * 新增权限配置子表记录
 * **/	
public int  addBaseGuestLimitObj(BaseGuestLimitObj baseGuestLimitObj);

/**
 * 软删除主表记录
 * **/	
public int deleteBaseGuestLimitById(BaseGuestLimit baseGuestLimit);

/**
 * 软删除子表记录
 * **/	
public int deleteBaseGuestLimitObjById(BaseGuestLimit baseGuestLimit);

/**
 * 获取主表记录
 * **/	
public List<BaseGuestLimit> getBaseGuestLimitByUserId(BaseGuestLimit baseGuestLimit);


/**
 * 获取子表记录（部门）
 * **/	
public List<BaseGuestLimitObj> getBaseGuestLimitObjByLimitId(BaseGuestLimitObj baseGuestLimitObj);

/**
 * 获取子表记录（职务）
 * **/	
public List<BaseGuestLimitObj> getBaseGuestLimitJobObjByLimitId(BaseGuestLimitObj baseGuestLimitObj);
/**
 * 新增主表、子表
 * */
public String addBaseGuestLimitList(BaseGuestLimit baseGuestLimit);

/**
 * 获取部门列表
 * */
public List<BaseGuestLimit> getDeparmentList(HashMap<String,Object> map);



}
