package WindowManagement;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataModels.CarModel;
import DataModels.CusModel;
import DataModels.DataTableModel;
import DataModels.LorryModel;
import DataModels.MbusModel;
import DataModels.VDetailModel;
import Utilities.Serialization;
import Utilities.Predicate;
import Utilities.RawDatas;

public class Return extends JFrame {

	private JPanel contentPane;
	private JTextField tfield1;
	private JTextField tfield2;
	private JTextField tfield3;
	private JTable table;
	private static String vType=RawDatas.VECHILE_TYPE[0];
	private JLabel labels[]= {new JLabel("Type"),new JLabel(),new JLabel()};
	private JTextField tfields[]= {new JTextField(),new JTextField()};
	private ArrayList<String> datas=null;
	private HashMap<String,VDetailModel> hired_fleet=null;
	private JScrollPane pane=null;
	private JPanel panel_3=null;
	private JPanel panel_3_1;
	private DataTableModel dataTableModel=null;
	private JButton return_btn=null,cancel_btn=null;
	private int car_doors=4;
	private String car_ftype="";
	private boolean car_door_selected=false,car_ftype_selected=false;
	private Class<?> cls=CarModel.class;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		RawDatas.set_rawdatas();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Return frame = new Return();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Return() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(RawDatas.WIN_SIZE,RawDatas.WIN_SIZE);
		getContentPane().setLayout(null);
		
