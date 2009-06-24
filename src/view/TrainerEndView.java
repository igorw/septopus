package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TrainerEndView extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JButton btRetry = new JButton("Retry");
	private JButton btSave = new JButton("Save");
	private JButton btCancel = new JButton("Cancel");
	
	public void initGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("End of session");
		setSize(400, 200);

		setContentPane(new JPanel());
		getContentPane().setLayout(new GridLayout(1, 3, 5, 5));

		getContentPane().add(btRetry);
		getContentPane().add(btSave);
		getContentPane().add(btCancel);
		
		setVisible(true);
	}
	
	public void addRetryActionListener(ActionListener l)
	{
		btRetry.addActionListener(l);
	}
	
	public void addSaveActionListener(ActionListener l)
	{
		btSave.addActionListener(l);
	}
	
	public void addCancelActionListener(ActionListener l)
	{
		btCancel.addActionListener(l);
	}
	
	public static void main(String[] args) {
		TrainerEndView v = new TrainerEndView();
		v.initGUI();
	}
}
