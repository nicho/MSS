package com.aus.common.model;

public class OperateLogLineDto {
/**
* 条目ID
* */	
private long lineId;
/**
* 操作ID
* */	
private long operateId;
/**
 * 变量名
 * */
private String variableName;
/**
 * 原始记录
 * */
private String oriValue;
/**
 * 更新后的记录
 * */
private String updatedValue;
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


public long getLineId() {
	return lineId;
}
public void setLineId(long lineId) {
	this.lineId = lineId;
}

public long getOperateId() {
	return operateId;
}
public void setOperateId(long operateId) {
	this.operateId = operateId;
}
public String getVariableName() {
	return variableName;
}
public void setVariableName(String variableName) {
	this.variableName = variableName;
}
public String getOriValue() {
	return oriValue;
}
public void setOriValue(String oriValue) {
	this.oriValue = oriValue;
}
public String getUpdatedValue() {
	return updatedValue;
}
public void setUpdatedValue(String updatedValue) {
	this.updatedValue = updatedValue;
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

}
