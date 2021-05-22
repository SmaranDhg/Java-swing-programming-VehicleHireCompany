package WindowManagement;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataModels.CarModel;
import DataModels.LorryModel;
import DataModels.MbusModel;
import SubWindows.FltDetPanel;
import SubWindows.VecDetPanel;
import SubWindows.VecFmPanel;
import Utilities.Serialization;
import Utilities.RawDatas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class VecManage extends JFrame {

	private JPanel contentPane;
	private FltDetPanel panel_1;
	private JTable table;
	private JButton btn3,btn4,btn5,btn6;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		RawDatas.set_rawdatas();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VecManage frame = new VecManage();
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
	public VecManage() {
		this.frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(RawDatas.WIN_SIZE,RawDatas.WIN_SIZE);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel_1 = new FltDetPanel();
		this.table=panel_1.getTable();
		table.setRowSelectionAllowed(true);
	    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					if(table.getSelectedRowCount()==0) {
						btn4.setEnabled(false);
						btn3.setEnabled(false);
						
							
					}
					else {
						btn4.setEnabled(true);
						btn3.setEnabled(true);
						
					}
						
						
				}});
		
		
		JLabel lblNewLabel = new JLabel(RawDatas.FLEET_MANAGE_TTL);
		lblNewLabel.setFont(new Font(RawDatas.TITLE_FONT,RawDatas.TITLE_FONT_STYLE,RawDatas.TITLE_FONT_SIZE));
	
		
		 btn3 = new JButton(RawDatas.FLEET_MANAGE_BTN_1);
		 btn3.setEnabled(false);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RawDatas.windows.push(frame);
				frame.setEnabled(false);
				JFrame jf=RawDatas.getJFrame();
				jf.setSize((int)(RawDatas.WIN_SIZE*.55), (int)(RawDatas.WIN_SIZE*.6));
		        jf.getContentPane().add(new VecDetPanel(jf, (String)table.getValueAt(table.getSelectedRow(), 0)));
			}
		});
		
		 btn4 = new JButton(RawDatas.FLEET_MANAGE_BTN_2);
		btn4.setEnabled(false);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> selectVehicle=new ArrayList<>();
			     for(int i : table.getSelectedRows()) 
			    	    selectVehicle.add((String) table.getValueAt(i, 0));
			     String vType=panel_1.getVtype();
			     
			     int confirmation=JOptionPane.showConfirmDialog(null, "Do you want to remove "+selectVehicle.size()+" "+vType+" from your fleet?");
			       
			     
			     if(confirmation==0) {
			   if(vType==RawDatas.VECHILE_TYPE[0]) //car
			   {
				  Serialization.update(CarModel.class ,panel_1.getData().stream()
						  .filter(c->!selectVehicle.contains(RawDatas.deserilizeTo(CarModel.class, c).getVechile_id()))
						  .collect(Collectors.toCollection(ArrayList::new)));
			      
			   }else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
			   {
				   Serialization.update(MbusModel.class ,panel_1.getData().stream()
							  .filter(c->!selectVehicle.contains(RawDatas.deserilizeTo(MbusModel.class, c).getVechile_id()))
							  .collect(Collectors.toCollection(ArrayList::new)));
			   	
			   }	
			   else {//lorry
				   Serialization.update(LorryModel.class ,panel_1.getData().stream()
							  .filter(c->!selectVehicle.contains(RawDatas.deserilizeTo(LorryModel.class, c).getVechile_id()))
							  .collect(Collectors.toCollection(ArrayList::new)));
			    }
			   panel_1.updateData(null);
			   panel_1.updateTable();
			   JOptionPane.showMessageDialog(new JFrame(), selectVehicle.size()+" "+vType+" removed from your fleet.","Success!",JOptionPane.INFORMATION_MESSAGE);
			     }

				
			}
		});
		
		 btn5 = new JButton(RawDatas.FLEET_MANAGE_BTN_3);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RawDatas.windows.push(frame);
				frame.setEnabled(false);
				JFrame jf=RawDatas.getJFrame();
				jf.setSize((int)(RawDatas.WIN_SIZE*.45), (int)(RawDatas.WIN_SIZE*.8));
		        jf.getContentPane().add(new VecFmPanel((VecManage) frame));
		        
			}
		});
		
		 
		
		 btn6 = new JButton(RawDatas.FLEET_MANAGE_BTN_4);
		btn6.addActionListener(new ActionListener() {
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
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(33)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
							.addGap(532))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(69)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btn6, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
							.addGap(38)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(29))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(101)
							.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btn6, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)))
					.addGap(0, 0, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
	}
	
	public void updateTable()
	{
		panel_1.updateData(null);
	    panel_1.updateTable();
	}
}
