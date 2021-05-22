package Utilities;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JFrame;

import org.jsefa.Deserializer;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.CsvSerializer;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;
import org.jsefa.csv.config.CsvConfiguration;


import DataModels.CarModel;
import DataModels.CusModel;
import DataModels.DataTableModel;
import DataModels.LDetailModel;
import DataModels.LorryModel;
import DataModels.MbusModel;
import DataModels.StaffModel;
import DataModels.VDetailModel;
import SubWindows.VecDetPanel;

public class RawDatas {
	
	public static Stack<JFrame> windows;
	public static enum LOGIN_ID{CUSTOMER,STAFF};
	public static final String COMPANY_NAME="ROLLING STONE VECHILES";  
	public static LOGIN_ID LOGIN_AS=LOGIN_ID.STAFF;
	public static final int WIN_SIZE=1000;
	public static final String MAIN_MENU_GREETING="Welcome, ";
	public static final String FNAME_CAR="CAR.csv";
	public static final String FNAME_MINIBUS="MINIBUS.csv";
	public static final String FNAME_LORRY="LORRY.csv";
	public static final String FNAME_CUSTOMER="CUSTOMER.csv";
	public static final String FNAME_STAFF="STAFF.csv";
	public static final String FNAME_FLEET="FLEET.csv";
	public static final String FNAME_LOGINDETAIL="LOGINDETAIL.csv";
	public static final String SPEC_CAR[]= {"Doors","Fuel type"};
	public static final String SPEC_LORRY[]= {"Load Cap."};
	public static final String SPEC_MINIBUS[]= {"Passenger"};
	public static final String VECHILE_TYPE[]= {"Car","MiniBus","Lorry"};
	public static final String VECHILE_CODE[]= {"C","M","L"};
	public static final String STATUS[]={"LD","AV"};
	public static final String STATUS_HIRED=RawDatas.STATUS[0];
	public static final String STATUS_AVAILABLE=RawDatas.STATUS[1];
	public static final String EMPTY_DATA="{\"None\":\"Fun\"}";
	public static final String FONT="DejaVu Sans";
	public static final int TITLE_FONT_SIZE=32;
	public static final int TITLE_FONT_STYLE=Font.BOLD; 
	public static final String TITLE_FONT=FONT;
	public static final Font FONT_TITLE=new Font("Liberation Serif", Font.BOLD, 34);
	public static final Font FONT_LABEL=new Font("DialogInput", Font.BOLD, 13);
	
	public static final String SN_LBL_1="Sign In";
	public static final String SN_LBL_2="Username";
	public static final String SN_LBL_3="Password";
	public static final String SN_BTN_1="Login";
	public static final String SN_BTN_2="Cancel";

	
	public static final String MENU_STAFF_1="Vehicles";
	public static final String MENU_STAFF_2="Customers";
	public static final String MENU_STAFF_3="Loan";
	public static final String MENU_STAFF_4="Return";
	public static final String MENU_STAFF_5="Exit";
	
	
	public static final String MENU_CUSTOMER_1="Hire";
	public static final String MENU_CUSTOMER_2="Detail";
	public static final String MENU_CUSTOMER_3="Exit";
	
	
	public static final String FLEET_MANAGE_TTL="Vehicles";
	public static final String FLEET_MANAGE_BTN_1="Detail";
	public static final String FLEET_MANAGE_BTN_2="Delete";
	public static final String FLEET_MANAGE_BTN_3="New";
	public static final String FLEET_MANAGE_BTN_4="Back";
	public static final String FLEET_LBL_1="Vehicle Type";
	public static final String FLEET_LBL_2="Vehicle Status";
	
	
	
	public static final String CUSTOMERS_BTN_1="Detail";
	public static final String CUSTOMERS_BTN_2="Delete";
	public static final String CUSTOMERS_BTN_3="New";
	public static final String CUSTOMERS_BTN_4="Back";
	public static final String CUSTOMERS_TITLE="CORPORATE CUSTOMER";
	
	public static final String HIRE_BTN_1="Loan";
	public static final String HIRE_BTN_2="Cancel";
	public static final String HIRE_LBL_1="Type";
	public static final String HIRE_LBL_2="Cancel";
	public static final String HIRE_LBL_3="Cancel";
	
