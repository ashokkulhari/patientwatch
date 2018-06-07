package com.reporting.pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;


import com.reporting.model.HeaderDataValues;
import com.reporting.model.MockDb;
import com.reporting.model.SubCol;
import com.reporting.util.FacesContextUtil;
 
@ManagedBean(name = "finalCheckBoxView")
@ViewScoped
public class FinalCheckBoxView  implements Serializable{
         
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{login}")
	private transient Login loginBean;

	List<SelectItem> items;
    String selectedItem;
    List<String> selectedItems;
    
    List<SelectItem> items2;
    String selectedItem2;
    List<String> selectedItems2;
    
    List<SelectItem> items3;
    String selectedItem3;
    List<String> selectedItems3;    
    List<SelectItem> items4;
    String selectedItem4;
    List<String> selectedItems4;
    List<SelectItem> items5;
    String selectedItem5;
    List<String> selectedItems5;
    List<SelectItem> items6;
    String selectedItem6;
    List<String> selectedItems6;
    List<SelectItem> items7;
    String selectedItem7;
    List<String> selectedItems7;
    List<SelectItem> items8;
    String selectedItem8;
    List<String> selectedItems8;
    List<SelectItem> items9;
    String selectedItem9;
    List<String> selectedItems9;
    List<SelectItem> items10;
    String selectedItem10;
    List<String> selectedItems10;
    
    private boolean b1;
    private boolean b2;
    private boolean b3;
    private boolean b4;
    private boolean b5;
    private boolean b6;
    private boolean b7;
    private boolean b8;
    private boolean b9;
    private boolean b10;
    
	
	@PostConstruct
    public void init() {
		System.out.println("....................final.checkboxview ......init....................");
		this.loginBean = FacesContextUtil.getBean(Login.class);
		 loginBean.finalList=null;
		 loginBean.finalQueryStr=null;
		loadCheckBoxs();
    }

	public void onBol1(){System.out.println("---onBol1--"+b1);
	if(b1==false){
		this.selectedItems=null;
	} if(b2==false){
		this.selectedItems2=null;
	} if(b3==false){
		this.selectedItems3=null;
	} if(b4==false){
		this.selectedItems4=null;
	} if(b5==false){
		this.selectedItems5=null;
	} if(b6==false){
		this.selectedItems6=null;
	} if(b7==false){
		this.selectedItems7=null;
	} if(b8==false){
		this.selectedItems8=null;
	} if(b9==false){
		this.selectedItems9=null;
	} if(b10==false){
		this.selectedItems10=null;
	}
	
	onSubmit();
	}
	public void loadCheckBoxs() {
		Map<String, HeaderDataValues> headerSubColNameMapping =MockDb.getColumnsMap();
		Map<String, ArrayList<SubCol>> values = MockDb.getHeaderSubColMap(headerSubColNameMapping);
		items = new ArrayList();
		items2 = new ArrayList();
		items3 = new ArrayList();
		items4 = new ArrayList();
		items5 = new ArrayList();
		items6 = new ArrayList();
		items7 = new ArrayList();
		items8 = new ArrayList();
		items9 = new ArrayList();
		items10 = new ArrayList();
        int k =1;
        SelectItemGroup g1;
        ArrayList<SelectItem> selectItem;
		for (Iterator iterator = values.keySet().iterator(); iterator.hasNext();) {
			String keyHeaderName = (String) iterator.next();
			ArrayList<SubCol> subCollist=values.get(keyHeaderName);
			 g1 = new SelectItemGroup(keyHeaderName);
			selectItem=new ArrayList<>();
			for (int i = 0; i < subCollist.size(); i++) {
				SubCol subCol =subCollist.get(i);
				selectItem.add(new SelectItem(subCol.getData(),subCol.getName()));
			}
			g1.setSelectItems(selectItem.toArray(new SelectItem[selectItem.size()]));
			
			if(k==1){
				items.add(g1);
			}
			if(k==2){
			items2.add(g1);
			}
			if(k==3){
			items3.add(g1);
			}
			if(k==4){
				items4.add(g1);
				}
			if(k==5){
				items5.add(g1);
				}
			if(k==6){
				items6.add(g1);
				}
			if(k==7){
				items7.add(g1);
				}
			if(k==8){
				items8.add(g1);
				}
			if(k==9){
				items9.add(g1);
				}
			if(k==10){
				items10.add(g1);
				}
			k=k+1;
		}
	}

