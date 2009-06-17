package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class TrainerView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton btNext;
	private JLabel lbCorrection;
	private JTextField tfForeign;
	private JLabel lbHome;

	public void initGUI() {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Trainer");
		setSize(300, 150);
		setResizable(false);
		
		{
			panel = new JPanel();
			setContentPane(panel);
			panel.setLayout(null);
			{
				lbHome = new JLabel();
				panel.add(lbHome);
				lbHome.setBounds(35, 10, 207, 28);
			}
			{
				tfForeign = new JTextField();
				panel.add(tfForeign);
				tfForeign.setBounds(35, 76, 131, 18);
			}
			{
				btNext = new JButton();
				panel.add(btNext);
				btNext.setText("Next");
				btNext.setBounds(186, 75, 78, 21);
			}
			{
				lbCorrection = new JLabel();
				panel.add(lbCorrection);
				lbCorrection.setBounds(35, 44, 207, 21);
				lbCorrection.setForeground(Color.red);
			}
		}

		setVisible(true);
	}
	
	public String getHome()
	{
		return lbHome.getText();
	}
	
	public void setHome(String home)
	{
		lbHome.setText(home);
	}
	
	public String getForeign()
	{
		return tfForeign.getText();
	}
	
	public void setForeign(String foreign)
	{
		tfForeign.setText(foreign);
	}
	
	public void setCorrection(String correction)
	{
		lbCorrection.setText(correction);
	}
	
	public void addNextActionListener(ActionListener l)
	{
		btNext.addActionListener(l);
	}
}
