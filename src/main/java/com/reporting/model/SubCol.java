package com.reporting.model;

public class SubCol {
    private String name;
    private String data;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public SubCol() {
    }

    public SubCol(String name, String data) {
        this.name = name;
        this.data = data;
    }   
    //getter & setter
}
