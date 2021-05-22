package Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.jsefa.Deserializer;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.CsvSerializer;


import DataModels.*;
import Utilities.RawDatas.LOGIN_ID;


@FunctionalInterface
public  interface Key_Retriever<T>
{
	String get(T a);
}


@FunctionalInterface
public  interface Predicate<T>
{
	boolean matches(T a);
}
public class Serialization {
	
	

	// appends
	public static <T> boolean save(Class<T> c,T f)
	{
	
		CsvSerializer serializer= CsvIOFactory.createFactory(c).createSerializer();
		try {
			serializer.open(new FileWriter(new File(gtPath(RawDatas.c2p.get(c.toString()))),true));
			serializer.write(f);
			serializer.close(true);
      } catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	//overwrite
	public static <T> boolean update(Class<T> cls,ArrayList<String> datas)
	{
		 String path=gtPath(RawDatas.c2p.get(cls.toString())); 
		
			try{
				
				Paths.get(path).toFile().delete(); // deleting existing file and writing again
	            OutputStream ostream = Files.newOutputStream(Paths.get(path),StandardOpenOption.CREATE_NEW);
	     	for(String s:datas)
				ostream.write((s + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
			
	        ostream.close();
			}catch (IOException e)
			{
			
				return false;
			}
	        return true;

		
	}
	
	
	// overwrite
	public static <T> boolean save(Class<T> cls,ArrayList<T> datas)
	{
		 String path=gtPath(RawDatas.c2p.get(cls.toString())); 
		 CsvSerializer serializer= CsvIOFactory.createFactory(cls).createSerializer();
			try{
				
				serializer.open(new FileWriter(new File(path)));
				 for(T s:datas)
				serializer.write(s);
	            serializer.close(true);
	        
			}catch (IOException e)
			{
			
				return false;
			}
	        return true;

		
	}

	
	//deleting
	public static <T> boolean save(Class<T> cls,HashMap<String, T> datas)
	{
		
		 String path=gtPath(RawDatas.c2p.get(cls.toString())); 
		 CsvSerializer serializer= CsvIOFactory.createFactory(cls).createSerializer();
			try{
				
				serializer.open(new FileWriter(new File(path)));
				 for(T s:datas.values())
				serializer.write(s);
	            serializer.close(true);
	        
		}catch (IOException e)
		{
		
			return false;
		}
        return true;
		
	}
	
	//updating the data
	public static <T> boolean save(Class<T> cls,HashMap<String, T> data,Key_Retriever<T> key)
	{
	
		 String path=gtPath(RawDatas.c2p.get(cls.toString())); 
		 CsvSerializer serializer= CsvIOFactory.createFactory(cls).createSerializer();
		 HashMap<String,T> map = new HashMap<String, T>();
		 Deserializer deserializer = CsvIOFactory.createFactory(cls).createDeserializer();
		 RawDatas.print("updating:",path);
		 try {
                deserializer.open(new FileReader(new File(path)));
			
				 T model;
			
				while(deserializer.hasNext())
				{
					model=deserializer.next();
					map.putIfAbsent(key.get(model), model);
				}
				    
				    map.putAll(data);
					save(cls,map);
					
				  return true;
				 }catch(Exception e)
				 {
					 System.err.println("Error wile removing duplicates from "+path);
				 }
	     
		return false;
	}
	
	
	
// for data random data generation
	public static <T> T choice(T[] arr)
	{
		
		int i=Math.abs(new SecureRandom().nextInt())%(arr.length);
		
	return arr[i];	
	}
	
	
	public static ArrayList<String> genHires()
	{
		ArrayList<String> arr=new ArrayList<>();
		ArrayList<CarModel> carModels=fetchItems(CarModel.class);
		ArrayList<MbusModel> minis=fetchItems(MbusModel.class);
		ArrayList<LorryModel> lorrys=fetchItems(LorryModel.class);
		SecureRandom random=new SecureRandom();
		
		for(int i=0;i<10;i++)
		{
			arr.add(carModels.get(random.nextInt(carModels.size())).getVechile_id());
			arr.add(minis.get(random.nextInt(minis.size())).getVechile_id());
			arr.add(lorrys.get(random.nextInt(lorrys.size())).getVechile_id());
			
		}
		
		return arr;
	
			
		
	}
	
	public static void main(String[] args) throws IOException {
		
          /*---------------------------------------__Random Data Generation___------------------------*/
		RawDatas.set_rawdatas();
	
		

		
		String  prop[]= {"1020","2030"};
        String make[]= {"TATA","TOYOTA"},models[]= {"ax30l","bc301","cd231"},top_speed[]= {"30","40","50"},reg_no[]= {"4324234","4234232","6543433"},dhr[]= {"10","2","3","40"},fuel_t[]= {"Petrol","Diesel"},no_door[]= {"2","3","4"};
		String seat_cap[]= {"30","20","25","50"},load_cap[]= {"30T","20T","30T"}
		,username[]= {"ram@temple.com","sita@jungle.com","gopal","Akansa","Ananya","Ram","Shyam","Hari"},password[]= {"ram","sita","gopal","Akansa","Ananya","Ram","Shyam","Hari"}
		,status[]={"Hired","Available"},types[]= {"Car","Lorry","MiniBus"}
		,name[]= {"Rita","Gita","Jenny","Akrity","Akansa","Ananya","Ram","Shyam","Hari"}
		,address[]= {"Cecilia Chapman,711-2880 Nulla St.Mankato,Mississippi 96522, (257) 563-7401",
				"Iris Watson,P.O. Box 283 8562 Fusce Rd.,Frederick Nebraska 20620,(372) 587-2335",
				"Celeste Slater,606-3727 Ullamcorper. Street,Roseville NH 11523,(786) 713-8616"},
				contact[]= {"1-541-754-3010","001-541-754","191 541 754 3010"},title[]= {"Receptionist","Manager","Supervisor"};
		
		 Object type[]= {LorryModel.class,CarModel.class,MbusModel.class},lid[]= {LOGIN_ID.CUSTOMER,LOGIN_ID.STAFF};
		 
		
		
		for(int i=0;i<100;i++) {
		
		    save(CarModel.class,new CarModel(RawDatas.gtVId(CarModel.class, choice(prop)),choice(make),choice(models),choice(no_door),choice(fuel_t),choice(top_speed),choice(dhr),choice(reg_no)));
		    save(LorryModel.class,new LorryModel(RawDatas.gtVId(LorryModel.class, choice(prop)),choice(make),choice(models),choice(load_cap),choice(top_speed),choice(dhr),choice(reg_no)));
            save(MbusModel.class,new MbusModel(RawDatas.gtVId(MbusModel.class, choice(prop)),choice(make),choice(models),choice(seat_cap),choice(top_speed),choice(dhr),choice(reg_no)));
            save(StaffModel.class,new StaffModel(choice(username),choice(password),choice(title),choice(name),choice(address).replace("\n", " "),choice(contact)));
			
		}
		
//		filling fleet detail from individual vechile type
		
		removeDuplicates(CarModel.class,(f)->f.getVechile_id());
		removeDuplicates(LorryModel.class,(f)->f.getVechile_id());
		removeDuplicates(MbusModel.class,(f)->f.getVechile_id());
		
		for(int i=0;i<100;i++) {
			save(CusModel.class,new CusModel(choice(username),choice(password),choice(name),choice(address).replace("\n", " "),choice(contact),genHires()));
			
		}
		
		removeDuplicates(CusModel.class,f->f.get_id());
		removeDuplicates(StaffModel.class,f->f.get_id());
		
		ArrayList<CarModel> carModels=fetchItems(CarModel.class);
		ArrayList<MbusModel> minis=fetchItems(MbusModel.class);
		ArrayList<LorryModel> lorrys=fetchItems(LorryModel.class);
		ArrayList<CusModel> cusModels=  fetchItems(CusModel.class,c->!c.getVechiles_on_hire().isEmpty());
		
		for(CarModel c:carModels)
		{
			
			CusModel cusModel =cusModels.stream().findAny().filter(d->d.getVechiles_on_hire().contains(c.getVechile_id())).orElse(new CusModel());
			RawDatas.print(cusModel.get_id(),cusModels.size(),c.getVechile_id());
			if(cusModel.get_id().equals("None"))
			save(VDetailModel.class,new VDetailModel(c.getVechile_id(),RawDatas.VECHILE_TYPE[0],RawDatas.STATUS_AVAILABLE));
			else {
				RawDatas.print(cusModel.get_id(),cusModels.size(),c.getVechile_id());
				save(VDetailModel.class, new VDetailModel(c.getVechile_id(),RawDatas.VECHILE_TYPE[0],RawDatas.STATUS_HIRED,cusModel.get_id()));
			}
		}
//		
		for(MbusModel c:minis)
				{
		CusModel cusModel =cusModels.stream().findFirst().filter(d->d.getVechiles_on_hire().contains(c.getVechile_id())).orElse(new CusModel());
		
		if(cusModel.get_id().equals("None"))
		     save(VDetailModel.class, new VDetailModel(c.getVechile_id(),RawDatas.VECHILE_TYPE[1],RawDatas.STATUS_AVAILABLE));
		else
			save(VDetailModel.class,new VDetailModel(c.getVechile_id(),RawDatas.VECHILE_TYPE[1],RawDatas.STATUS_HIRED,cusModel.get_id()));
      	}
		
		for(LorryModel l:lorrys)
		{
			CusModel cusModel =cusModels.stream().findFirst().filter(d->d.getVechiles_on_hire().contains(l.getVechile_id())).orElse(new CusModel());
			if(cusModel.get_id().equals("None"))
			save(VDetailModel.class,new VDetailModel(l.getVechile_id(),RawDatas.VECHILE_TYPE[2],RawDatas.STATUS_AVAILABLE));
			else
				save(VDetailModel.class,new VDetailModel(l.getVechile_id(),RawDatas.VECHILE_TYPE[2],RawDatas.STATUS_HIRED,cusModel.get_id()));
		}
				
		
		ArrayList<StaffModel> staffs=fetchItems(StaffModel.class);
		ArrayList<CusModel> customers=fetchItems(CusModel.class);
		
		for(StaffModel s:staffs)
			save(LDetailModel.class,new LDetailModel(s.get_id(),s.get_password(),RawDatas.LOGIN_ID.STAFF));
		for(CusModel s:customers)
			save(LDetailModel.class,new LDetailModel(s.get_id(),s.get_password(),RawDatas.LOGIN_ID.CUSTOMER));
		
		
		
		

//		removeDuplicates(VDetailModel.class,(f)->f.getVechile_id());
		removeDuplicates(LDetailModel.class,(f)->f.getUsername()+f.getLogin_id());

		

			
	
		
        
		
	
	
//		FilesHandle.removeDuplicates(LoginDetail.class,(a)->a.getUsername());
//		System.out.println(FilesHandle.fetchItems(LoginDetail.class).size());
	}
	

	// for removing duplicated entries from the system
	 public static  <T> boolean removeDuplicates(Class<T> t,Key_Retriever<T> key)
	{
		 String path=gtPath(RawDatas.c2p.get(t.toString())); 
		 HashMap<String,T> map = new HashMap<String, T>();
		 Deserializer deserializer = CsvIOFactory.createFactory(t).createDeserializer();
		 try {
                deserializer.open(new FileReader(new File(path)));
			
				 T model;
			
				while(deserializer.hasNext())
				{
					model=deserializer.next();
					map.putIfAbsent(key.get(model), model);
				}
				    
					save(t,map);
			
		 }catch(IOException e)
		 {
			 System.err.println("Error wile removing duplicates from "+path+" "+e);
		 }
		 
		
		 return true;
	}
	
	 
	
	 public static <T> ArrayList<T> fetchItems(Class<T> cls)
	 {
		 
		 String path=gtPath(RawDatas.c2p.get(cls.toString())); 
		 ArrayList<T> ret = new ArrayList();
		 Deserializer deserializer = CsvIOFactory.createFactory(cls).createDeserializer();

		 try {
                deserializer.open(new FileReader(new File(path)));
			 T model;
			
				while(deserializer.hasNext())
				{
					model=deserializer.next();
					ret.add(model);
				}
				    
				   deserializer.close(true);
			 
			
		} catch (Exception e) {
			System.err.println("Error while removing duplicates from "+path+" "+e);
		}
	
		 
		 return ret;
	 }
	 
	 public static <T> ArrayList<T> fetchItems(Class<T> cls,Predicate<T> pred)
	 {
		
		 String path=gtPath(RawDatas.c2p.get(cls.toString())); 
		 ArrayList<T> ret = new ArrayList();
		 Deserializer deserializer = CsvIOFactory.createFactory(cls).createDeserializer();

		 try {
                deserializer.open(new FileReader(new File(path)));
			 T model;
			
				while(deserializer.hasNext())
				{
					model=deserializer.next();
					if(pred.matches(model))
					  ret.add(model);
				}
				    
				   deserializer.close(true);
			 
			
		} catch (IOException e) {
			
			System.err.println("Error at fetching time for "+cls.toString()+" "+e );
		}
	
		 
		 return ret;
	 }
	
	 
	 public static <T> ArrayList<String> fetchItemString(Class<T> cls)
	 {
		 System.out.println(cls.toString());
         ArrayList<String> ret= new ArrayList<>();
		 InputStream istream;
		try {
			istream = Files.newInputStream(Paths.get(gtPath(RawDatas.c2p.get(cls.toString()))),StandardOpenOption.READ);
			Scanner sc=new Scanner(istream);
			while(sc.hasNextLine()) {
			
					ret.add(sc.nextLine());
			}
			istream.close();
			 
			
		} catch (IOException e) {
			System.err.println("Error at fetching time for "+cls.toString()+" "+e );
		}
	
		 
		 return ret;
	 }
	 
	 public static <T> ArrayList<String> fetchItemString(Class<T> cls,Predicate<T> pred)
	 {
		 System.out.println(cls.toString());
         ArrayList<String> ret= new ArrayList<>();
		 InputStream istream;
		try {
			istream = Files.newInputStream(Paths.get(gtPath(RawDatas.c2p.get(cls.toString()))),StandardOpenOption.READ);
			Scanner sc=new Scanner(istream);
			String line=null;
			while(sc.hasNextLine()) {
				try {
				line=sc.nextLine();
				if(pred.matches(RawDatas.deserilizeTo(cls, line)))
					ret.add(line);
				}catch (Exception e) {
					continue;
				}
			}						
			istream.close();
			 
			
		} catch (IOException e) {
			System.err.println("Error at fetching time for "+cls.toString()+" "+e );
		}
	
		 
		 return ret;
	 }
	
	
	 
	 public static <T> Map<String,T> fetchItemMap(Class<T> cls,Key_Retriever<T> key)
	 {
		 String path=gtPath(RawDatas.c2p.get(cls.toString())); 
		 HashMap<String,T> map = new HashMap<String, T>();
		 Deserializer deserializer = CsvIOFactory.createFactory(cls).createDeserializer();
		 try {
                deserializer.open(new FileReader(new File(path)));
			
				 T model;
			
				while(deserializer.hasNext())
				{
					model=deserializer.next();
					map.putIfAbsent(key.get(model), model);
				}
				    
					

		 }catch(IOException e)
		 {
			 System.err.println("Error while fetching file from "+path);
		 }
		 return map;
		 
	 }
	 
	 public static <T> Map<String,T> fetchItemMap(Class<T> cls,Key_Retriever<T> key,Predicate<T> pred)
	 {
		 String path=gtPath(RawDatas.c2p.get(cls.toString())); 
		 HashMap<String,T> map = new HashMap<String, T>();
		 Deserializer deserializer = CsvIOFactory.createFactory(cls).createDeserializer();
		 try {
                deserializer.open(new FileReader(new File(path)));
			
				 T model;
			
				while(deserializer.hasNext())
				{
					model=deserializer.next();
					if(pred.matches(model))
					map.putIfAbsent(key.get(model), model);
				}
				    
					

		 }catch(IOException e)
		 {
			 System.err.println("Error while fetching file from "+path);
		 }
		 return map;
		 
	 }
	 

	 
	 
	 
	 // returns path from filename also checks the if files exists otherwirs creates new
	 public static String gtPath(String fname) 
	{
		String path="."+File.separator+"src"+File.separator+"Files"+File.separator+fname;
		System.out.println(path);
		
		if (Files.exists(Paths.get(path)))
		return path;
		else {
			
			try {
				new File(path).createNewFile();
				System.out.println(path+" created");
				
			} catch (IOException e) {
				System.out.println(path+" exists");
				e.printStackTrace();
			}
			
			return path;
		}
		
	}
}










