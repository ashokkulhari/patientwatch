package com.reporting.pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.component.column.Column;
import org.primefaces.component.columngroup.ColumnGroup;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.row.Row;

import com.reporting.model.Header;
import com.reporting.model.MockDb;
import com.reporting.model.SubCol;
import com.reporting.util.FacesContextUtil;
import com.reporting.util.Util;

@ManagedBean(name = "DynamicColumnBean")
@ViewScoped
public class DynamicColumnBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private transient DataTable myDataTable;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@ManagedProperty("#{login}")
	private transient Login loginBean;
	private ArrayList<ArrayList<Object>> datas;
	
	
	@PostConstruct
    public void init() {
			System.out.println(".................DynamicColumn init................");
			this.loginBean = FacesContextUtil.getBean(Login.class);
			String id=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("javax.faces.source");
			loadDataFromDBInit(id);
			myDataTable = new DataTable();
			getDataInit();
			loadDataTableInit();
    }

	public void loadDataFromDBInit(String id) {System.out.println("loadDataFromDBInit :"+id);
		String mn=null;
		if(loginBean.getMonth()!=null && !"".equals(loginBean.getMonth().trim())){
			mn="'"+loginBean.getMonth()+"'";
		}
		String nm=null;
		if(loginBean.getNoOfBed()!=null && loginBean.getNoOfBed()>0){
			nm=""+loginBean.getNoOfBed();
		}
		String qStr=null;
		if(loginBean.getQuater()!=null && !"".equals(loginBean.getQuater().trim())){
			 qStr =Util.getQuaterMap().get(loginBean.getQuater());
		}
		String dStr=null;
		if(loginBean.getDistrict()!=null && !"".equals(loginBean.getDistrict().trim())){
			dStr="'"+loginBean.getDistrict()+"'";
		}
		String bStr=null;
		if(loginBean.getBlock()!=null && !"".equals(loginBean.getBlock().trim())){
			bStr="'"+loginBean.getBlock()+"'";
		}
		String mStr=null;
		if(loginBean.getMtc()!=null && !"".equals(loginBean.getMtc().trim())){
			mStr="'"+loginBean.getMtc()+"'";
		}
		System.out.println("Dynamic : "+ loginBean.getDistrictFilters());
		if(loginBean.getDistrictFilters()!=null && !"".equals(loginBean.getDistrictFilters().trim())){
			dStr=loginBean.getDistrictFilters();
		}
		
		if(id!=null){
			if( id.endsWith(":submit")){
				System.out.println("...Yes this is Dynamic init submit click....................");
				
				
				this.loginBean.setDbDatas(dStr,bStr,mStr,mn,nm,qStr);
			}else{
				System.out.println("....................id......"+id);
			}
		}else{
			System.out.println("....................id......null ");
			this.loginBean.setDbDatas(dStr,bStr,mStr,mn,nm,qStr);
		}
	}

	public void getDataInit() {
		if(this.loginBean.finalQueryStr!=null && !"".equals(this.loginBean.finalQueryStr.trim())){
        	this.datas=loginBean.getDatas();
        }else{
        	this.datas=loginBean.getDatas();
        }
	}

	public void loadDataTableInit() {
		myDataTable.setValue(datas);
        myDataTable.setVar("mydata");
       
        ColumnGroup columnGroup = new ColumnGroup();
        myDataTable.getChildren().add(columnGroup);
        columnGroup.setType("header");
        List<Header> headerList = MockDb.getHeaderList(loginBean.finalList);
        /*Create Column for Header*/        
        Row headerRow = new Row();
        columnGroup.getChildren().add(headerRow);
        for(Header header : headerList) {
            Column column = new Column();
            /*Make sure to set column span*/
            column.setColspan(header.getSubColList().size());
            headerRow.getChildren().add(column);
            column.setHeaderText(header.getName());
            
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        ExpressionFactory ef = application.getExpressionFactory();
        ELContext elc = fc.getELContext();

        /*Create Column for Sub Column of current header*/      
        Row subColRow = new Row();
        columnGroup.getChildren().add(subColRow);
        for(Header header : headerList) {
            for(SubCol subCol : header.getSubColList()) {
                Column column = new Column();
                subColRow.getChildren().add(column);column.setStyle("background: lightgrey ;");
                column.setHeaderText(subCol.getName());
                column.setSortable(true);
                column.setSortBy(subCol.getData());
//                ValueExpression valueExpression=null;
//                if(subCol.getData().equals("districts") || subCol.getData().equals("mtc_name")){
//                	valueExpression = ef.createValueExpression( elc, "#{dynamicRow['"+subCol.getData()+"']}", String.class);
//                    
//                }else{
//                	valueExpression = ef.createValueExpression( elc, "#{dynamicRow['"+subCol.getData()+"']}", Integer.class);
//                    
//                }
//                 column.setValueExpression("sortBy", valueExpression);
//                column.setValueExpression("filterBy", valueExpression);
            }
        }
        
        /*Create data row for 15 columns*/      
//        for(int i=1; i <= 15; i++) {
//            ValueExpression valueExp = ef.createValueExpression(elc, "#{mydata.data" + i + "}", Object.class);
//            HtmlOutputText output = (HtmlOutputText)application.createComponent(HtmlOutputText.COMPONENT_TYPE );
//            System.out.println(valueExp.getExpectedType()+"..................."+valueExp.toString());
//            output.setValueExpression("value", valueExp);
//            Column dataColumn = new Column();
//            dataColumn.getChildren().add(output);
//            myDataTable.getChildren().add(dataColumn);
//        }
        ValueExpression valueExp = ef.createValueExpression(elc, "#{DynamicColumnBean.datas}", List.class);
        myDataTable.setValueExpression("value", valueExp);
        myDataTable.setVar("dynamicRow");
        System.out.println("QU DATA size "+datas.size());
        if(datas!=null && datas.size()>0  && datas.get(0)!=null){
        	for (int i = 0; i < datas.get(0).size(); i++) {
            	HtmlOutputText output = new HtmlOutputText();
//            	if(i==0){
//            		valueExp = ef.createValueExpression(elc, "#{dynamicRow[" + i + "]}", Object.class);
//            	}else{
            		valueExp = ef.createValueExpression(elc, "#{dynamicRow[" + i + "]}", Object.class);
//            	}
                output.setValueExpression("value", valueExp);
               
              Column dataColumn = new Column();
              dataColumn.getChildren().add(output);
              
              
              myDataTable.getChildren().add(dataColumn);
            }
        }
        
        
        
//        HtmlOutputText output = (HtmlOutputText)application.createComponent(HtmlOutputText.COMPONENT_TYPE );
//        System.out.println(valueExp.getExpectedType()+"..................."+valueExp.toString());
//        output.setValueExpression("value", valueExp);
//        Column dataColumn = new Column();
//        dataColumn.getChildren().add(output);
//        myDataTable.getChildren().add(dataColumn);
	}
	public void onSubmit(){
		System.out.println("Dynamic onSubmit");
	}
	
	public void postProcessXLS(Object document) {
		XSSFWorkbook wb = (XSSFWorkbook) document;
		Sheet  sheet = wb.getSheetAt(0);
		
		PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        CellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
         
        for(int i=0; i < sheet.getPhysicalNumberOfRows();i++) {
        	org.apache.poi.ss.usermodel.Row header = sheet.getRow(i);
        	if(header!=null){System.out.println();
        		Cell cell = header.getCell(i);
        		if(cell!=null){
        			System.out.println("cell----------- "+cell.getCellType() +"  "+cell);
                    cell.setCellStyle(cellStyle);
        		}
                
        	}else{
        		System.out.println("header null...");
        	}
            
        }
    }
	public Login getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(Login loginBean) {System.out.println("........loginbean..dynamic......");
		this.loginBean = loginBean;
	}
	public ArrayList<ArrayList<Object>> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<ArrayList<Object>> datas) {
		this.datas = datas;
	}
	public DataTable getMyDataTable() {
        return myDataTable;
    }

    public void setMyDataTable(DataTable myDataTable) {
        this.myDataTable = myDataTable;
    }
    


}