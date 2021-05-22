package WindowManagement;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import DataModels.CusModel;
import DataModels.LDetailModel;
import DataModels.StaffModel;
import Utilities.Serialization;
import Utilities.RawDatas;
import Utilities.RawDatas.LOGIN_ID;

public class SignIn extends JPanel {
	private JLabel lblLogIn;
	private JPasswordField psword;
	private HashMap<String, LDetailModel> logindetails=null;

	/**
	 * Create the panel.
	 */
	public SignIn() {
		fetchData();
		
		
		JLabel lblNewLabel = new JLabel(RawDatas.COMPANY_NAME);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		
		lblLogIn = new JLabel(RawDatas.SN_LBL_1);
		lblLogIn.setFont(new Font("Dialog", Font.BOLD, 20));
		
		psword = new JPasswordField();
		psword.setToolTipText("password");
		
		JTextPane txtusrnm = new JTextPane();
		txtusrnm.setToolTipText("customerID");
		
		JRadioButton asCustomer = new JRadioButton("As Customer");
		asCustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(asCustomer.isSelected()) {
				RawDatas.LOGIN_AS=RawDatas.LOGIN_ID.CUSTOMER; // updating  the enum here
				System.out.println("As Customer");
				}
				else RawDatas.LOGIN_AS=RawDatas.LOGIN_ID.STAFF;
				
			}
			
		});
		
		JLabel lblUsername = new JLabel(RawDatas.SN_LBL_2);
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblUsername_1 = new JLabel(RawDatas.SDETAIL_3);
		lblUsername_1.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JButton btnLogin = new JButton(RawDatas.SN_BTN_1);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=txtusrnm.getText();
				String password=new String(psword.getPassword());
				
		         System.out.println(username+psword);
				if(logindetails.containsKey(username) && logindetails.get(username).getPassword().equals(password))
				{
					
					if(RawDatas.LOGIN_AS.equals(RawDatas.LOGIN_ID.CUSTOMER)) {
						try {
						CusModel c=Serialization.fetchItems(CusModel.class, cus->cus.get_id().equals(username)).get(0);
						new Menu1(c).setVisible(true);
						}catch(Exception ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Username not in Customer list, try Signing as Staff","Login Error",JOptionPane.ERROR_MESSAGE);
						}
						}	
					else {
						try {
							StaffModel s=Serialization.fetchItems(StaffModel.class, cus->cus.get_id().equals(username)).get(0);
							new Menu2(s).setVisible(true);
							}catch(Exception ex)
							{
								JOptionPane.showMessageDialog(new JFrame(), "Username not in Staff list, try Signing as Customer","Login Error",JOptionPane.ERROR_MESSAGE);
							}
						
					}
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Username and Password do not match","Login Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(147)
							.addComponent(lblLogIn, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(147)
							.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
							.addGap(38)
							.addComponent(txtusrnm, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
							.addGap(257))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(147)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername_1, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(asCustomer, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
									.addGap(2)))
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
									.addGap(86)
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
								.addComponent(psword, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
							.addGap(257)))
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(49)
					.addComponent(lblLogIn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtusrnm, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsername_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(psword, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(asCustomer, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))))
		);
		setLayout(groupLayout);

	}
	
	
	private void fetchData()
	{
		logindetails=(HashMap<String, LDetailModel>) Serialization.fetchItemMap(LDetailModel.class, ld->ld.getUsername());
	}


}