	public void onSubmit(){
		System.out.println(" ..final....checkbox...........on submit..................");
		System.out.println("selectedItems = "+selectedItems +" ,selectedItems2 =" +selectedItems2+" ,selectedItems10 =" +selectedItems10);
		ArrayList<String> finalColList =new ArrayList<>();
		buildList(finalColList,selectedItems);
		buildList(finalColList,selectedItems2);
		buildList(finalColList,selectedItems3);
		buildList(finalColList,selectedItems4);
		buildList(finalColList,selectedItems5);
		buildList(finalColList,selectedItems6);
		buildList(finalColList,selectedItems7);
		buildList(finalColList,selectedItems8);
		buildList(finalColList,selectedItems9);
		buildList(finalColList,selectedItems10);
		StringBuilder queryStr= new StringBuilder();
		for (int i = 0; i < finalColList.size(); i++) {
			if(i==0){
				queryStr.append(finalColList.get(i));
			}else{
				queryStr.append(","+finalColList.get(i));
			}
		}
		 this.loginBean = FacesContextUtil.getBean(Login.class);
		 loginBean.finalList=finalColList;
		 loginBean.finalQueryStr=queryStr.toString();
		System.out.println("finalQueryStr = "+loginBean.finalQueryStr + "  checkbox submit : "+loginBean.finalList);
	}

	public void buildList(ArrayList<String> finalColList,List<String> sitems) {
		if(sitems!=null && sitems.size()>0){
			for (int i = 0; i < sitems.size(); i++) {
				finalColList.add(sitems.get(i));
			}
		}
	}
		
	public List<SelectItem> getItems4() {
		return items4;
	}

	public void setItems4(List<SelectItem> items4) {
		this.items4 = items4;
	}

	public String getSelectedItem4() {
		return selectedItem4;
	}

	public void setSelectedItem4(String selectedItem4) {
		this.selectedItem4 = selectedItem4;
	}

	public List<String> getSelectedItems4() {
		return selectedItems4;
	}

	public void setSelectedItems4(List<String> selectedItems4) {
		this.selectedItems4 = selectedItems4;
	}

	public List<SelectItem> getItems5() {
		return items5;
	}

	public void setItems5(List<SelectItem> items5) {
		this.items5 = items5;
	}

	public String getSelectedItem5() {
		return selectedItem5;
	}

	public void setSelectedItem5(String selectedItem5) {
		this.selectedItem5 = selectedItem5;
	}

	public List<String> getSelectedItems5() {
		return selectedItems5;
	}

	public void setSelectedItems5(List<String> selectedItems5) {
		this.selectedItems5 = selectedItems5;
	}

	public List<SelectItem> getItems6() {
		return items6;
	}

	public void setItems6(List<SelectItem> items6) {
		this.items6 = items6;
	}

	public String getSelectedItem6() {
		return selectedItem6;
	}

	public void setSelectedItem6(String selectedItem6) {
		this.selectedItem6 = selectedItem6;
	}

	public List<String> getSelectedItems6() {
		return selectedItems6;
	}

	public void setSelectedItems6(List<String> selectedItems6) {
		this.selectedItems6 = selectedItems6;
	}

	public List<SelectItem> getItems7() {
		return items7;
	}

	public void setItems7(List<SelectItem> items7) {
		this.items7 = items7;
	}

	public String getSelectedItem7() {
		return selectedItem7;
	}

	public void setSelectedItem7(String selectedItem7) {
		this.selectedItem7 = selectedItem7;
	}

	public List<String> getSelectedItems7() {
		return selectedItems7;
	}

	public void setSelectedItems7(List<String> selectedItems7) {
		this.selectedItems7 = selectedItems7;
	}

	public List<SelectItem> getItems8() {
		return items8;
	}

