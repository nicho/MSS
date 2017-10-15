package com.aus.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aus.common.model.FlexValueDto;
import com.aus.common.model.ProcessCodeClass;
import com.aus.common.model.ProcessCodeSubClass;

public interface ProcessCodeSubClassDao {
/**
* 获取所有流程类别配置的数据(包括父类节点)
* */
public List<ProcessCodeSubClass>getAllProcessCodeSubClassList(ProcessCodeSubClass subClass);

/**
* 获取所有流程类别配置的数据(不包括父类节点)
* */
public List<ProcessCodeSubClass>getAllProcessCodeSubClassListNoPar(ProcessCodeSubClass subClass);
/**
 * 新增流程类型配置的数据
 * */
public int addProcessCodeSubClass(ProcessCodeSubClass subClass);
/**
 * 修改流程类型配置的数据
 * */
public int updateProcessCodeSubClass(ProcessCodeSubClass subClass);
/**
 * 删除流程类型配置的数据
 * */
public int deleteByIds(String[]ids);
/**
 * 处理流程类型配置的重复数据
 * */
public int updateSubClassRepeatData(@Param("orgId") String orgId);
/**
 * 根据流程code获取名称
 * */
public List<FlexValueDto> getFlowTypeFlexValue(@Param("type")String type,@Param("flex_value")String flex_value);
/**
 * 获取所有父类节点
 * */
public List<ProcessCodeClass> getAllProcessCodeClass();
}
