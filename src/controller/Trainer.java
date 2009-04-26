package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Book;
import model.Entry;


/**
 * The trainer is the main program for voc training
 * @author igor
 */
public class Trainer
{
	/**
	 * The book used for training
	 */
	private Book book = new Book();
	
	/**
	 * A book that contains all wrong answers
	 */
	private Book wrongAnswers = new Book();
	
	/**
	 * When set to recurse, all wrong answers are repeated
	 */
	private boolean recursive = false;
	
	/**
	 * When set, the questions are randomised (yes, i'm british)
	 */
	private boolean random = true;
	
	/**
	 * Process a test
	 * @param verbose	if enabled extra info about beginning and ending the test
	 * 					is displayed
	 * @param recurse	if enabled wrong answers are asked repeatedly 
	 */
	public void test(boolean verbose)
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
		
		if (verbose)
		{
			System.out.println("Beginning test: " + book.getName());
		}
		
		for (Entry entry : book)
		{
			System.out.println(entry.getKnown());

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			try
			{
				String input = br.readLine();
				
				if (!input.trim().equals(entry.getUnknown()))
				{
					wrongAnswers.addEntry(entry);
					System.out.println("Wrong. Correct: " + entry.getUnknown());
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if (!wrongAnswers.isEmpty())
		{
			if (recursive)
			{
				System.out.println(wrongAnswers.size() + " wrong answers. Repeating wrong answers.");
				
				Trainer trainer = new Trainer();
				trainer.setBook(wrongAnswers);
				trainer.test(false);
				trainer.setRecursive(true);
			}
			else
			{
				System.out.println(wrongAnswers.size() + " wrong answers. The are listed here:");
				for (Entry entry : wrongAnswers)
				{
					System.out.println(entry.getUnknown() + " - " + entry.getKnown());
				}
			}
		}
		
		if (verbose)
		{
			System.out.println("End.");
		}
	}
	
	/**
	 * default settings of test()
	 */
	public void test()
	{
		test(true);
	}
	
	/**
	 * Replace the current book with a new one
	 * @param book the new book
	 */
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	/**
	 * Set recursion
	 * @param recursive value for recursive
	 */
	public void setRecursive(boolean recursive)
	{
		this.recursive = recursive;
	}
	
	/**
	 * Set randomisation
	 * @param random value for random
	 */
	public void setRandom(boolean random)
	{
		this.random = random;
	}
}
