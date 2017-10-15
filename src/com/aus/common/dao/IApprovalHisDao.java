package com.aus.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aus.common.model.ApprovalHisDto;
import com.aus.common.model.ApprovalInstDto;

public interface IApprovalHisDao {

	List<ApprovalHisDto> getApprovalHis(ApprovalHisDto approvalHisDto);
	List<ApprovalInstDto> getApprovalInst(ApprovalHisDto approvalHisDto);
	String getFirstApprovalPersonMobilePhone(@Param("docId")String docId,@Param("processCode")String processCode);
	
	
	List<ApprovalHisDto>  getLastApprovalComments(ApprovalHisDto approvalHisDto);
}
