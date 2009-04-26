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
import model.Entry;

public class GuiPresentation extends JFrame
{
	private static final long serialVersionUID = 1L;

	/**
	 * Contains our data
	 */
	private Book book = new Book();
	
	/**
	 * The first word
	 */
	private JLabel firstLabel = new JLabel("", JLabel.CENTER);
	
	/**
	 * The second word
	 */
	private JLabel secondLabel = new JLabel("", JLabel.CENTER);
	
	/**
	 * Constructor
	 */
	public GuiPresentation()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Exitig program on mouse click
		addMouseListener(new MouseListener() {
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
		addKeyListener(new KeyListener() {
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
		setUndecorated(true);

		// switching to fullscreen mode
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
		
		// content pane
		setContentPane(new JPanel());
		
		// set font size
		setFont(new Font(null, Font.CENTER_BASELINE, 30));
		firstLabel.setFont(getFont());
		secondLabel.setFont(getFont());
		
		// set layout
		getContentPane().setLayout(new GridLayout(0, 1));
		
		// add labels
		getContentPane().add(new JLabel());
		getContentPane().add(firstLabel);
		getContentPane().add(secondLabel);
		getContentPane().add(new JLabel());
	}
	
	/**
	 * Start the presentation
	 */
	public void present()
	{
		setVisible(true);
		
		for (Entry entry : book)
		{
			// show known word
			setFirst(entry.getKnown());
			
			GuiPresentation.sleep(2);
			
			// show unknown word
			setSecond(entry.getUnknown());
			
			GuiPresentation.sleep(4);
			
			// clear known and unknown words
			setFirst("");
			setSecond("");
		}
		
		System.exit(0);
	}
	
	/**
	 * Set the first text
	 * @param first Text for the first text item
	 */
	public void setFirst(String first)
	{
		firstLabel.setText(first);
	}
	
	/**
	 * Set the second text
	 * @param first Text for the second text item
	 */
	public void setSecond(String second)
	{
		secondLabel.setText(second);
	}
	
	/**
	 * Set the book to use
	 * @param book
	 */
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	/**
	 * Wait for some seconds
	 * @param seconds
	 */
	public static void sleep(int seconds)
	{
		try
		{
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e)
		{
		}
	}
}
