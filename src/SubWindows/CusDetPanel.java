package SubWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;

import DataModels.CarModel;
import DataModels.CusModel;
import DataModels.DataTableModel;
import DataModels.LorryModel;
import DataModels.MbusModel;
import DataModels.VDetailModel;

import Utilities.Serialization;

import Utilities.RawDatas;

public class CusDetPanel extends JPanel {

	private JPanel contentPane;

	private  CusModel cusModel;
	private DataTableModel tablemodel;
	private ArrayList<String> tabledata;
	private JTable table;
	private JFrame jframe;
	private int cars=0,minibuses=0,lorries=0;
    private JTextArea textArea;
	/**
	 * Create the frame.
	 */
	public CusDetPanel(CusModel c) {
		
		this.cusModel=c;
		fetchData();
		JLabel lblNewLabel = new JLabel(RawDatas.CDETAIL_1);
		
		JLabel info = new JLabel();
		info.setText(c.get_id());
		
		JLabel lblName = new JLabel(RawDatas.CDETAIL_2);
		
		JLabel info_1 = new JLabel();
		info_1.setText(c.get_name());
		
		JLabel lblAddress = new JLabel(RawDatas.CDETAIL_3);
	
		JTextPane txtpnThis = new JTextPane();
		txtpnThis.setText(c.get_address().replace(",", "\n"));
		txtpnThis.setFont(new Font("Dialog", Font.BOLD, 12));
		txtpnThis.setBackground(UIManager.getColor("Button.background"));
		txtpnThis.setEditable(false);
		
		JLabel lblContact = new JLabel(RawDatas.CDETAIL_4);
		
		JLabel info_3 = new JLabel();
		info_3.setText(c.get_contact());
		
		JLabel lblVechilesHired = new JLabel(RawDatas.CDETAIL_5);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		 textArea = new JTextArea();
		updateCount();
		RawDatas.print(cars,minibuses);
		textArea.setFont(RawDatas.FONT_LABEL);
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setEditable(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblContact, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAddress, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addGap(10))
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(lblVechilesHired, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtpnThis, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(info_1, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(info_3, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
								.addComponent(info, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
							.addGap(48))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(info, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(info_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnThis, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContact, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(info_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVechilesHired, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		
		table = new JTable(this.tablemodel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		
	}
	
public CusDetPanel setJframe(JFrame jframe)
{
	this.jframe=jframe;
	this.jframe.addWindowListener(new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent arg0) {
			if(!RawDatas.windows.isEmpty()) {
				RawDatas.windows.peek().setVisible(true);
				RawDatas.windows.pop().setEnabled(true);
			}
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					jframe.dispose();
				}
					
			});
		}
		
		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	
	return this;
}
	
private void updateTable()
{
	this.tabledata=Serialization.fetchItemString(VDetailModel.class,f->cusModel.getVechiles_on_hire().contains(f.getVechile_id()));
	tablemodel.setData(VDetailModel.class,tabledata);
	tablemodel.fireTableDataChanged();
	
}
private void updateCount()
{

	if(cusModel.getVechiles_on_hire()!=null) {
	 cars=(int) cusModel.getVechiles_on_hire().stream().filter(t->t.startsWith(RawDatas.c2vc.get(CarModel.class.toString()))).count();
	 minibuses=(int) cusModel.getVechiles_on_hire().stream().filter(t->t.startsWith(RawDatas.c2vc.get(MbusModel.class.toString()))).count();
	 lorries=(int) cusModel.getVechiles_on_hire().stream().filter(t->t.startsWith(RawDatas.c2vc.get(LorryModel.class.toString()))).count();
	}
	textArea.setText(" \n Cars : "+cars+"\n\n Minibus : "+minibuses+"\n\n Lorry : "+lorries+"\n\n");
	
}
public  void updateCustomer(CusModel c)
{
this.cusModel=c;
updateTable();
updateCount();

}
private void fetchData()
	{
		RawDatas.print("Hired Vechile",cusModel.getVechiles_on_hire().toString());
		this.tabledata=Serialization.fetchItemString(VDetailModel.class,f->cusModel.getVechiles_on_hire().contains(f.getVechile_id()));
		RawDatas.print(tabledata.toString());
	    this.tablemodel= DataTableModel.getTableModel(VDetailModel.class,tabledata);
	}
	public JTable getTable()
	{
		return this.table;
	}

	public static void main(String[] args) {
		
		RawDatas.set_rawdatas();
	
		ArrayList<CusModel> cusModels=Serialization.fetchItems(CusModel.class);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame=new JFrame();
					frame.getContentPane().add(new CusDetPanel(cusModels.get(0)));
					frame.setSize(RawDatas.WIN_SIZE, RawDatas.WIN_SIZE);
					frame.setVisible(true);;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
