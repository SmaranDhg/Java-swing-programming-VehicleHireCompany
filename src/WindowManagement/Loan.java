package WindowManagement;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import DataModels.CarModel;
import DataModels.CusModel;
import DataModels.DataTableModel;
import DataModels.LDetailModel;
import DataModels.LorryModel;
import DataModels.MbusModel;
import DataModels.VDetailModel;
import Utilities.Serialization;

import Utilities.Predicate;
import Utilities.RawDatas;

import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSeparator;

public class Loan extends JFrame {
	private JTextField tfield1;
	private JTextField tfield2;
	private JTextField tfield3;
	private JTable table;
	private static String vType=RawDatas.VECHILE_TYPE[0];
	private JLabel label2,label1;
	private JLabel labels[];
	private JTextField tfields[];
	private ArrayList<String> datas=null;
	private HashMap<String,VDetailModel> available_fleet=null;
	private JScrollPane pane=null;
	private JPanel panel_3=null;
	private JPanel panel_3_1;
	private DataTableModel dataTableModel=null;
	private JButton hire_btn=null,cancel_btn=null;
	private int car_doors=4;
	private String car_ftype="";
	private boolean car_door_selected=false,car_ftype_selected=false;
	private Class<?> cls =CarModel.class;
	private JTextField textField1;
	private JTextField textField2;
	private JLabel label;
	private boolean fromCustomerMenu=false;
	private String user="";
	private JFrame parent=null;
	/**
	 * Create the frame.
	 */
	public Loan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(RawDatas.WIN_SIZE,RawDatas.WIN_SIZE);
		  label = new JLabel("Type");
		  label.setHorizontalAlignment(SwingConstants.TRAILING);
		  label1 = new JLabel();
		  label1.setHorizontalAlignment(SwingConstants.TRAILING);
		  label2 = new JLabel();
		  label2.setHorizontalAlignment(SwingConstants.TRAILING);
		  labels= new JLabel[]{label,label1,label2};
		  
		  textField1 = new JTextField();
		  textField1.setColumns(10);
		  textField2 = new JTextField();
		  textField2.setColumns(10);
		  tfields=new JTextField[] {textField1,textField2};
		
		 fetchData();
		
		
		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(43, 13, 923, 53);
		panel_1.setLayout(null);
		
		JLabel lblHireVechile = new JLabel(RawDatas.HIRE_TITLE);
		lblHireVechile.setFont(RawDatas.FONT_TITLE);
		lblHireVechile.setBounds(0, 12, 911, 44);
		panel_1.add(lblHireVechile);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(43, 64, 923, 75);
	    panel_2.add(tfields[1]);
		
	
		JComboBox comboBox = new JComboBox();
		
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
		
		
	
		
		
		
		
		panel_3_1 = new JPanel();
		panel_3_1.setBounds(43, 153, 923, 493);
	
//		for(String s: RawDatas.VECHILE_TYPE)
//			comboBox.addItem(s);
		
		
		label1.setToolTipText("Select vechile type");
		label1.setFont(RawDatas.FONT_LABEL);
	
		
	
		label2.setToolTipText("Select vechile type");
		label2.setFont(RawDatas.FONT_LABEL);
	
	
		
