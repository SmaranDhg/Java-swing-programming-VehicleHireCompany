package DataModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;


import Utilities.RawDatas;


public  class DataTableModel extends AbstractTableModel {
	
	private ArrayList<String> datas=null;
   
	private String[] row=null;
	
	public static  <T> DataTableModel getTableModel(Class<T> cls, ArrayList<String> data)
	{
	   return new DataTableModel(cls,data);
	}

	
	
	public  <T> void  setData(Class<T> cls,ArrayList<String> data)
	{
		this.datas=data;
		this.row=RawDatas.c2th.get(cls.toString());
		if(datas.isEmpty())
			datas.add("{\"None\":\"Fun\"}");
	    
	     
		
	}
	
	private   DataTableModel(Class<?> cls,ArrayList<String> data) {
		this.datas=data;
		this.row=RawDatas.c2th.get(cls.toString());
	     
		if(datas.isEmpty())
			datas.add("{\"None\":\"Fun\"}");
		
			
	}
	
    public  DataTableModel() {
	
		
	}
	
    @Override
    public String getColumnName(int column) {
    	
    	return row[column];
    }
	@Override
	public int getColumnCount() {
		return row.length;
	}

	@Override
	public int getRowCount() {
		return datas.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		String[] row1=this.datas.get(arg0).split(";");
		
		if(row1.length>arg1)
		 return row1[arg1];
		else return "None";
	}
}
