package controller.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Book;
import model.Word;
import view.TrainerView;


/**
 * The trainer is the main program for voc training
 * @author igor
 * @todo move sysout stuff to a new cli-view
 */
public class TrainerController extends Module
{
	/**
	 * A book that contains all wrong answers
	 */
	private Book wrongAnswers = new Book();
	
	/**
	 * When set, the questions are randomised (yes, i'm british)
	 */
	private boolean random = true;
	
	private TrainerView view;
	
	private int index = 0;
	
	/**
	 * Constructor
	 */
	public TrainerController()
	{
		setTitle("Trainer");
		setDescription("Test your vocabulary until you crack.");
		setPriority(9);
	}
	
	/**
	 * Set randomisation
	 * @param random value for random
	 */
	public void setRandom(boolean random)
	{
		this.random = random;
	}
	
	/**
	 * Launch the module
	 */
	public void launch(Book book)
	{
		setBook(book);
		
		// reset
		index = 0;
		random = true;
		
		view = new TrainerView();
		view.initGUI();
		
		view.addNextActionListener(new NextListener());
		view.addForeignKeyListener(new NextListener());
		
		// shuffle book
		if (random)
		{
			book.shuffle();
		}
		
		Word word = thisWord();
		view.setHome(word.getHome());
		
		/**
		 * @todo display wrong words
		 */
	}
	
	private Word nextWord()
	{
		if (index + 1 == book.size())
		{
			return null;
		}
		
		return book.get(++index);
	}
	
	private Word thisWord()
	{
		if (index == book.size())
		{
			return null;
		}
		
		return book.get(index);
	}
	
	private class NextListener implements ActionListener, KeyListener
	{
		private boolean lastWrong = false;
		
		private Word word;
		
		public void perform()
		{
			String input = view.getForeign();
			word = thisWord();
			
			if (lastWrong)
			{
				lastWrong = false;
				next();
				return;
			}
			
			if (word == null)
			{
				view.setVisible(false);
				return;
			}
			
			if (!input.trim().equals(word.getForeign()))
			{
				wrongAnswers.addWord(word);
				view.setCorrection(word.getForeign());
				lastWrong = true;
				return;
			}
			
			next();
		}
		
		private void next()
		{
			word = nextWord();
			
			if (word == null)
			{
				view.setVisible(false);
				return;
			}
			
			view.setHome(word.getHome());
			view.setForeign("");
			view.setCorrection("");
			view.requestForeignFocus();
		}
		
		public void actionPerformed(ActionEvent e)
		{
			perform();
		}

		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				perform();
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}
