package view;

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

import model.Book;
import controller.Presentation;

public class GuiPresentation extends Presentation
{
	/**
	 * The frame that contains the gui
	 */
	private JFrame frame = new JFrame();
	
	/**
	 * The first word
	 */
	private JLabel leftLabel = new JLabel("", JLabel.CENTER);
	
	/**
	 * The second word
	 */
	private JLabel rightLabel = new JLabel("", JLabel.CENTER);
	
	public GuiPresentation(Book book)
	{
		super(book);
		
		initGUI();
	}
	
	public boolean nextWord()
	{
		boolean result = super.nextWord();
		
		if (result)
		{
			leftLabel.setText(word.getLeft());
			rightLabel.setText(word.getRight());
		}
		
		return result;
	}

	public void prepare()
	{
		frame.setVisible(true);
	}

	public void showLeft()
	{
		leftLabel.setVisible(true);
	}

	public void showRight()
	{
		rightLabel.setVisible(true);
	}

	public void showNone()
	{
		leftLabel.setVisible(false);
		rightLabel.setVisible(false);
	}

	public void initGUI()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Exitig program on mouse click
		frame.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		// add key listener
		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					System.exit(0);
				}
			}
			public void keyTyped(KeyEvent e) {}
		});

		// remove window frame 
		frame.setUndecorated(true);

		// switching to fullscreen mode
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
		
		// content pane
		frame.setContentPane(new JPanel());
		
		// set font size
		frame.setFont(new Font(null, Font.CENTER_BASELINE, 30));
		leftLabel.setFont(frame.getFont());
		rightLabel.setFont(frame.getFont());
		
		// set layout
		frame.getContentPane().setLayout(new GridLayout(0, 1));
		
		// add labels
		frame.getContentPane().add(new JLabel());
		frame.getContentPane().add(leftLabel);
		frame.getContentPane().add(rightLabel);
		frame.getContentPane().add(new JLabel());
		
		// set label visibility
		leftLabel.setVisible(false);
		rightLabel.setVisible(false);
	}
}
