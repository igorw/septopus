package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class AboutView extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JLabel lbLogo;
	private JLabel lbTitle;
	private JLabel lbText;
	private JButton btOkay;

	public void initGUI()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setIconImage(new ImageIcon("septopus.png").getImage());
		setResizable(false);
		
		setTitle("About Septopus");
		setSize(341, 240);
		
		setLayout(null);

		lbTitle = new JLabel("Septopus", JLabel.CENTER);
		lbTitle.setFont(new Font("Dialog", Font.PLAIN, 24));
		lbTitle.setBounds(58, 6, 194, 24);
		getContentPane().add(lbTitle);

		lbText = new JLabel("Advanced Vocab Trainer", JLabel.CENTER);
		lbText.setBounds(47, 36, 227, 37);
		getContentPane().add(lbText);

		lbLogo = new JLabel(new ImageIcon("septopus.png"));
		lbLogo.setBounds(105, 72, 114, 109);
		getContentPane().add(lbLogo);
		
		btOkay = new JButton("Okay");
		btOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
		btOkay.setBounds(105, 185, 114, 20);
		getContentPane().add(btOkay);
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AboutView view = new AboutView();
		view.initGUI();
	}
}
