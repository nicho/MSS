package com.aus.common.model;

public class QuartzProductItemDto {
	String url;
	String cuxItemSeries;
	String cuxItemLevel;
	String orgId;
	String itemCode;
	String description;
	String channel_type;
 
	public String getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCuxItemSeries() {
		return cuxItemSeries;
	}

	public void setCuxItemSeries(String cuxItemSeries) {
		this.cuxItemSeries = cuxItemSeries;
	}

	public String getCuxItemLevel() {
		return cuxItemLevel;
	}

	public void setCuxItemLevel(String cuxItemLevel) {
		this.cuxItemLevel = cuxItemLevel;
	}

}
