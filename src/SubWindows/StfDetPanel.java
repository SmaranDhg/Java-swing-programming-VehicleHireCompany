package SubWindows;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import DataModels.CusModel;
import DataModels.DataTableModel;
import DataModels.StaffModel;
import DataModels.VDetailModel;
import Utilities.Serialization;
import Utilities.RawDatas;

public class StfDetPanel extends JPanel {


	public  StaffModel staffModel;
	

	/**
	 * Create the frame.
	 */
	public StfDetPanel(StaffModel f) {
		
		this.staffModel=f;
		
		JLabel lblNewLabel = new JLabel(RawDatas.SDETAIL_1);
		
		JLabel info = new JLabel();
		info.setText(f.get_id());
		
		JLabel lblName = new JLabel(RawDatas.SDETAIL_2);
		
		JLabel info_1 = new JLabel();
		info_1.setText(f.get_title());
		
		JLabel lblAddress = new JLabel(RawDatas.SDETAIL_3);
		
		JLabel lblContact = new JLabel(RawDatas.SDETAIL_4);
		
		JLabel info_1_1 = new JLabel();
		info_1_1.setText(f.get_name());
		
//		JTextPane txtpnThis = new JTextPane();
//		txtpnThis.setText(f.get_address());
//		txtpnThis.setFont(MetaDatas.FONT_LABEL);
//		txtpnThis.setBackground(UIManager.getColor("Button.background"));
//		txtpnThis.setEditable(false);
		
		JLabel info_3_1 = new JLabel();
		info_3_1.setText(f.get_contact());
		

	
		JLabel lblContact_1 = new JLabel(RawDatas.SDETAIL_5);
		
;
		
		JTextPane txtpnThis = new JTextPane();
		txtpnThis.setText(f.get_address().replace(",", "\n"));
		txtpnThis.setFont(RawDatas.FONT_LABEL);
		txtpnThis.setEditable(false);
		txtpnThis.setBackground(UIManager.getColor("Button.background"));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblContact, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContact_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(info_3_1, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtpnThis, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(info_1_1, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(info_1, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(info, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
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
						.addComponent(info_1_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblContact, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(27))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtpnThis, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContact_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(info_3_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(177, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	



	public static void main(String[] args) {
		
		RawDatas.set_rawdatas();
	
		ArrayList<StaffModel> customers=Serialization.fetchItems(StaffModel.class);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame=new JFrame();
					frame.getContentPane().add(new StfDetPanel(customers.get(0)));
					frame.setSize(RawDatas.WIN_SIZE, RawDatas.WIN_SIZE);
					frame.setVisible(true);;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
