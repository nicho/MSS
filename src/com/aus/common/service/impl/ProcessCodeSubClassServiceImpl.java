package com.aus.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.dao.ProcessCodeSubClassDao;
import com.aus.common.model.FlexValueDto;
import com.aus.common.model.ProcessCodeClass;
import com.aus.common.model.ProcessCodeSubClass;
import com.aus.common.service.ProcessCodeSubClassService;
@Service("processCodeSubClassService")
public class ProcessCodeSubClassServiceImpl implements ProcessCodeSubClassService {

	@Autowired
	ProcessCodeSubClassDao processCodeSubClassdao;
	
	@Override
	public List<ProcessCodeSubClass> getAllProcessCodeSubClassList(
			ProcessCodeSubClass subClass) {
		// TODO Auto-generated method stub
		return processCodeSubClassdao.getAllProcessCodeSubClassList(subClass);
	}

	@Override
	public List<ProcessCodeSubClass> getAllProcessCodeSubClassListNoPar(
			ProcessCodeSubClass subClass) {
		// TODO Auto-generated method stub
		return processCodeSubClassdao.getAllProcessCodeSubClassListNoPar(subClass);
	}

	@Override
	public String addProcessCodeSubClass(ProcessCodeSubClass subClass) {
		// TODO Auto-generated method stub
		if(processCodeSubClassdao.addProcessCodeSubClass(subClass)>0){
			return "save_sucess";
		}else{
			return "save_failure";
		}
	}

	@Override
	public String updateProcessCodeSubClass(ProcessCodeSubClass subClass) {
		
		if(processCodeSubClassdao.updateProcessCodeSubClass(subClass)>0){
			return "update_sucess";
		}else{
			return "update_failure";
		}
	}

	@Override
	public String saveProcessCodeSubClass(String[] process_subclass_id_l,
			String[] process_subclass_code_l, String[] status,
			String[] process_class_code_l, String deleteIds, String userId,
			String orgId) {
		if(process_class_code_l !=null && process_class_code_l.length>0){
			  for( int i=0;i<process_class_code_l.length;i++){
		        	if(status !=null && status.length >0){
		        		
		        		if("add".equals(status[i])){
		        			
		        			ProcessCodeSubClass subClass = new 	ProcessCodeSubClass();
		        			subClass.setProcess_class_code(process_class_code_l[i]);
		        			subClass.setProcess_subclass_code(process_subclass_code_l[i]);
		        			subClass.setCreation_by(userId);
		        			subClass.setLast_update_by(userId);
		        			List<FlexValueDto> flex = processCodeSubClassdao.getFlowTypeFlexValue("CUX_PROCESS_TYPE", process_subclass_code_l[i]);
		        			if(flex !=null && flex.size()>0){
		        			subClass.setProcess_subclass_name(flex.get(0).getFlexValuesDesc());
		        			}
		        			subClass.setOrg_id(orgId);
		        			processCodeSubClassdao.addProcessCodeSubClass(subClass);
		        			
		        		} if("update".equals(status[i])){
		        			ProcessCodeSubClass subClass = new 	ProcessCodeSubClass();
		        			subClass.setProcess_class_code(process_class_code_l[i]);
		        			subClass.setProcess_subclass_code(process_subclass_code_l[i]);
		        			List<FlexValueDto> flex = processCodeSubClassdao.getFlowTypeFlexValue("CUX_PROCESS_TYPE", process_subclass_code_l[i]);
		        			if(flex !=null && flex.size()>0){
		        			subClass.setProcess_subclass_name(flex.get(0).getFlexValuesDesc());
		        			}
		        			subClass.setLast_update_by(userId);
						processCodeSubClassdao
								.updateProcessCodeSubClass(subClass);
					}

				}
			}
			// 获取要删除的日志ID组
			String[] ids = deleteIds.split(",");
			if (ids != null && ids.length > 0) {
				processCodeSubClassdao.deleteByIds(ids);
			}

			processCodeSubClassdao.updateSubClassRepeatData(orgId);

		}
		
		
		
		return "save_success";
	}

	@Override
	public String deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		if(processCodeSubClassdao.deleteByIds(ids)>0){
			return "delete_succrss";
		}else{
			return "delete_failure";
		}
	}

	@Override
	public int updateSubClassRepeatData(String orgId) {
		// TODO Auto-generated method stub
		return processCodeSubClassdao.updateSubClassRepeatData(orgId);
	}

	@Override
	public List<FlexValueDto> getFlowTypeFlexValue(String type,
			String flex_value) {
		// TODO Auto-generated method stub
		return processCodeSubClassdao.getFlowTypeFlexValue(type, flex_value);
	}

	@Override
	public List<ProcessCodeClass> getAllProcessCodeClass() {
		// TODO Auto-generated method stub
		return processCodeSubClassdao.getAllProcessCodeClass();
	}

}
