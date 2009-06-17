package controller.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Book;
import model.Word;
import view.TrainerView;


/**
 * The trainer is the main program for voc training
 * @author igor
 * @todo move sysout stuff to a new cli-view
 */
public class TrainerController extends ModuleBase
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
	 * Process a test
	 */
	public void test()
	{
		// don't test empty books
		if (book.isEmpty())
		{
			return;
		}
		
		// shuffle book
		if (random)
		{
			book.shuffle();
		}
		
		for (Word word : book)
		{
			System.out.println(word.getHome());

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			try
			{
				String input = br.readLine();
				
				if (!input.trim().equals(word.getForeign()))
				{
					wrongAnswers.addWord(word);
					System.out.println("Wrong. Correct: " + word.getHome());
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if (!wrongAnswers.isEmpty())
		{
			System.out.println(wrongAnswers.size() + " wrong answers. The are listed here:");
			for (Word word : wrongAnswers)
			{
				System.out.println(word.getForeign() + " - " + word.getHome());
			}
		}
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
		
		view = new TrainerView();
		view.initGUI();
		
		view.addNextActionListener(new NextListener());
		
		// shuffle book
		if (random)
		{
			book.shuffle();
		}
		
		Word word = thisWord();
		view.setHome(word.getHome());
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
	
	private class NextListener implements ActionListener {
		private boolean lastWrong = false;
		public void actionPerformed(ActionEvent e) {
			
			String input = view.getForeign();
			Word word = thisWord();
			
			if (lastWrong)
			{
				lastWrong = false;
				word = nextWord();
				
				if (word == null)
				{
					System.out.println("fertig");
					return;
				}
				
				view.setHome(word.getHome());
				view.setForeign("");
				view.setCorrection("");
				return;
			}
			
			if (word == null)
			{
				System.out.println("fertig");
				return;
			}
			
			if (!input.trim().equals(word.getForeign()))
			{
				wrongAnswers.addWord(word);
				view.setCorrection(word.getForeign());
				lastWrong = true;
				return;
			}
			
			word = nextWord();
			
			if (word == null)
			{
				System.out.println("fertig");
				return;
			}
			
			view.setHome(word.getHome());
			view.setForeign("");
			view.setCorrection("");
		}
	}
}
