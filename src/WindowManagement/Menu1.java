package WindowManagement;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataModels.CusModel;

import SubWindows.CusDetPanel;
import SubWindows.VecDetPanel;
import Utilities.Serialization;

import Utilities.RawDatas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Menu1 extends JFrame {

	private JPanel contentPane;
	private CusModel c=null;
	private JFrame frame;
	private JTable table;
	private CusDetPanel sidePanel;
	private JButton btn3,btn4,btn5;

	private JPanel panel;
	public Menu1(CusModel c) {
		
		super(RawDatas.COMPANY_NAME);
		this.c=c;
		this.frame=this;
		setSize(RawDatas.WIN_SIZE,RawDatas.WIN_SIZE);
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		
		sidePanel = new CusDetPanel(this.c);
		this.table =sidePanel.getTable();
		 this.table.setRowSelectionAllowed(true);
	     this.table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(table.getSelectedRowCount()==0)
					btn4.setEnabled(false);
				else
					btn4.setEnabled(true);
				
			}
		});
		
		btn3 = new JButton(RawDatas.MENU_CUSTOMER_1);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RawDatas.windows.push(frame);
				setVisible(false);
				new Loan().setCId(c.get_id()).setParent(frame).setVisible(true);
			}
		});
		
		btn4 = new JButton(RawDatas.MENU_CUSTOMER_2);
		btn4.setEnabled(false);
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RawDatas.windows.push(frame);
				frame.setEnabled(false);
				JFrame jf=RawDatas.getJFrame();
				jf.setSize((int)(RawDatas.WIN_SIZE*.5), (int)(RawDatas.WIN_SIZE*.6));
		        jf.getContentPane().add(new VecDetPanel(jf, (String)table.getValueAt(table.getSelectedRow(), 0)));
		       
				
			}
		});
		
		 btn5 = new JButton(RawDatas.MENU_CUSTOMER_3);
		btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						dispose();
					}
						
				});
				
			}
		});
		
		JLabel lblNewLabel = new JLabel(RawDatas.COMPANY_NAME);
		lblNewLabel.setFont(RawDatas.FONT_TITLE);
		
		JLabel lblWelcomeMr = new JLabel(RawDatas.MAIN_MENU_GREETING+c.get_name());
		
		JLabel lblNewLabel_1 = new JLabel("Customer Menu");
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
							.addGap(85)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblWelcomeMr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
								.addComponent(btn4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(29)
							.addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblWelcomeMr, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(21)
							.addComponent(sidePanel, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE)))
					.addGap(49))
		);
		panel.setLayout(gl_panel);
		
//		pack();
//		setLocationRelativeTo(null);

	}
	
	
public static void main(String[] args) {
		
		RawDatas.set_rawdatas();
	
		ArrayList<CusModel> cusModels=Serialization.fetchItems(CusModel.class);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				new Menu1(cusModels.get(0)).setVisible(true);;
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


public void updateCustomer() {
	
	CusModel c=Serialization.fetchItems(CusModel.class, v->v.get_id().equals(this.c.get_id())).get(0);
	sidePanel.updateCustomer(c);
}
}
