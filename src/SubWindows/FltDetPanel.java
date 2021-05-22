package SubWindows;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import DataModels.CarModel;
import DataModels.CusModel;
import DataModels.DataTableModel;
import DataModels.LorryModel;
import DataModels.MbusModel;
import DataModels.VDetailModel;
import Utilities.Serialization;
import Utilities.Predicate;
import Utilities.RawDatas;

public class FltDetPanel extends JPanel {
	private ArrayList<String> datas=null;
	private DataTableModel tablemodel=null;
	private JComboBox combo_type,combo_status;
	private JLabel lbl_type,lbl_status;
	private JTable table;
	private static String vType=RawDatas.VECHILE_TYPE[0];
	private static String vStatus=RawDatas.STATUS_AVAILABLE;
	private static HashMap<String,VDetailModel> vDetailModel=null;
	private  Class<?> cls=CarModel.class;

	/**
	 * Create the panel.
	 */
	public FltDetPanel() {
		fetchData();

		 lbl_type = new JLabel(RawDatas.FLEET_LBL_1);
		 lbl_status = new JLabel(RawDatas.FLEET_LBL_2);
		  combo_type = new JComboBox();
		 combo_status = new JComboBox();
		 for(String s:RawDatas.VECHILE_TYPE)
			 combo_type.addItem(s);
		 
		 for(String s:RawDatas.STATUS)
			 combo_status.addItem(s);
		 
		 
			combo_type.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					vType=(String)combo_type.getSelectedItem();
					
					 updateData(null);
						
		    	 updateTable();
		    	 tablemodel.fireTableStructureChanged();
					
				}
			});
			combo_status.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					vStatus=(String)combo_status.getSelectedItem();
					vDetailModel=(HashMap<String, VDetailModel>) Serialization.fetchItemMap(VDetailModel.class,f->f.getVechile_id(),f->f.getStatus().equals(vStatus));
					
					updateData(null);
						
		    	 updateTable();
		    	tablemodel.fireTableStructureChanged();
					
				}
			});
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(combo_type, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_type, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbl_status, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addComponent(combo_status, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 559, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_type)
						.addComponent(lbl_status))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(combo_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(combo_status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(this.tablemodel);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}
	public  void updateData(Predicate<?> p)
	{
		
		if(vType==RawDatas.VECHILE_TYPE[0]) //car
		{
			cls=CarModel.class;
			if(p==null)
				p=(CarModel c)->vDetailModel.containsKey(c.getVechile_id());
			this.datas=Serialization.fetchItemString(CarModel.class,(Predicate<CarModel>) p);
		   
		}else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
		{
			cls=CarModel.class;
			if(p==null)
				p=(MbusModel m)->vDetailModel.containsKey(m.getVechile_id());
			this.datas=Serialization.fetchItemString(MbusModel.class,(Predicate<MbusModel>) p);
		}	
	   else {// Lorry
		   cls=CarModel.class;
		   if(p==null)
				p=(LorryModel l)->vDetailModel.containsKey(l.getVechile_id());
		   this.datas=Serialization.fetchItemString(LorryModel.class,(Predicate<LorryModel>) p);
	     }
		
		
		
	}
	 public void updateTable()
	    {
		 tablemodel.setData(cls,datas);
		 tablemodel.fireTableDataChanged();
		}	
	 private void fetchData()
	{
		this.datas=Serialization.fetchItemString(cls);
		this.tablemodel=DataTableModel.getTableModel(cls,this.datas);
		this.vDetailModel=(HashMap<String, VDetailModel>) Serialization.fetchItemMap(VDetailModel.class,f->f.getVechile_id(),f->f.getStatus().equals(vStatus));
	}
	public ArrayList<String> getData()
	{
		return this.datas;
	}
	public void setData(ArrayList<String> data)
	{
		this.datas=data;
	}
	public Class<?> get_Class()
	{
		return this.cls;
	}

	public String getVtype()
	{
		return this.vType;
	}

	
	
	public JTable getTable()
	{
		return this.table;
	}


public static void main(String[] args) {
	
	RawDatas.set_rawdatas();

	ArrayList<CarModel> carModel=Serialization.fetchItems(CarModel.class);
	
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				JFrame frame=new JFrame();
				frame.getContentPane().add(new FltDetPanel());
				frame.setSize(RawDatas.WIN_SIZE, RawDatas.WIN_SIZE);
				frame.setVisible(true);;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}
	