	public static final String HIRE_TITLE="LOAN VEHICLE";
	
	
	public static final String RETURN_BTN_1="Return";
	public static final String RETURN_BTN_2="Cancel";
	public static final String RETURN_TITLE="RETURN VECHILE";
	
	public static final String CDETAIL_1="Username";
	public static final String CDETAIL_2="Name";
	public static final String CDETAIL_3="Address";
	public static final String CDETAIL_4="Contact";
	public static final String CDETAIL_5="Vehicles Hired";
	public static final String CDETAIL_TTL="Customer Detail Form";
	public static final String CDETAIL_BTN_1="Save";
    public static final String CDETAIL_BTN_2="Back";
	
	public static final String SDETAIL_1="Username";
	public static final String SDETAIL_2="Designe";
	public static final String SDETAIL_3="Name";
	public static final String SDETAIL_4="Address";
	public static final String SDETAIL_5="Contact";
	
	public static final String VDETAIL_1="VID";
	public static final String VDETAIL_2="Make";
	public static final String VDETAIL_3="Model";
	public static final String VDETAIL_4C1="Doors";
	public static final String VDETAIL_4C2="Fuel";
	public static final String VDETAIL_4M1="Max. Seat Cap";
	public static final String VDETAIL_4L1="Max. Load Cap";
	public static final String VDETAIL_5="Top Speed";
	public static final String VDETAIL_6="Daily Hire Rate";
	public static final String VDETAIL_7="Registration No.";
	public static final String VDETAIL_TTL=" Detail";
	public static final String VDETAIL_BTN_1="Back";
	
	public static final String VFORM_TTL="Enter Vehicle Detail";
	public static final String VFORM_BTN_1="Add Vehicle";
	public static final String VFORM_BTN_2="Cancel";
	
	
	public static final String LOGIN_DETAIL_1="Username";
	public static final String LOGIN_DETAIL_2="Password";

	

	public static final String H_CAR[]={"vechile_id", "make", "model", "no_of_doors", "fule_type", "top_speed", "daily_hire_rate", "registration_no"} ;
	public static final String H_CUSTOMER[]={"_id", "_password", "_name", "_address", "_contact", "vechiles_on_hire"} ;
	public static final String H_LOGINDETAIL[]={"username", "password", "login_id"} ;
	public static final String H_LORRY[]={"vechile_id", "make", "model", "max_load_capacity", "top_speed", "daily_hire_rate", "registration_no"} ;
	public static final String H_MINIBUS[]={"vechile_id", "make", "model", "max_seating_capacity", "top_speed", "daily_hire_rate", "registration_no"} ;
	public static final String H_STAFF[]={"_id", "_password", "_title", "_name", "_address", "_contact"} ;
	public static final String H_VEHICLE_DETAIL[]={"vechile_id", "type", "status", "customer_id"} ;
	
