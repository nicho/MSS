package com.aus.authority.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 树
 * 
 * @author duzh
 * 
 */
public class TreeDTO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	List<TreeDTO> children;

	String id;

	String state;

	String text;

	String value;
	
	String level;
	
	String parentId;
	
	String url;
	
	String sort;
	
	String nodeType;
	
	String OriginalSource;
	
	String icon;
	
	String nodeCode;
	
	String nodeDesc;
	
	String ischecked;

	public List<TreeDTO> getChildren() {
		if(children==null){
			children = new ArrayList<TreeDTO>();
		}
		
		return children;
	}

	public String getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}

	public void setChildren(List<TreeDTO> children) {
		this.children = children;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getOriginalSource() {
		return OriginalSource;
	}

	public void setOriginalSource(String originalSource) {
		OriginalSource = originalSource;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getIschecked() {
		return ischecked;
	}

	public void setIschecked(String ischecked) {
		this.ischecked = ischecked;
	}

	public String getNodeDesc() {
		return nodeDesc;
	}

	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}
	
	
	
}