		fetchData();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1000, 1);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(43, 13, 923, 68);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHireVechile = new JLabel(RawDatas.RETURN_TITLE);
		lblHireVechile.setFont(new Font("DejaVu Serif", Font.BOLD, 32));
		lblHireVechile.setBounds(0, 12, 700, 44);
		panel_1.add(lblHireVechile);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(43, 130, 370, 352);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		
		labels[0].setFont(new Font("Dialog", Font.BOLD, 15));
		labels[0].setToolTipText("Select vechile type");
		labels[0].setBounds(12, 32, 70, 15);
		panel_2.add(labels[0]);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(186, 23, 172, 24);
		for(String s: RawDatas.VECHILE_TYPE)
			comboBox.addItem(s);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vType=(String)comboBox.getSelectedItem();
				showLabels();
				 updateData(null);
					
	    	 updateTable();
	    	dataTableModel.fireTableStructureChanged();
				
			}
		});
		panel_2.add(comboBox);
		
		
		labels[1].setToolTipText("Select vechile type");
		labels[1].setFont(new Font("Dialog", Font.BOLD, 15));
		labels[1].setBounds(12, 94, 150, 15);
		panel_2.add(labels[1]);
		
	
		labels[2].setToolTipText("Select vechile type");
		labels[2].setFont(new Font("Dialog", Font.BOLD, 15));
		labels[2].setBounds(12, 145, 150, 15);
		panel_2.add(labels[2]);
		
		
	
		
		tfields[0].setBounds(186, 23+50, 172, 37);
		tfields[0].setColumns(10);
		tfields[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text=tfields[0].getText();
				try {
				if(vType==RawDatas.VECHILE_TYPE[0]) //car
				{
				
					 if(text.isEmpty())
					 {
						 car_door_selected=false;
						 datas=Serialization.fetchItemString(CarModel.class, (CarModel c)->{
								if(car_ftype_selected)
									return hired_fleet.containsKey(c.getVechile_id())&&Integer.valueOf(c.getNo_of_doors())==car_doors&&c.getFule_type().compareToIgnoreCase(car_ftype)==0;
								else
									return hired_fleet.containsKey(c.getVechile_id());});
					 }else
					 {
						 car_door_selected=true;
						 car_doors=Integer.valueOf(text);
					 
				    	datas=Serialization.fetchItemString(CarModel.class, (CarModel c)->{
						if(car_ftype_selected)
							return hired_fleet.containsKey(c.getVechile_id())&&Integer.valueOf(c.getNo_of_doors())==car_doors&&c.getFule_type().compareToIgnoreCase(car_ftype)==0;
							else
						return hired_fleet.containsKey(c.getVechile_id())&&Integer.valueOf(c.getNo_of_doors())==car_doors;
						});
					 }
				}else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
				{
					 if(text.isEmpty()) 
						 datas=Serialization.fetchItemString(MbusModel.class, (MbusModel m)->{return hired_fleet.containsKey(m.getVechile_id());});
					 
							 else {
					int passenger_count=Integer.valueOf(tfields[0].getText());
					datas=Serialization.fetchItemString(MbusModel.class, (MbusModel m)->{return hired_fleet.containsKey(m.getVechile_id())&&Integer.valueOf(m.getMax_seating_capacity())>=passenger_count;});
							 }
							 }	
				else {//lorry
				
					
				  
					 if(text.isEmpty()) 
						  
				    datas=Serialization.fetchItemString(LorryModel.class, (LorryModel l)->{return hired_fleet.containsKey(l.getVechile_id())&&Integer.valueOf(l.getMax_load_capacity().substring(0, l.getMax_load_capacity().length()-1))>=0;});
					 else {
						 int Load_cap=Integer.valueOf(text.substring(0,text.length()));
						 System.out.println(Load_cap);
						 datas=Serialization.fetchItemString(LorryModel.class, (LorryModel l)->{return hired_fleet.containsKey(l.getVechile_id())&&Integer.valueOf(l.getMax_load_capacity().substring(0, l.getMax_load_capacity().length()-1))>=Load_cap;});
					 }
				 }
				
			
				
				}catch (Exception b) {
				System.err.println(b);	
				}
			updateTable();
				
			}
		});
		panel_2.add(tfields[0]);
		
		
	
		tfields[1].setColumns(10);
		tfields[1].setBounds(186, 23+100+10, 172, 37);
        tfields[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			          car_ftype=tfields[1].getText();
					  System.out.println(car_ftype);
					  if(!car_ftype.isBlank())
					  {
						  car_ftype_selected=true;
					  datas=Serialization.fetchItemString(CarModel.class, (CarModel c)->{
						  if(car_door_selected)
							  return hired_fleet.containsKey(c.getVechile_id())&&c.getFule_type().compareToIgnoreCase(car_ftype)==0&&Integer.valueOf(c.getNo_of_doors())==car_doors;
						  
						else
						  return hired_fleet.containsKey(c.getVechile_id())&&c.getFule_type().compareToIgnoreCase(car_ftype)==0;
						  });
					  }else
						  car_ftype_selected=false;
				   
	         updateTable();
				
			}
		});
				panel_2.add(tfields[1]);
		
		

		
	    panel_3_1 = new JPanel();
		panel_3_1.setBounds(443, 130, 523, 493);
		getContentPane().add(panel_3_1);
		panel_3_1.setLayout(null);
		panel_3_1.setVisible(true);
		getContentPane().add(panel_3_1);
		
	    showTable(datas);
		showLabels();
	}
	

	private void  updateData(Predicate<?> p)
	{
	
			
		if(vType==RawDatas.VECHILE_TYPE[0]) //car
		{
			cls=CarModel.class;
			
			if(p==null)
				p=(CarModel c)->hired_fleet.containsKey(c.getVechile_id());
			this.datas=Serialization.fetchItemString(CarModel.class,(Predicate<CarModel>) p);
		   
		}else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
		{
			cls=MbusModel.class;
			if(p==null)
				p=(MbusModel m)->hired_fleet.containsKey(m.getVechile_id());
			this.datas=Serialization.fetchItemString(MbusModel.class,(Predicate<MbusModel>) p);
		}	
	   else {// Lorry
		   if(p==null)
			   cls=LorryModel.class;
				p=(LorryModel l)->hired_fleet.containsKey(l.getVechile_id());
		   this.datas=Serialization.fetchItemString(LorryModel.class,(Predicate<LorryModel>) p);
	     }
	}
	
    private void fetchData()
{
	hired_fleet=(HashMap<String, VDetailModel>) Serialization.fetchItemMap(VDetailModel.class, f->f.getVechile_id(),f->f.getStatus().equals(RawDatas.STATUS_HIRED));
	this.datas=Serialization.fetchItemString(CarModel.class,c->hired_fleet.containsKey(c.getVechile_id()));// getting data
    
}

    private void updateTable()
    {
    	 
			dataTableModel.setData(cls,datas);
			dataTableModel.fireTableDataChanged();
		
    }
	
    private void showTable(ArrayList<String> datas)
	{
		
		dataTableModel=DataTableModel.getTableModel(cls,datas);
		table=new JTable(dataTableModel);
	    return_btn = new JButton(RawDatas.RETURN_BTN_1);
		cancel_btn = new JButton(RawDatas.RETURN_BTN_2);
       
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(table.getSelectedRowCount()==0)
					return_btn.setEnabled(false);
				else
					return_btn.setEnabled(true);
				
			}
		});
	    table.setBounds(524, 353, 517, 351);
		
		pane=new JScrollPane(table);
		pane.setBounds(0, 0, 523,403);
		panel_3_1.add(pane);
		
		
		
	   
		return_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				VDetailModel item;
				
               int confirmation=JOptionPane.showConfirmDialog(null, "Do you want to return "+table.getSelectedRowCount()+" "+vType+" back to fleet?");
               ArrayList<CusModel> cusModels=   Serialization.fetchItems(CusModel.class);
