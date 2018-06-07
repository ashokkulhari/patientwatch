package com.reporting.model;

import java.util.List;
import java.util.Map;

public class LocationDDModel {

	
	private String district; 
    private String block; 
    private String mtc; 
    private List<String> districts;
    private List<String> blocks;
    private List<String> mtcs;
    private boolean islocationrender;
    
    private Map<String,List<String>> locationData;
}