	public static Map<String,String> c2p;
	public static Map<String,String> c2vc;
	public static Map<String,String> c2vt;
	public static Map<String,String[]> c2th;
	
public static void set_rawdatas()
{
windows=new Stack<JFrame>();	

set_c2p();
set_c2vc();
set_c2vt();
set_c2ht();


    
}


//mapping class to vehicle code
private static void set_c2vc()
{
	c2vc=new HashMap<>();
	 c2vc.put(CarModel.class.toString(), RawDatas.VECHILE_CODE[0]);
    c2vc.put(MbusModel.class.toString(), RawDatas.VECHILE_CODE[1]);
	 c2vc.put(LorryModel.class.toString(), RawDatas.VECHILE_CODE[2]);
	
}

//mapping class to vehicle type
private static void set_c2vt()
{
	 c2vt=new HashMap<>();
	 c2vt.put(CarModel.class.toString(), RawDatas.VECHILE_TYPE[0]);
     c2vt.put(MbusModel.class.toString(), RawDatas.VECHILE_TYPE[1]);
	 c2vt.put(LorryModel.class.toString(), RawDatas.VECHILE_TYPE[2]);
}

//mapping class to path of files
private static void set_c2p()
{
	c2p=new HashMap<>();
	 c2p.put(CarModel.class.toString(), RawDatas.FNAME_CAR);
	 c2p.put(CusModel.class.toString(), RawDatas.FNAME_CUSTOMER);
	 c2p.put(VDetailModel.class.toString(), RawDatas.FNAME_FLEET);
	 c2p.put(LDetailModel.class.toString(), RawDatas.FNAME_LOGINDETAIL);
	 c2p.put(LorryModel.class.toString(), RawDatas.FNAME_LORRY);
	 c2p.put(MbusModel.class.toString(), RawDatas.FNAME_MINIBUS);
	 c2p.put(StaffModel.class.toString(), RawDatas.FNAME_STAFF);
}


//mapping class to table headers
private static void set_c2ht()
{
	c2th=new HashMap<>();
	 c2th.put(CarModel.class.toString(), RawDatas.H_CAR);
	 c2th.put(CusModel.class.toString(), RawDatas.H_CUSTOMER);
	 c2th.put(VDetailModel.class.toString(), RawDatas.H_VEHICLE_DETAIL);
	 c2th.put(LDetailModel.class.toString(), RawDatas.H_LOGINDETAIL);
	 c2th.put(LorryModel.class.toString(), RawDatas.H_LORRY);
	 c2th.put(MbusModel.class.toString(), RawDatas.H_MINIBUS);
	 c2th.put(StaffModel.class.toString(), RawDatas.H_STAFF);
	
}



public static <T> String gtVId(Class<T> type,String... props)
{
  
	String ret=c2vc.get(type.toString());

	for(String prop:props)
		if(prop.length()>=2)
		ret+=prop.charAt(0)+prop.charAt(1);
	
	
	
	SecureRandom rn = new SecureRandom();
    byte[] randomBytes = new byte[67];
    rn.nextBytes(randomBytes);
 
    for ( int i=0;i<4;i++) 
       ret+=rn.nextInt(9);

	return ret;
}

public static String genProp(String...  props)
{
	String ret="";
	for(String prop:props)
		ret+=prop.toUpperCase().charAt(0);
	return ret;
	
}

public static JFrame getJFrame()
{
	JFrame jf=new JFrame();
	jf.pack();
	jf.setLocationRelativeTo(null);
    jf.setVisible(true);
    return jf;
}

public static <T> T deserilizeTo(Class<T> cls,String data)
{
	
	Deserializer deserializer = CsvIOFactory.createFactory(cls).createDeserializer();
	
	T p=null;
	deserializer.open(new StringReader(data));
	while (deserializer.hasNext()) 
	     p = deserializer.next();
	    
	deserializer.close(true);
	
	return p;


}
public static <T> String serializeFrom(Class<T> cls,T f)
{
	
	
	CsvSerializer serialization= CsvIOFactory.createFactory(cls).createSerializer();
    StringWriter sWriter=new StringWriter();
	serialization.open(sWriter);
	serialization.write(f);
	serialization.close(true);
	
	return sWriter.toString();


}

@CsvDataType()
static  class Ram
{

	@CsvField(pos=1)
	String name;
	
	@CsvField(pos=2)
	String id;
	
	public Ram(String nm,String id)
	{
		this.name=nm;
		this.id=id;
	}
	public Ram()
	{
		
	}
	
}


//

public static void main(String[] args) throws IOException {

}

synchronized public static boolean print(Object... lT) {

    for (Object a : lT) {

        System.out.print(a);
        System.out.print(" \n");
    }
    System.out.println();
    return true;
}

//if(vType==MetaDatas.VECHILE_TYPE[0]) //car
//{
//   
//}else if(vType==MetaDatas.VECHILE_TYPE[1])//minibus
//{
//	
//}	
//else {//lorry
//
// }


//tfields[2].setColumns(10);
//tfields[2].setBounds(186, 23+150+20, 172, 37);
//tfields[2].addActionListener(new ActionListener() {
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		
//		if(vType==MetaDatas.VECHILE_TYPE[0]) //car
//		{
//		
//		  int  count=Integer.valueOf(tfields[1].getText());
//			
//			   
//		}else if(vType==MetaDatas.VECHILE_TYPE[1])//minibus
//		{
//			
//		}	
//		else {
//		
//		 }
//		tableModel.setData(datas);
//		tableModel.fireTableDataChanged();
//		tableModel.fireTableStructureChanged();
//		
//	}
//});

 

}
