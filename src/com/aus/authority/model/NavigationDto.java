package com.aus.authority.model;

/**
 * 导航
 * 
 * @author duzh
 *
 */
public class NavigationDto {

	private String naVigationId;
	private String menuNodeId;
	private String menuNodeName;
	private String menuNodeCode;
	private String menuNodeDesc;
	private String parentNodeId;//顶节点的父节点填写0
	private String orderNum;
	private String menuNodeType;//10-菜单节点；20-功能节点
	private String OriginalSource;
	private String url;
	private String icon;
	private String lastUpdateLogin;
	private String lastUpdatedBy;
	private String lastUpdateDate;
	private String createdBy;
	private String creationDate;	
	
	
	public String getNaVigationId() {
		return naVigationId;
	}
	public void setNaVigationId(String naVigationId) {
		this.naVigationId = naVigationId;
	}
	public String getMenuNodeId() {
		return menuNodeId;
	}
	public void setMenuNodeId(String menuNodeId) {
		this.menuNodeId = menuNodeId;
	}
	public String getMenuNodeCode() {
		return menuNodeCode;
	}
	public void setMenuNodeCode(String menuNodeCode) {
		this.menuNodeCode = menuNodeCode;
	}
	public String getMenuNodeDesc() {
		return menuNodeDesc;
	}
	public void setMenuNodeDesc(String menuNodeDesc) {
		this.menuNodeDesc = menuNodeDesc;
	}
	public String getParentNodeId() {
		return parentNodeId;
	}
	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getMenuNodeType() {
		return menuNodeType;
	}
	public void setMenuNodeType(String menuNodeType) {
		this.menuNodeType = menuNodeType;
	}
	public String getOriginalSource() {
		return OriginalSource;
	}
	public void setOriginalSource(String originalSource) {
		OriginalSource = originalSource;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLastUpdateLogin() {
		return lastUpdateLogin;
	}
	public void setLastUpdateLogin(String lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getMenuNodeName() {
		return menuNodeName;
	}
	public void setMenuNodeName(String menuNodeName) {
		this.menuNodeName = menuNodeName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
