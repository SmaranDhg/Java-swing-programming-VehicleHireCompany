package WindowManagement;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Utilities.RawDatas;
public class Main {

	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel");

		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	RawDatas.set_rawdatas();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame a=new JFrame();
//				a.setLayout(new GridBagLayout());
				a.add(new SignIn());
				a.pack();
				a.setLocationRelativeTo(null);
				a.setSize(RawDatas.WIN_SIZE, RawDatas.WIN_SIZE);
				a.setVisible(true);
			
				
			}
		});
		
		}
}
 