//       		   Supplier<Stream<Customer>> cStream= ()->customers.values().stream();
               
			     if(confirmation==0) {
			    	 CusModel cusModel;
		       for(int i : table.getSelectedRows()) {
		    	    String key=(String) table.getValueAt(i, 0);
		    	   
		    	    cusModel = cusModels.stream().findAny().filter(d->d.getVechiles_on_hire().contains(key)).orElse(new CusModel());
		    	    if(!cusModel.get_id().equals("None"))
		    	      cusModel.getVechiles_on_hire().remove(key);
		    	    item=hired_fleet.get(key);
		    	    item.setStatus(RawDatas.STATUS_AVAILABLE); //updating status to hired
		    	    hired_fleet.put(key, item);
                }
		       
		       Serialization.save(CusModel.class,cusModels);
		       Serialization.save(VDetailModel.class,hired_fleet,(p)->p.getVechile_id()); //saving the fleet detail
		       fetchData(); //now just updating data and table
		       updateData(null);
		       updateTable();
			     }
			
			}
			
		});
		
		return_btn.setBounds(12, 424, 124, 43);
		return_btn.setEnabled(false);
		panel_3_1.add(return_btn);
		cancel_btn.setBounds(394, 424, 117, 43);
		cancel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				if(!RawDatas.windows.isEmpty()) {
					RawDatas.windows.peek().setVisible(true);
					RawDatas.windows.pop().setEnabled(true);
				}
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						dispose();
					}
						
				});
			}
		});
		panel_3_1.add(cancel_btn);
		pane.setVisible(true);
		
	}
	
    private   void showLabels()
	{
		
		for(JLabel lbl:labels)
				lbl.setVisible(false);
	    labels[0].setVisible(true);
		for(JTextField tf:tfields)
			  tf.setVisible(false);
		
		int i=1;
		
		if(vType==RawDatas.VECHILE_TYPE[0])
		{
			for(String s:RawDatas.SPEC_CAR)
			{
				labels[i].setVisible(true);
				tfields[i-1].setVisible(true);
				tfields[i-1].setText("");
				labels[i].setText(s);
			i++;	
			}
			
		}else if(vType==RawDatas.VECHILE_TYPE[1])
		{
			for(String s:RawDatas.SPEC_MINIBUS)
			{
				labels[i].setVisible(true);
				tfields[i-1].setVisible(true);
				tfields[i-1].setText("");
				labels[i].setText(s);
			i++;	
			}
			
		}else {
			for(String s:RawDatas.SPEC_LORRY)
			{
				labels[i].setVisible(true);
				tfields[i-1].setVisible(true);
				tfields[i-1].setText("");
				labels[i].setText(s);
			i++;	
			}
		}
		
	}
	
	
	

}
