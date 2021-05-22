package WindowManagement;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import DataModels.CusModel;
import DataModels.DataTableModel;
import DataModels.LDetailModel;
import DataModels.VDetailModel;
import SubWindows.CusDetPanel;
import SubWindows.CusFmPanel;
import SubWindows.VecDetPanel;
import Utilities.Serialization;
import Utilities.RawDatas;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class Customers extends JFrame {

	private JPanel contentPane;
	private JTable table;
    private JScrollPane sPane=null;
    private DataTableModel tableModel=null;
    private ArrayList<String> datas=null;
    private HashMap<String,CusModel> cMap=null;
    private HashMap<String,LDetailModel> ldMap=null;
    private JButton btnDetail,remove_btn,btnAdd,cancel_btn;
    private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		RawDatas.set_rawdatas();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customers frame = new Customers();
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
	public Customers() {
	    this.frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(RawDatas.WIN_SIZE,RawDatas.WIN_SIZE);
		getContentPane().setLayout(null);
		fetchData();
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 707);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(33, 90, 928, 468);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		
//		RawDatas.print(datas.get(0),tableModel.getColumnCount(),tableModel.getRowCount(),tableModel.getValueAt(0, 5));
		table = new JTable(tableModel);
		table.setRowSelectionAllowed(true);
	    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					if(table.getSelectedRowCount()==0) {
						btnDetail.setEnabled(false);
						remove_btn.setEnabled(false);
						
							
					}
					else {
						btnDetail.setEnabled(true);
						remove_btn.setEnabled(true);
						
					}
						
						
				}});
		sPane=new JScrollPane(table);
		
		table.setBounds(524, 353, 800, 351);
		sPane.setBounds(0, 0, 900,468);
		sPane.setVisible(true);
		panel_1.add(sPane);
		
		JLabel lblNewLabel = new JLabel(RawDatas.CUSTOMERS_TITLE);
		lblNewLabel.setFont(RawDatas.FONT_TITLE);
		lblNewLabel.setBounds(33, 12, 928, 70);
		panel.add(lblNewLabel);
		
		 btnDetail = new JButton(RawDatas.CUSTOMERS_BTN_1);
		 btnDetail.setEnabled(false);
		 btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RawDatas.windows.push(frame);
				frame.setEnabled(false);
				JFrame jf=RawDatas.getJFrame();
				jf.setSize((int)(RawDatas.WIN_SIZE*.7), (int)(RawDatas.WIN_SIZE*.6));
		        jf.getContentPane().add(new CusDetPanel(RawDatas.deserilizeTo(CusModel.class,datas.get(table.getSelectedRow()) )).setJframe(jf));
				
			}});
		btnDetail.setBounds(68, 586, 136, 50);
		panel.add(btnDetail);
		
		 btnAdd = new JButton(RawDatas.CUSTOMERS_BTN_3);
		 btnAdd.setFont(RawDatas.FONT_LABEL);
		 btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    RawDatas.windows.push(frame);
				frame.setEnabled(false);
				JFrame jf=RawDatas.getJFrame();
				jf.setSize((int)(RawDatas.WIN_SIZE*.45), (int)(RawDatas.WIN_SIZE*.6));
				
		        jf.getContentPane().add(new CusFmPanel(jf,(Customers) frame));
		        
				
			}
		});
		 btnAdd.setBounds(431, 586, 136, 50);
		panel.add(btnAdd);
		
		remove_btn = new JButton(RawDatas.CUSTOMERS_BTN_2);
		remove_btn.setBounds(252, 586, 136, 50);
		remove_btn.setEnabled(false);
		remove_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirmation=JOptionPane.showConfirmDialog(null, "Do you want to remove "+table.getSelectedRowCount()+" Customers?");
				   if(confirmation==0) {
					VDetailModel item;
					String key="";
			       for(int i : table.getSelectedRows()) {
			    	    key=(String) table.getValueAt(i, 0);
			    	    datas.remove(i);
			    	    cMap.remove(key);
			    	    ldMap.remove(key+RawDatas.LOGIN_ID.CUSTOMER);
			   
	                }
			       
			       Serialization.save(CusModel.class,cMap); //saving the fleet detail
			       Serialization.save(LDetailModel.class,ldMap); //saving the fleet detail
			       
			       updateTable();
				     }
			}

		
		});
		panel.add(remove_btn);
		
		 cancel_btn = new JButton(RawDatas.CUSTOMERS_BTN_4);
		cancel_btn.setBounds(619, 586, 128, 50);
		cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		panel.add(cancel_btn);
	}
	
	private void fetchData()
	{
		 this.datas=Serialization.fetchItemString(CusModel.class);
		this.cMap=(HashMap<String, CusModel>) Serialization.fetchItemMap(CusModel.class, c->c.get_id());
		this.ldMap=(HashMap<String, LDetailModel>) Serialization.fetchItemMap(LDetailModel.class, c->c.getUsername()+c.getLogin_id());
		tableModel= DataTableModel.getTableModel(CusModel.class,datas);
	}
	   public void updateTable()
	    {
		       this.datas=Serialization.fetchItemString(CusModel.class);
			   tableModel.setData(CusModel.class,datas);
			   tableModel.fireTableDataChanged();
		 }
}
