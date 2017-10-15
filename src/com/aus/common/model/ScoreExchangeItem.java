package com.aus.common.model;

public class ScoreExchangeItem {
	private String prosku;
	private String proname;
	private String proscore;
	private String inventory_item_id;
	private String primary_uom_code;
	private String primary_uom_name;
	
	public String getPrimary_uom_name() {
		return primary_uom_name;
	}
	public void setPrimary_uom_name(String primary_uom_name) {
		this.primary_uom_name = primary_uom_name;
	}
	public String getInventory_item_id() {
		return inventory_item_id;
	}
	public void setInventory_item_id(String inventory_item_id) {
		this.inventory_item_id = inventory_item_id;
	}
	public String getPrimary_uom_code() {
		return primary_uom_code;
	}
	public void setPrimary_uom_code(String primary_uom_code) {
		this.primary_uom_code = primary_uom_code;
	}
	public String getProsku() {
		return prosku;
	}
	public void setProsku(String prosku) {
		this.prosku = prosku;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getProscore() {
		return proscore;
	}
	public void setProscore(String proscore) {
		this.proscore = proscore;
	} 
	
}
