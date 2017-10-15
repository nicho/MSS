package com.aus.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.dao.OperateLogDao;
import com.aus.common.model.CuxOaLoginLogDto;
import com.aus.common.model.OperateLogDto;
import com.aus.common.model.OperateLogLineDto;
import com.aus.common.service.OperateLogService;
@Service("OperateLogService")
public class OperateLogServiceImpl implements OperateLogService{
	@Autowired
	private OperateLogDao operateLogDao;
	/**
	* 新增操作日志
	* */
	public String addOperateLog(OperateLogDto dto){
		if(operateLogDao.addOperateLog(dto)>0){
			return "save_success";
		}else{
			return "save_failure";
		}
	}
	/**
	 * 新增操作日志条目
	 * */
	public String addOperateLogLine(OperateLogLineDto dto){
		if(operateLogDao.addOperateLogLine(dto)>0){
			return "save_success";
		}else{
			return "save_failure";
		}
	}
	@Override
	public String addLoginLog(CuxOaLoginLogDto dto) {
		// TODO Auto-generated method stub
		if(operateLogDao.addLoginLog(dto)>0){
			return "save_success";
		}else{
			return "save_failure";
		}
	}
}
