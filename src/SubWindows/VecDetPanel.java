package SubWindows;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import DataModels.CarModel;
import DataModels.LorryModel;
import DataModels.MbusModel;
import DataModels.StaffModel;
import Utilities.Serialization;

import Utilities.RawDatas;

import javax.swing.JButton;

public class VecDetPanel extends JPanel {
private String vtype;
private JFrame parent;

	public  VecDetPanel(JFrame parent,String vechile_id)
	{
		this.parent=parent;
System.out.println(vechile_id);
		
		JLabel lblNewLabel = new JLabel(RawDatas.VDETAIL_1);
		JLabel info = new JLabel();
		JLabel lblName = new JLabel(RawDatas.VDETAIL_2);
		JLabel info_1 = new JLabel();
		JLabel lblAddress = new JLabel(RawDatas.VDETAIL_3);
		JLabel lblContact = new JLabel(RawDatas.VDETAIL_4C1);
		JLabel info_1_1 = new JLabel();
		
		

		JLabel info_3_1 = new JLabel();
        JLabel lblContact_1 = new JLabel(RawDatas.VDETAIL_4C2);
		

		
		JTextPane txtpnThis = new JTextPane();
		txtpnThis.setFont(RawDatas.FONT_LABEL);
		txtpnThis.setEditable(false);
		txtpnThis.setBackground(UIManager.getColor("Button.background"));
		
		JLabel lblContact_1_1 = new JLabel(RawDatas.VDETAIL_5);
		JLabel info_3_1_1 = new JLabel();
		
		
		JLabel lblContact_1_1_1 = new JLabel(RawDatas.VDETAIL_6);
		JLabel info_3_1_2 = new JLabel();
		
		
		JLabel lblContact_1_1_1_1 = new JLabel(RawDatas.VDETAIL_7);
		JLabel info_3_1_3 = new JLabel();
		
		

		
		
		if(vechile_id.startsWith(RawDatas.c2vc.get(CarModel.class.toString()))) //car
		{
			this.vtype=RawDatas.VECHILE_TYPE[0];
		    CarModel carModel=Serialization.fetchItems(CarModel.class,m->m.getVechile_id().equals(vechile_id)).get(0);
			info_3_1.setVisible(true);
			lblContact_1.setVisible(true);
			txtpnThis.setText(carModel.getFule_type());
			info.setText(carModel.getVechile_id());
			info_1.setText(carModel.getMake());
			info_1_1.setText(carModel.getModel());
			info_3_1.setText(carModel.getNo_of_doors());
			info_3_1_1.setText(carModel.getTop_speed());
			info_3_1_2.setText(carModel.getDaily_hire_rate());
			info_3_1_3.setText(carModel.getRegistration_no());
		   
		}else if(vechile_id.startsWith(RawDatas.c2vc.get(MbusModel.class.toString())))//minibus
		{
			vtype=RawDatas.VECHILE_TYPE[1];
			MbusModel minibus=Serialization.fetchItems(MbusModel.class,m->m.getVechile_id().equals(vechile_id)).get(0);
			info_3_1.setVisible(false);
			lblContact_1.setVisible(false);
			lblContact.setText(RawDatas.VDETAIL_4M1);
			info.setText(minibus.getVechile_id());
			info_1.setText(minibus.getMake());
			info_1_1.setText(minibus.getModel());
			txtpnThis.setText(minibus.getMax_seating_capacity());
			info_3_1_1.setText(minibus.getTop_speed());
			info_3_1_2.setText(minibus.getDaily_hire_rate());
			info_3_1_3.setText(minibus.getRegistration_no());
		
		}	
		else {//lorry
			vtype=RawDatas.VECHILE_TYPE[2];
			LorryModel lorryModel=Serialization.fetchItems(LorryModel.class,m->m.getVechile_id().equals(vechile_id)).get(0);
			info_3_1.setVisible(false);
			lblContact_1.setVisible(false);
			lblContact.setText(RawDatas.VDETAIL_4L1);
			info.setText(lorryModel.getVechile_id());
			info_1.setText(lorryModel.getMake());
			info_1_1.setText(lorryModel.getModel());
			txtpnThis.setText(lorryModel.getMax_load_capacity());
			info_3_1_1.setText(lorryModel.getTop_speed());
			info_3_1_2.setText(lorryModel.getDaily_hire_rate());
			info_3_1_3.setText(lorryModel.getRegistration_no());
		 }
		
		
		JLabel lblNewLabel_1 = new JLabel(vtype+RawDatas.VDETAIL_TTL);
		lblNewLabel_1.setFont(RawDatas.FONT_TITLE);
		
		JButton cancel_btn = new JButton(RawDatas.VDETAIL_BTN_1);
		cancel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!RawDatas.windows.isEmpty()) {
					RawDatas.windows.peek().setVisible(true);
					RawDatas.windows.pop().setEnabled(true);
				}
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						parent.dispose();
					}
						
				});
			}
		});
		
		parent.addWindowListener(new WindowListener() {
			
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
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cancel_btn)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
											.addComponent(lblName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
											.addComponent(lblAddress, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
											.addComponent(lblContact, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
										.addGap(64))
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblContact_1, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
											.addComponent(lblContact_1_1, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
											.addComponent(lblContact_1_1_1, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblContact_1_1_1_1, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(info_3_1_2, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
								.addComponent(info_1_1, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
								.addComponent(info_1, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
								.addComponent(info, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
								.addComponent(txtpnThis, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(info_3_1, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(info_3_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(info_3_1_3, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)))))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(info, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(info_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(info_1_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblContact, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpnThis, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(info_3_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(273)
							.addComponent(lblContact_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(info_3_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblContact_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblContact_1_1_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblContact_1_1_1_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(info_3_1_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(info_3_1_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addGap(45)
					.addComponent(cancel_btn)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	
	
	



	public static void main(String[] args) {
		
		RawDatas.set_rawdatas();
	
		ArrayList<MbusModel> customers=Serialization.fetchItems(MbusModel.class);
		RawDatas.print(customers.get(0).getVechile_id());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame=new JFrame();
					frame.getContentPane().add(new VecDetPanel(frame,customers.get(0).getVechile_id()));
					frame.setSize(RawDatas.WIN_SIZE, RawDatas.WIN_SIZE);
					frame.setVisible(true);;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