		tfields[0].setBounds(186, 23+50, 172, 37);
		tfields[0].setColumns(10);
		tfields[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text=tfields[0].getText();
				System.out.println(text);
				try {
				if(vType==RawDatas.VECHILE_TYPE[0]) //car
				{
				
					 if(text.isBlank())// just keep the data as it was
					 {
						 System.out.println(text);
						 car_door_selected=false;
						 datas=Serialization.fetchItemString(CarModel.class, (CarModel c)->{
							 	if(car_ftype_selected)
									return available_fleet.containsKey(c.getVechile_id())&&Integer.valueOf(c.getNo_of_doors())==car_doors&&c.getFule_type().compareToIgnoreCase(car_ftype)==0;
								else
									return available_fleet.containsKey(c.getVechile_id());});
					 
					 }
					 else{
						 car_door_selected=true;
						 car_doors=Integer.valueOf(text);
						 RawDatas.print(text,car_doors);
					 
				    	datas=Serialization.fetchItemString(CarModel.class, (CarModel c)->{
						if(car_ftype_selected) return available_fleet.containsKey(c.getVechile_id())&&Integer.valueOf(c.getNo_of_doors())==car_doors&&c.getFule_type().compareToIgnoreCase(car_ftype)==0;
						else return available_fleet.containsKey(c.getVechile_id())&&Integer.valueOf(c.getNo_of_doors())==car_doors;
						});
					 
				}}else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
				{
					 if(text.isEmpty()) 
						 datas=Serialization.fetchItemString(MbusModel.class, (MbusModel m)->{
							 
							 return available_fleet.containsKey(m.getVechile_id());});
					 
							 else {
					int passenger_count=Integer.valueOf(text);
					datas=Serialization.fetchItemString(MbusModel.class, (MbusModel m)->{return available_fleet.containsKey(m.getVechile_id())&&Integer.valueOf(m.getMax_seating_capacity())>=passenger_count;});
							 }
				}	
				
				
				else {//lorry
				
					
				  
					 if(text.isEmpty()) 
						  
				    datas=Serialization.fetchItemString(LorryModel.class, (LorryModel l)->{return available_fleet.containsKey(l.getVechile_id())&&Integer.valueOf(l.getMax_load_capacity().substring(0, l.getMax_load_capacity().length()-1))>=0;});
					 else {
						 int Load_cap=Integer.valueOf(text.substring(0,text.length()));
						 System.out.println(Load_cap);
						 datas=Serialization.fetchItemString(LorryModel.class, (LorryModel l)->{return available_fleet.containsKey(l.getVechile_id())&&Integer.valueOf(l.getMax_load_capacity().substring(0, l.getMax_load_capacity().length()-1))>=Load_cap;});
					 }
				 }
				
			
				
				}catch (Exception b) {
				System.err.println(b);
				b.printStackTrace();
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
							  return available_fleet.containsKey(c.getVechile_id())&&c.getFule_type().compareToIgnoreCase(car_ftype)==0&&Integer.valueOf(c.getNo_of_doors())==car_doors;
						  
						else
						  return available_fleet.containsKey(c.getVechile_id())&&c.getFule_type().compareToIgnoreCase(car_ftype)==0;
						  });
					  }else {
						 car_ftype_selected=false;
		               datas=Serialization.fetchItemString(MbusModel.class,  m-> available_fleet.containsKey(m.getVechile_id()));
					  }
				   
	         updateTable();
				
			}
		});
				
		panel.add(panel_3_1);
		panel.add(panel_1);
		panel.add(panel_2);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
						.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(43)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		
		JSeparator separator = new JSeparator();

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label2, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField2, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addGap(68))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(label1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
									.addGap(2)
									.addComponent(label2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	    showTable(datas);
		showLabels();
	
	}
	
	private void  updateData(Predicate<?> p)
	{
	
			
		if(vType==RawDatas.VECHILE_TYPE[0]) //car
		{
			cls=CarModel.class;
			if(p==null)
				p=(CarModel c)->available_fleet.containsKey(c.getVechile_id());
			this.datas=Serialization.fetchItemString(CarModel.class,(Predicate<CarModel>) p);
		   
		}else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
		{
			cls=MbusModel.class;
			if(p==null)
				p=(MbusModel m)->available_fleet.containsKey(m.getVechile_id());
			this.datas=Serialization.fetchItemString(MbusModel.class,(Predicate<MbusModel>) p);
		}	
	   else {// Lorry
		   cls=LorryModel.class;
		   if(p==null)
				p=(LorryModel l)->available_fleet.containsKey(l.getVechile_id());
		   this.datas=Serialization.fetchItemString(LorryModel.class,(Predicate<LorryModel>) p);
	     }
	}
	
	public Loan setParent(JFrame parent)
	{
		this.parent=parent;
		return this;
	}
	
    private void fetchData()
{
	available_fleet=(HashMap<String, VDetailModel>) Serialization.fetchItemMap(VDetailModel.class, (f)->f.getVechile_id(),(f)->f.getStatus().equals(RawDatas.STATUS_AVAILABLE));
	this.datas=Serialization.fetchItemString(CarModel.class,(c)->available_fleet.containsKey(c.getVechile_id()));// getting data
	
}

    private void updateTable()
    {
    	 
			dataTableModel.setData(cls,datas);
			dataTableModel.fireTableDataChanged();
		
    }
    public Loan setCId(String id)
	{
		this.user=id;
		this.fromCustomerMenu=true;
		return this;
	}
	private void showTable(ArrayList<String> datas)
	{
		
		dataTableModel=DataTableModel.getTableModel(cls,datas);
		table=new JTable(dataTableModel);
	    hire_btn = new JButton(RawDatas.HIRE_BTN_1);
		cancel_btn = new JButton(RawDatas.HIRE_BTN_2);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(table.getSelectedRowCount()==0)
					hire_btn.setEnabled(false);
				else
					hire_btn.setEnabled(true);
				
			}
		});
	    table.setBounds(524, 353, 517, 351);
		
		pane=new JScrollPane(table);
		pane.setBounds(new Rectangle(0, 12, 923, 418));
		hire_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("btn");
                 
				
				 
				 if(user.equals(""))
	                  user=JOptionPane.showInputDialog("Enter User ID to hire for.");
				     boolean usernotfound=Serialization.fetchItems(LDetailModel.class,l->(l.getUsername()).equals(user)&& l.getLogin_id().equals(RawDatas.LOGIN_ID.CUSTOMER)).isEmpty();
			     HashMap<String,CusModel> customers=(HashMap<String, CusModel>) Serialization.fetchItemMap(CusModel.class,c->c.get_id());
		    	 
			     if(usernotfound)
			    	 JOptionPane.showMessageDialog(new JFrame(),"Customer Id: "+user+" doesn't exists ","Trouble!",JOptionPane.ERROR_MESSAGE);
			     else {
			    	 int confirmation=JOptionPane.showConfirmDialog(null, "Do you want to hire "+table.getSelectedRowCount()+" "+vType+" for user "+user+" to fleet?");
			   if(confirmation==0) {
				VDetailModel item;
				String key="";
		       for(int i : table.getSelectedRows()) {
		    	    key=(String) table.getValueAt(i, 0);
		    	    item=available_fleet.get(key);
		    	    item.setStatus(RawDatas.STATUS_HIRED); //updating status to hired
		    	    item.setCustomer_id(user);
		    	    customers.get(user).getVechiles_on_hire().add(key);
		    	    available_fleet.put(key, item);
                }
		       Serialization.save(CusModel.class, customers);
		       Serialization.save(VDetailModel.class,available_fleet,(p)->p.getVechile_id()); //saving the fleet detail
		       fetchData(); //now just updating data and table
		       updateData(null);
		       updateTable();
			     }}
			     
			}
			
		});
		
		hire_btn.setBounds(10, 438, 124, 43);
		hire_btn.setEnabled(false);
		cancel_btn.setBounds(402, 438, 117, 43);
		cancel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(parent!=null&&fromCustomerMenu)
					((Menu1) parent).updateCustomer();
			
				if(!RawDatas.windows.isEmpty())
					RawDatas.windows.pop().setVisible(true);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						dispose();
					}
						
				});
			}
		});
		GroupLayout gl_panel_3_1 = new GroupLayout(panel_3_1);
		gl_panel_3_1.setHorizontalGroup(
			gl_panel_3_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(hire_btn)
					.addGap(34)
					.addComponent(cancel_btn)
					.addContainerGap(727, Short.MAX_VALUE))
				.addComponent(pane, GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
		);
		gl_panel_3_1.setVerticalGroup(
			gl_panel_3_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(pane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_3_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancel_btn)
						.addComponent(hire_btn)))
		);
		panel_3_1.setLayout(gl_panel_3_1);
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
	
	
	
	
	public static void main(String[] args) {
	     try {
			UIManager.setLookAndFeel(
			         UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		RawDatas.set_rawdatas();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loan frame = new Loan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}




