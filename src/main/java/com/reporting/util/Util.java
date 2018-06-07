package com.reporting.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {

	public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
 
      public static String getUserName()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("username").toString();
      }
       
      public static String getUserId()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("userid");
        else
            return null;
      }

      public static List<String> getDaysNo(int size){
    	  List<String> days = new ArrayList<>();
    	  days.add("Day1");days.add("Day2");days.add("Day3");
    	  days.add("Day4");days.add("Day5");days.add("Day6");
    	  days.add("Day7");days.add("Day8");days.add("Day9");
    	  days.add("Day10");days.add("Day11");days.add("Day12");
    	  days.add("Day13");days.add("Day14");days.add("Day15");
    	  days.add("Day16");days.add("Day17");days.add("Day18");
    	  days.add("Day19");days.add("Day20");days.add("Day21");
    	  days.add("Day22");days.add("Day23");days.add("Day24");
    	  days.add("Day25");days.add("Day26");days.add("Day27");
    	  days.add("Day28");
    	  if(size>0){
    		  List<String> daysC = new ArrayList<>();
    		  for (int i = size; i < days.size(); i++) {
    			  daysC.add(days.get(i));
			}
    		  days=daysC;
    	  }
    	  return days;
      }

      public static List<String> getMonthList(){
    	  List<String> months = new ArrayList<>();
    	  months.add("January");months.add("February");months.add("March");months.add("April");
    	  months.add("May");months.add("June");months.add("July");months.add("August");
    	  months.add("September");months.add("October");months.add("November");months.add("December");
    	  return months;
      }
      
      public static List<Integer> getNoOfBeds(){
    	  List<Integer> noOfBeds = new ArrayList<>();
    	  noOfBeds.add(20);noOfBeds.add(10);noOfBeds.add(6);
    	  return noOfBeds;
      }
      public static List<String> getQuaters(){
    	  List<String> quaters = new ArrayList<>();
    	  quaters.add("Quarter1");quaters.add("Quarter2");quaters.add("Quarter3");quaters.add("Quarter4");
    	  return quaters;
      }
      
      public static Map<String,String> getQuaterMap(){
    	  Map<String,String> maps = new HashMap<>();
    	  maps.put("Quarter1","'january','february' ,'march' ");
    	  maps.put("Quarter2","'april','may' ,'june' ");
    	  maps.put("Quarter3","'july','august' ,'september' ");
    	  maps.put("Quarter4","'october','november' ,'december' ");
    	  return maps;
      }

      public static String formFilters(String district, String block, String mtc, String month, String noOfBeds, String quarter,
  			String s) {
  		if(noOfBeds!=null && !"".equals(noOfBeds.trim())){
  			s=" where m.no_beds in ("+noOfBeds+")";
  		}
  		if(month!=null && !"".equals(month.trim())){
  			if(s!=null && !"".equals(s.trim())){
  				s=s+" and lower(f.month) in ("+month.toLowerCase()+")";
  			}else{
  				s =" where lower(f.month) in ("+month.toLowerCase()+")";
  			}
  		}
  		if(quarter!=null && !"".equals(quarter.trim())){
  			if(s!=null && !"".equals(s.trim())){
  				s=s+" and lower(f.month) in ("+quarter+")";
  			}else{
  				s =" where lower(f.month) in ("+quarter+")";
  			}
  		}
  		
  		if(district!=null && !"".equals(district.trim())){
  			if(s!=null && !"".equals(s.trim())){
  				s=s+" and d.district_name  in ("+district.trim()+")";
  			}else{
  				s =" where d.district_name  in ("+district.trim()+")";
  			}
  		}
  		if(block!=null && !"".equals(block.trim())){
  			if(s!=null && !"".equals(s.trim())){
  				s=s+" and b.block_name  in ("+block.trim()+")";
  			}else{
  				s =" where b.block_name  in ("+block.trim()+")";
  			}
  		}
  		if(mtc!=null && !"".equals(mtc.trim())){
  			if(s!=null && !"".equals(s.trim())){
  				s=s+" and m.mtc_name  in ("+mtc.trim()+")";
  			}else{
  				s =" where m.mtc_name  in ("+mtc.trim()+")";
  			}
  		}
  		return s;
  	}

      
      public static List<String> getDischagedStatus(){
    	  List<String> cs = new ArrayList<>();
    	  cs.add("Cured");cs.add("Partially cured");cs.add("Non-Respondent");
    	  cs.add("Early exit (Defaulter)");cs.add("Early exit (LAMA)");cs.add("Up-Referred");
    	  cs.add("Death");
    	  return cs;
      }  
}
