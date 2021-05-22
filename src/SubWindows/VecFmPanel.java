package SubWindows;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import DataModels.CarModel;
import DataModels.CusModel;
import DataModels.LorryModel;
import DataModels.MbusModel;
import DataModels.VDetailModel;
import Utilities.Serialization;
import WindowManagement.VecManage;
import Utilities.RawDatas;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VecFmPanel extends JPanel {

	private String vType=RawDatas.VECHILE_TYPE[0];
	private JLabel lblNewLabel_2 = new JLabel("Type");
	
	
	private JLabel lblNewLabel = new JLabel(RawDatas.VDETAIL_1);
	private JLabel lblName = new JLabel(RawDatas.VDETAIL_2);
	
	private JLabel lblAddress = new JLabel(RawDatas.VDETAIL_3);
	private JLabel lblContact = new JLabel(RawDatas.VDETAIL_4C1);
	private JLabel lblContact_1 = new JLabel(RawDatas.VDETAIL_4C2);
	private JLabel lblContact_1_1 = new JLabel(RawDatas.VDETAIL_5);
	private JLabel lblContact_1_1_1 = new JLabel(RawDatas.VDETAIL_6);
	private JLabel lblContact_1_1_1_1 = new JLabel(RawDatas.VDETAIL_7);
	
	
	private JTextField info = new JTextField();
	private JTextField info_1 = new JTextField();
	private JTextField info_3_1 = new JTextField();
	private JTextField info_1_1 = new JTextField();
	private JTextField txtpnThis = new JTextField();
	private JTextField info_3_1_1 = new JTextField();
	private JTextField info_3_1_2 = new JTextField();
	private JTextField info_3_1_3 = new JTextField();
	private JComboBox comboBox;
	private CarModel carModel;
	private LorryModel lorryModel;
	private MbusModel minibus;
	private JButton add_btn,cancel_btn;
	private JFrame jframe;
	private  VecManage parent;
	
	private String prop1="00",prop2="00";//unique properties of vehicle
	/**
	 * Create the frame.
	 */
	public  VecFmPanel(VecManage parent) {
	    this.jframe=parent;
	    this.carModel=new CarModel();
		this.lorryModel=new LorryModel();
		this.minibus=new MbusModel();
		
		
		JLabel lblNewLabel_1 = new JLabel(RawDatas.VFORM_TTL);
		lblNewLabel_1.setFont(RawDatas.FONT_TITLE);
		
		 comboBox = new JComboBox();
		 for(String s:RawDatas.VECHILE_TYPE)
			 comboBox.addItem(s);
		 
		 info.setEditable(false);
		 info.setEnabled(false);
		 comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vType=(String) comboBox.getSelectedItem();
				updateWindow();
			}
		});
		 
		 
		 txtpnThis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prop1=txtpnThis.getText();
				if(vType==RawDatas.VECHILE_TYPE[0]) //car
				  info.setText(RawDatas.gtVId(CarModel.class,prop1,prop2));
					else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
						info.setText(RawDatas.gtVId(MbusModel.class,prop1,prop2));	
				else info.setText(RawDatas.gtVId(LorryModel.class,prop1,prop2));
			}
		});
		 info_3_1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					prop2=txtpnThis.getText();
					if(vType==RawDatas.VECHILE_TYPE[0]) //car
					  info.setText(RawDatas.gtVId(CarModel.class,prop1,prop2));
						else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
							info.setText(RawDatas.gtVId(MbusModel.class,prop1,prop2));	
					else info.setText(RawDatas.gtVId(LorryModel.class,prop1,prop2));
				}
			});
		 
	    
	   	JButton add_btn = new JButton(RawDatas.VFORM_BTN_1);
	   	add_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prop1=txtpnThis.getText();
				prop2=txtpnThis.getText();
				
				
				if(vType==RawDatas.VECHILE_TYPE[0]) //car
				  info.setText(RawDatas.gtVId(CarModel.class,prop1,prop2));
					else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
						info.setText(RawDatas.gtVId(MbusModel.class,prop1,prop2));	
				else info.setText(RawDatas.gtVId(LorryModel.class,prop1,prop2));
				System.out.println("here");
				updateValue();
				String data="";
				try {
					
					if(vType==RawDatas.VECHILE_TYPE[0]) //car
				        data=RawDatas.serializeFrom(CarModel.class,carModel);
				   else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
				        data=RawDatas.serializeFrom(MbusModel.class,minibus);
				   else //lorry
					    data=RawDatas.serializeFrom(LorryModel.class,lorryModel);
				 
				
				System.out.println(data);
				if(data.contains(";;")||data.isBlank())
				{
					JOptionPane.showMessageDialog(new JFrame(), "Some data is missing","Empty Field",JOptionPane.ERROR_MESSAGE);
				}
				
				else { 
				int confirmation=JOptionPane.showConfirmDialog(null, "Do you want to add new vehicle?");
				if(confirmation==0) {
					if(vType==RawDatas.VECHILE_TYPE[0]) //car
					{
					   Serialization.save(CarModel.class,carModel);
					   Serialization.save(VDetailModel.class,new VDetailModel(carModel.getVechile_id(),RawDatas.VECHILE_TYPE[0],RawDatas.STATUS_AVAILABLE));
				
					}else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
					{
						Serialization.save(MbusModel.class,minibus);
						   Serialization.save(VDetailModel.class,new VDetailModel(minibus.getVechile_id(),RawDatas.VECHILE_TYPE[0],RawDatas.STATUS_AVAILABLE));
					
					}	
					else {//lorry
						Serialization.save(LorryModel.class,lorryModel);
						   Serialization.save(VDetailModel.class, new VDetailModel(lorryModel.getVechile_id(),RawDatas.VECHILE_TYPE[0],RawDatas.STATUS_AVAILABLE));
					
					 }
					
					EventQueue.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							parent.updateTable();
							JOptionPane.showMessageDialog(new JFrame(), "Vechile Details  Saved Sucessfully,Thank you!","Success",JOptionPane.INFORMATION_MESSAGE);
							jframe.dispose();
						}
					});
				}
					
			}
			}catch (Exception e1) {
					   System.err.println("Error at Add Button fleetmanagement\n\n"+e);
				}
				
			}
				
		});
	   	
		JButton cancel_btn = new JButton(RawDatas.VFORM_BTN_2);
	    jframe.addWindowListener(new WindowListener() {
			
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
							jframe.dispose();
						}
							
					});
					
				}
			});
	
		
	
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(add_btn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(cancel_btn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(194, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
									.addGap(14))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblContact_1_1_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addComponent(lblContact_1_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addComponent(lblContact_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addComponent(lblContact_1, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
											.addGap(64))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblContact, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addComponent(lblName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addComponent(lblAddress, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
											.addGap(64)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(info_3_1_3, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addComponent(info_3_1_1, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addComponent(info_3_1, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addComponent(info_3_1_2, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addComponent(txtpnThis, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addComponent(info_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addComponent(info_1, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addComponent(comboBox, 0, 237, Short.MAX_VALUE)
										.addComponent(info, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
									.addGap(18)))
							.addGap(0))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(info, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(info_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(info_1_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnThis, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContact, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContact_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(info_3_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContact_1_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(info_3_1_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(info_3_1_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContact_1_1_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContact_1_1_1_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(info_3_1_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cancel_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(add_btn, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		
	     updateWindow();
		setLayout(groupLayout);
		
	}
	
private void updateWindow()
{
	
	
	if(vType==RawDatas.VECHILE_TYPE[0]) //car
	{
		info_3_1.setVisible(true);
		lblContact_1.setVisible(true);
	
	   
	}else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
	{
		info_3_1.setVisible(false);
		lblContact_1.setVisible(false);
	
	
	}	
	else {//lorry
		info_3_1.setVisible(false);
		lblContact_1.setVisible(false);
	
	 }
	
	
}

private void updateValue()
{

	if(vType==RawDatas.VECHILE_TYPE[0]) //car
	{

		carModel.setFule_type(txtpnThis.getText());
		carModel.setVechile_id(info.getText());
		carModel.setMake(info_1.getText());
		carModel.setModel(info_1_1.getText());
		carModel.setNo_of_doors(info_3_1.getText());
		carModel.setTop_speed(info_3_1_1.getText());
		carModel.setDaily_hire_rate(info_3_1_2.getText());
		carModel.setRegistration_no(info_3_1_3.getText());
	   
	}else if(vType==RawDatas.VECHILE_TYPE[1])//minibus
	{
		
		lblContact.setText(RawDatas.VDETAIL_4M1);
		minibus.setVechile_id(info.getText());
		minibus.setMake(info_1.getText());
		minibus.setModel(info_1_1.getText());
		minibus.setMax_seating_capacity(txtpnThis.getText());
		minibus.setTop_speed(info_3_1_1.getText());
		minibus.setDaily_hire_rate(info_3_1_2.getText());
		minibus.setRegistration_no(info_3_1_3.getText());
	
	}	
	else {//lorry
		
		lblContact.setText(RawDatas.VDETAIL_4L1);
		lorryModel.setVechile_id(info.getText());
		lorryModel.setMake(info_1.getText());
		lorryModel.setModel(info_1_1.getText());
		lorryModel.setMax_load_capacity(txtpnThis.getText());
		lorryModel.setTop_speed(info_3_1_1.getText());
		lorryModel.setDaily_hire_rate(info_3_1_2.getText());
		lorryModel.setRegistration_no(info_3_1_3.getText());
	 }
	
}


	public static void main(String[] args) {
		
		RawDatas.set_rawdatas();
	
		ArrayList<MbusModel> customers=Serialization.fetchItems(MbusModel.class);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame=new JFrame();
					frame.getContentPane().add(new VecFmPanel(new VecManage()));
					frame.setSize(RawDatas.WIN_SIZE, RawDatas.WIN_SIZE);
					frame.setVisible(true);;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
