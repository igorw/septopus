package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The window for the trainer
 */
public class TrainerView extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton btNext;
	private JLabel lbCorrection;
	private JTextField tfForeign;
	private JLabel lbHome;

	/**
	 * Initialise the GUI
	 */
	public void initGUI()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Trainer");
		setSize(300, 150);
		setResizable(false);
		
		panel = new JPanel();
		setContentPane(panel);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		getContentPane().setLayout(layout);
		
		lbHome = new JLabel();
		
		tfForeign = new JTextField();
		
		btNext = new JButton("Next");

		lbCorrection = new JLabel();
		lbCorrection.setForeground(Color.red);
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lbHome)
					.addComponent(lbCorrection)
					.addComponent(tfForeign)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(btNext, GroupLayout.Alignment.TRAILING)
				)
		);
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(lbHome)
				.addComponent(lbCorrection)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(tfForeign)
					.addComponent(btNext)
				)
		);

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
	
	public void requestForeignFocus()
	{
		tfForeign.requestFocus();
	}
	
	public void addNextActionListener(ActionListener l)
	{
		btNext.addActionListener(l);
	}
	
	public void addForeignKeyListener(KeyListener l)
	{
		tfForeign.addKeyListener(l);
	}
}
