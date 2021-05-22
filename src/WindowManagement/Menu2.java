package WindowManagement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Utilities.Serialization;
import Utilities.RawDatas;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import DataModels.CusModel;
import DataModels.StaffModel;
import SubWindows.StfDetPanel;

import javax.swing.JSeparator;

public class Menu2 extends JFrame {

	/**
	 * Create the panel.
	 */
	private JPanel panel;
	private StaffModel s;
	private StfDetPanel panel_1;
	private JFrame frame;
	public Menu2(StaffModel s) {
		super(RawDatas.COMPANY_NAME);
		this.frame=this;
		this.s=s;
		setSize(RawDatas.WIN_SIZE,RawDatas.WIN_SIZE);
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		
		panel_1=new StfDetPanel(s);
		
		JButton btn1 = new JButton(RawDatas.MENU_STAFF_1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RawDatas.windows.push(frame);
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						new VecManage().setVisible(true);
					}});
			}
		});
		
		JButton btn2 = new JButton(RawDatas.MENU_STAFF_2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RawDatas.windows.push(frame);
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new Customers().setVisible(true);
						
					}});
			}
		});
		
		JButton btn3 = new JButton(RawDatas.MENU_STAFF_3);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RawDatas.windows.push(frame);
				setVisible(false);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new Loan().setVisible(true);
						
					}});
			}
		});
		JButton btn4 = new JButton(RawDatas.MENU_STAFF_4);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RawDatas.windows.push(frame);
				setVisible(false);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new Return().setVisible(true);
						
					}});
			}
		});
		
		JButton btn5 = new JButton(RawDatas.MENU_STAFF_5);
		btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						dispose();
					}});
			}
		});
		
		JLabel lblNewLabel = new JLabel(RawDatas.COMPANY_NAME);
		lblNewLabel.setFont(RawDatas.FONT_TITLE);
		
		JLabel lblWelcomeMr = new JLabel(RawDatas.MAIN_MENU_GREETING+s.get_name());
		
		JLabel lblNewLabel_1 = new JLabel("Employee Menu");
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblWelcomeMr, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
							.addGap(100)))
					.addGap(151)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
					.addGap(106))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn2, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
						.addComponent(btn1, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
						.addComponent(btn5, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
						.addComponent(btn4, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
						.addComponent(btn3, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
					.addGap(61)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
					.addGap(49))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(lblWelcomeMr, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(21)
							.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(btn2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 152, Short.MAX_VALUE))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
//		pack();
//		setLocationRelativeTo(null);

	}
	
	private void fill_title()
	{
		
	}
	
	
public static void main(String[] args) {
		
		RawDatas.set_rawdatas();
	
		ArrayList<StaffModel> customers=Serialization.fetchItems(StaffModel.class);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				new Menu2(customers.get(0)).setVisible(true);;
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