	public void setItems8(List<SelectItem> items8) {
		this.items8 = items8;
	}

	public String getSelectedItem8() {
		return selectedItem8;
	}

	public void setSelectedItem8(String selectedItem8) {
		this.selectedItem8 = selectedItem8;
	}

	public List<String> getSelectedItems8() {
		return selectedItems8;
	}

	public void setSelectedItems8(List<String> selectedItems8) {
		this.selectedItems8 = selectedItems8;
	}

	public List<SelectItem> getItems9() {
		return items9;
	}

	public void setItems9(List<SelectItem> items9) {
		this.items9 = items9;
	}

	public String getSelectedItem9() {
		return selectedItem9;
	}

	public void setSelectedItem9(String selectedItem9) {
		this.selectedItem9 = selectedItem9;
	}

	public List<String> getSelectedItems9() {
		return selectedItems9;
	}

	public void setSelectedItems9(List<String> selectedItems9) {
		this.selectedItems9 = selectedItems9;
	}

	public List<SelectItem> getItems10() {
		return items10;
	}

	public void setItems10(List<SelectItem> items10) {
		this.items10 = items10;
	}

	public String getSelectedItem10() {
		return selectedItem10;
	}

	public void setSelectedItem10(String selectedItem10) {
		this.selectedItem10 = selectedItem10;
	}

	

	public List<SelectItem> getItems3() {
		return items3;
	}

	public void setItems3(List<SelectItem> items3) {
		this.items3 = items3;
	}

	public String getSelectedItem3() {
		return selectedItem3;
	}

	public void setSelectedItem3(String selectedItem3) {
		this.selectedItem3 = selectedItem3;
	}

	public List<String> getSelectedItems3() {
		return selectedItems3;
	}

	public void setSelectedItems3(List<String> selectedItems3) {
		this.selectedItems3 = selectedItems3;
	}

	public String getSelectedItem2() {
		return selectedItem2;
	}

	public void setSelectedItem2(String selectedItem2) {
		this.selectedItem2 = selectedItem2;
	}

	public List<String> getSelectedItems2() {
		return selectedItems2;
	}

	public void setSelectedItems2(List<String> selectedItems2) {
		this.selectedItems2 = selectedItems2;
	}

	public List<SelectItem> getItems2() {
		return items2;
	}

	public void setItems2(List<SelectItem> items2) {
		this.items2 = items2;
	}

    public List<SelectItem> getItems() {
		return items;
	}

	public void setItems(List<SelectItem> items) {
		this.items = items;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<String> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<String> selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	public List<String> getSelectedItems10() {
		return selectedItems10;
	}

	public void setSelectedItems10(List<String> selectedItems10) {
		this.selectedItems10 = selectedItems10;
	}
	
	public Login getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(Login loginBean) {
		System.out.println("...setting login bean...");
		this.loginBean = loginBean;
	}
	public boolean isB1() {
		return b1;
	}

	public void setB1(boolean b1) {
		this.b1 = b1;
	}

	public boolean isB2() {
		return b2;
	}

	public void setB2(boolean b2) {
		this.b2 = b2;
	}

	public boolean isB3() {
		return b3;
	}

	public void setB3(boolean b3) {
		this.b3 = b3;
	}

	public boolean isB4() {
		return b4;
	}

	public void setB4(boolean b4) {
		this.b4 = b4;
	}

	public boolean isB5() {
		return b5;
	}

	public void setB5(boolean b5) {
		this.b5 = b5;
	}

	public boolean isB6() {
		return b6;
	}

	public void setB6(boolean b6) {
		this.b6 = b6;
	}

	public boolean isB7() {
		return b7;
	}

	public void setB7(boolean b7) {
		this.b7 = b7;
	}

	public boolean isB8() {
		return b8;
	}

	public void setB8(boolean b8) {
		this.b8 = b8;
	}

	public boolean isB9() {
		return b9;
	}

	public void setB9(boolean b9) {
		this.b9 = b9;
	}

	public boolean isB10() {
		return b10;
	}

	public void setB10(boolean b10) {
		this.b10 = b10;
	}

}
