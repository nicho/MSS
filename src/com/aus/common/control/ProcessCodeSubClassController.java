package com.aus.common.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContext;

import com.aus.authority.annotation.AccreditAnnotation;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.common.model.FlexValueDto;
import com.aus.common.model.ProcessCodeClass;
import com.aus.common.model.ProcessCodeSubClass;
import com.aus.common.service.FlexValueService;
import com.aus.common.service.ProcessCodeSubClassService;
import com.aus.common.util.OAUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ProcessCodeSubClassController {
private static Logger logger = Logger.getLogger(ProcessCodeSubClassController.class);
	
	@Autowired
	private ProcessCodeSubClassService processCodeSubClassService ;
	
	@Autowired
	private FlexValueService flexValueService ;
	
	
	/**
	 * 初始化跳转到流程分类配置管理界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoProcessCodeSubClassList")
	@AccreditAnnotation(url="gotoProcessCodeSubClassList.do", resourceCode = "")
	public String gotoProcessCodeSubClassList(HttpServletRequest request) {
		String orgId = OAUtils.getOrg(request);
		
	    List<FlexValueDto> processSubClassList = flexValueService.getFlexValueList("CUX_PROCESS_TYPE");//流程类型列表
	    request.setAttribute("processSubClassList", processSubClassList);

	    ProcessCodeSubClass processCodeSubClass = new ProcessCodeSubClass();
	    processCodeSubClass.setOrg_id(orgId);
	    //整个组织的流程分类查看
	    List<ProcessCodeClass> processClassList =  processCodeSubClassService.getAllProcessCodeClass();
	    request.setAttribute("processClassList", processClassList);//流程大类
	    
	    
		String processSubClassListJson = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			processSubClassListJson = objectMapper.writeValueAsString(processSubClassList);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		request.setAttribute("processSubClassListJson", processSubClassListJson);
		
		String processClassListJson = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			processClassListJson = objectMapper.writeValueAsString(processClassList);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		request.setAttribute("processClassListJson", processClassListJson);
		
	    
		return "jsp/processCodeClass/processcode_sub_class_list";
	}
	
	/**
	 * 跳转到流程分类配置管理界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProcessCodeSubClassList")
	@AccreditAnnotation(url="gotoProcessCodeSubClassList.do", resourceCode = "")
	public String getProcessCodeSubClassList(ProcessCodeSubClass processCodeSubClass,HttpServletRequest request) {
        String orgId = OAUtils.getOrg(request);
		
	    List<FlexValueDto> processSubClassList = flexValueService.getFlexValueList("CUX_PROCESS_TYPE");//流程类型列表
	    request.setAttribute("processSubClassList", processSubClassList);

	    processCodeSubClass.setOrg_id(orgId);
	    //整个组织的流程分类查看
	    List<ProcessCodeClass> processClassList =  processCodeSubClassService.getAllProcessCodeClass();
	    request.setAttribute("processClassList", processClassList);//流程大类
	    
	    
		String processSubClassListJson = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			processSubClassListJson = objectMapper.writeValueAsString(processSubClassList);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		request.setAttribute("processSubClassListJson", processSubClassListJson);
		
		String processClassListJson = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			processClassListJson = objectMapper.writeValueAsString(processClassList);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		request.setAttribute("processClassListJson", processClassListJson);
		List<ProcessCodeSubClass> subList = processCodeSubClassService.getAllProcessCodeSubClassListNoPar(processCodeSubClass);
		request.setAttribute("subList", subList);
		request.setAttribute("oreasonSize", subList.size());
		return "jsp/processCodeClass/processcode_sub_class_list";
	}
	
	/**
	 * 跳转到流程分类配置管理界面
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/expStockMiscConfigList")
	@AccreditAnnotation(url="gotoStockMiscConfigList.do", resourceCode = "miscConfig:export")
	public String expStockMiscConfigList(StockMiscReason reason,HttpServletRequest request,HttpServletResponse response) {
		String orgId = OAUtils.getOrg(request);
		reason.setOrg_id(orgId);
	   
		List<StockMiscReason> oreasonList = null;
		try {

			oreasonList =  stockMiscConfigService.getAllInvMiscReasonList(reason);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if(oreasonList ==null){
			oreasonList = new ArrayList<StockMiscReason>();
		}
		
		   //导出字段
		String columnPropertys = "department_code,misc_reason_name";
					//导出execl文件
		exportFile(request, response, oreasonList, "MiscConfig", columnPropertys);
		return null ;
	}
	
	 //导出文件
	private String exportFile(HttpServletRequest request, HttpServletResponse response, List<?> list, String excelName,
				String columnPropertys) {
			InputStream is = null;
			OutputStream os = null;
			try {
				response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
				
				response.setContentType("application/vnd.ms-excel");
	            //模板文件
				is = ExpenseAccountController.class.getResourceAsStream("/template/" + excelName + ".xls");
				
				os = response.getOutputStream();
				
				ExcelExecuter.exporterCommon(is, os, list, 1, 0, columnPropertys);
			} catch (final Exception e) {
				logger.error(e.getMessage());
			} finally {
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
			}
			return null;

		}*/
	
	/**
	 * 
	 * 新增修改程分类配置
	 * @param request
	 * @param invSundriesHead
	 * @return
	 */
	@RequestMapping(value = "/saveProcessCodeSubClass")
	@AccreditAnnotation(url="gotoProcessCodeSubClassList.do", resourceCode = "processSubClass:save")
	public String saveProcessCodeSubClass(HttpServletRequest request,ProcessCodeSubClass processCodeSubClass,RedirectAttributes redirectAttributes) {		
		RequestContext requestContext = new RequestContext(request);
		String str="";
		try {			
			ShiroUser shiroUser = OAUtils.getUser();
			
			String orgId = OAUtils.getOrg(request);

            String []process_class_code_l = processCodeSubClass.getProcess_class_code_l();
            String []process_subclass_code_l = processCodeSubClass.getProcess_subclass_code_l();
            String []status = processCodeSubClass.getStatus();
            String []process_subclass_id_l = processCodeSubClass.getProcess_subclass_id_l();
            String deleteIds = request.getParameter("deleteIds");
            str=processCodeSubClassService.saveProcessCodeSubClass(process_subclass_id_l, process_subclass_code_l, status, process_class_code_l, deleteIds, shiroUser.getUserId(), orgId);
            if(StringUtils.isEmpty(str)){
             str = "save_success"; //没有修改、删除、新增数据时 	
            }
 
			redirectAttributes.addFlashAttribute("InfoMessage", requestContext.getMessage(str));	
			
		}catch(Exception e) {
			logger.error(e);
			redirectAttributes.addFlashAttribute("InfoMessage", requestContext.getMessage("save_failure"));
			return "result";
		}finally {
			
		}
			return "redirect:/gotoProcessCodeSubClassList.do";
	}
	
	
	
}
