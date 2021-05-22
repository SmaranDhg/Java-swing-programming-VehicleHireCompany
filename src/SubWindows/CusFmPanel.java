package SubWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import DataModels.CusModel;
import DataModels.DataTableModel;
import DataModels.LDetailModel;
import Utilities.Serialization;
import WindowManagement.Customers;
import de.javasoft.plaf.synthetica.SyntheticaDefaultLookup;
import de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel;
import Utilities.RawDatas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.LookAndFeel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CusFmPanel extends JPanel {

	private JPanel contentPane;

	public  CusModel cusModel;
	private DataTableModel tablemodel;
	private ArrayList<String> tabledata;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JFrame jframe;
	private JButton cancel_btn,add_btn;
	private Customers parent;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		RawDatas.set_rawdatas();
		try {
			UIManager.setLookAndFeel("de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel");

		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<CusModel> cusModels=Serialization.fetchItems(CusModel.class);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame=new JFrame();
					frame.getContentPane().add(new CusFmPanel(frame,new Customers()));
					frame.setSize(RawDatas.WIN_SIZE, RawDatas.WIN_SIZE);
					frame.setVisible(true);;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public CusFmPanel(JFrame frame,Customers parent) {
		this.cusModel=new CusModel();
		this.jframe=frame;
		this.parent=parent;
		
		
		JLabel lblNewLabel = new JLabel(RawDatas.CDETAIL_1);
		JLabel lblName = new JLabel(RawDatas.CDETAIL_2);
		JLabel lblAddress = new JLabel(RawDatas.CDETAIL_3);
	    JLabel lblContact = new JLabel(RawDatas.CDETAIL_4);
		JLabel lblPassword_1 = new JLabel(RawDatas.LOGIN_DETAIL_2);
		add_btn = new JButton(RawDatas.CDETAIL_BTN_1);
		cancel_btn = new JButton(RawDatas.CDETAIL_BTN_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cusModel.set_id(textField.getText());
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cusModel.set_password(textField_1.getText());
			}
		});
		
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cusModel.set_name(textField_2.getText());
			}
		});
		
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cusModel.set_address(textField_3.getText());
			}
		});
		
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cusModel.set_contact(textField_3.getText());
				
			}
			
		});
		
		
		JLabel lblNewLabel_1 = new JLabel(RawDatas.CDETAIL_TTL);
		lblNewLabel_1.setFont(RawDatas.FONT_TITLE);
		
		
		add_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cusModel.set_password(textField_1.getText());
				cusModel.set_id(textField.getText());
				cusModel.set_name(textField_2.getText());
				cusModel.set_address(textField_3.getText());
				cusModel.set_contact(textField_3.getText());
				if(cusModel.get_id().isBlank() || cusModel.get_password().isBlank())
				{
					JOptionPane.showMessageDialog(new JFrame(), "Username and Password shouldn't be empty","Error while saving",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					RawDatas.print(cusModel.get_address());
					Serialization.save(CusModel.class,cusModel);
					Serialization.save(LDetailModel.class,new LDetailModel(cusModel.get_id(),cusModel.get_password(),RawDatas.LOGIN_ID.CUSTOMER));
					
					EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						parent.updateTable();
						JOptionPane.showMessageDialog(new JFrame(), "Customer Data Saved Sucessfully,Thank you!","Success",JOptionPane.INFORMATION_MESSAGE);
						if(!RawDatas.windows.isEmpty()) {
							RawDatas.windows.peek().setVisible(true);
							RawDatas.windows.pop().setEnabled(true);
						}
						jframe.dispose();
					}
				});
					
					
				}
					
			}
		});
		
		
	    frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			
			@Override
			public void windowClosed(WindowEvent e) {
				cancel_btn.doClick();
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				cancel_btn.doClick();
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cancel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!RawDatas.windows.isEmpty()) {
					RawDatas.windows.peek().setVisible(true);
					RawDatas.windows.pop().setEnabled(true);
				}
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						frame.dispose();
					}
						
				});
				
			}
		});
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
										.addComponent(lblPassword_1, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
										.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
										.addComponent(lblAddress, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
										.addComponent(lblContact, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
									.addGap(52)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
										.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(add_btn)
									.addPreferredGap(ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
									.addComponent(cancel_btn)))
							.addGap(96))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)))
					.addGap(29))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContact, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(add_btn)
						.addComponent(cancel_btn))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		


	}
}
