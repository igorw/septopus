package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The presentation window
 */
public class PresentationView extends JFrame
{
	private static final long serialVersionUID = 1L;

	/**
	 * The first word
	 */
	private JLabel foreignLabel = new JLabel("", JLabel.CENTER);
	
	/**
	 * The second word
	 */
	private JLabel homeLabel = new JLabel("", JLabel.CENTER);
	
	/**
	 * is the presentation paused?
	 */
	private boolean paused = false;
	
	public void setForeign(String foreign)
	{
		foreignLabel.setText(foreign);
	}
	
	public void setHome(String home)
	{
		homeLabel.setText(home);
	}

	public void showForeign()
	{
		foreignLabel.setVisible(true);
		
		pausedCheck();
	}

	public void showHome()
	{
		homeLabel.setVisible(true);
		
		pausedCheck();
	}

	public void showNone()
	{
		foreignLabel.setVisible(false);
		homeLabel.setVisible(false);
	}

	/**
	 * Initialise the GUI
	 */
	public void initGUI()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// Exiting program on mouse click
		addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e)
			{
				setVisible(false);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		// add key listener
		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_ESCAPE:
						setVisible(false);
						break;
					case KeyEvent.VK_SPACE:
						paused = !paused;
						break;
				}
			}
			public void keyTyped(KeyEvent e) {}
		});

		// remove window frame 
		setUndecorated(true);
		
		// content pane
		setContentPane(new JPanel());
		
		// set font size
		setFont(new Font(null, Font.CENTER_BASELINE, 30));
		foreignLabel.setFont(getFont());
		homeLabel.setFont(getFont());
		
		// set layout
		getContentPane().setLayout(new GridLayout(0, 1));
		
		// add labels
		getContentPane().add(new JLabel());
		getContentPane().add(homeLabel);
		getContentPane().add(foreignLabel);
		getContentPane().add(new JLabel());
		
		// set label visibility
		foreignLabel.setVisible(false);
		homeLabel.setVisible(false);
		
		setVisible(true);

		// switching to fullscreen mode
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
		
		setBackground(Color.white);
	}
	
	private void pausedCheck()
	{
		while (paused)
		{
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
			}
		}
	}
}
