package com.reporting.model;

public class HeaderDataValues {

	
	private String headerName;
	private String subColName;
	private String subColDataName;//subcol variable name
	
	public HeaderDataValues(String subColDataName, String subColName ,String headerName ){
		this.headerName=headerName;
		this.subColName=subColName;
		this.subColDataName=subColDataName;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getSubColName() {
		return subColName;
	}
	public void setSubColName(String subColName) {
		this.subColName = subColName;
	}
	public String getSubColDataName() {
		return subColDataName;
	}
	public void setSubColDataName(String subColDataName) {
		this.subColDataName = subColDataName;
	}
}
