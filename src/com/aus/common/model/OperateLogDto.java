package com.aus.common.model;

public class OperateLogDto {
/**
* 操作ID
*/	
private long operateId;
/**
 * 操作人
 */
private String operateUserId;
/**
 * 操作信息
 */
private String operateMsg;
/**
 * 操作类型
 */
private String operateType;
/**
 * 被操作对象ID
 */
private String beOperateObjId;
/**
 * 记录类型
 */
private String logType;
/**
 * 创建时间
 */
private String creationDate;

/**
 * 创建时间
 */
private String creationBy;
/**
 * 最后修改人
 */
private String lastUpdateBy;
/**
 * 最后修改时间
 */
private String lastUpdateDate;

public long getOperateId() {
	return operateId;
}
public void setOperateId(long operateId) {
	this.operateId = operateId;
}
public String getOperateUserId() {
	return operateUserId;
}
public void setOperateUserId(String operateUserId) {
	this.operateUserId = operateUserId;
}
public String getOperateMsg() {
	return operateMsg;
}
public void setOperateMsg(String operateMsg) {
	this.operateMsg = operateMsg;
}
public String getOperateType() {
	return operateType;
}
public void setOperateType(String operateType) {
	this.operateType = operateType;
}
public String getBeOperateObjId() {
	return beOperateObjId;
}
public void setBeOperateObjId(String beOperateObjId) {
	this.beOperateObjId = beOperateObjId;
}
public String getLogType() {
	return logType;
}
public void setLogType(String logType) {
	this.logType = logType;
}
public String getCreationDate() {
	return creationDate;
}
public void setCreationDate(String creationDate) {
	this.creationDate = creationDate;
}
public String getCreationBy() {
	return creationBy;
}
public void setCreationBy(String creationBy) {
	this.creationBy = creationBy;
}
public String getLastUpdateBy() {
	return lastUpdateBy;
}
public void setLastUpdateBy(String lastUpdateBy) {
	this.lastUpdateBy = lastUpdateBy;
}
public String getLastUpdateDate() {
	return lastUpdateDate;
}
public void setLastUpdateDate(String lastUpdateDate) {
	this.lastUpdateDate = lastUpdateDate;
}


}
