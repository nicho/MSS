package com.aus.common.service;

import java.util.List;

import com.aus.common.model.FlexValueDto;
import com.aus.common.model.ProcessCodeClass;
import com.aus.common.model.ProcessCodeSubClass;

public interface ProcessCodeSubClassService {
	/**
	* 获取所有流程类别配置的数据
	* */
	public List<ProcessCodeSubClass>getAllProcessCodeSubClassList(ProcessCodeSubClass subClass);
	
	/**
	* 获取所有流程类别配置的数据(不包括父类节点)
	* */
	public List<ProcessCodeSubClass>getAllProcessCodeSubClassListNoPar(ProcessCodeSubClass subClass);
	/**
	 * 新增流程类型配置的数据
	 * */
	public String addProcessCodeSubClass(ProcessCodeSubClass subClass);
	/**
	 * 修改流程类型配置的数据
	 * */
	public String  updateProcessCodeSubClass(ProcessCodeSubClass subClass);
	/**
	 * 保存流程类型配置的数据
	 * */
	public String saveProcessCodeSubClass(String[] process_subclass_id_l,
			String[] process_subclass_code_l, String[] status,
			String[] process_class_code_l, String deleteIds, String userId,
			String orgId);
	/**
	 * 删除流程类型配置的数据
	 * */
	public String  deleteByIds(String[]ids);
	/**
	 * 处理流程类型配置的重复数据
	 * */
	public int updateSubClassRepeatData(String orgId);
	/**
	 * 根据流程CODE获取流程的名称
	 * */
	public List<FlexValueDto> getFlowTypeFlexValue(String type,String flex_value);
	
	/**
	 * 获取所有父类节点
	 * */
	public List<ProcessCodeClass> getAllProcessCodeClass();
	
}
