package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Book;
import model.Word;


/**
 * The trainer is the main program for voc training
 * @author igor
 * @todo move sysout stuff to a new cli-view
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
			System.out.println(word.getRight());

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			try
			{
				String input = br.readLine();
				
				if (!input.trim().equals(word.getLeft()))
				{
					wrongAnswers.addWord(word);
					System.out.println("Wrong. Correct: " + word.getRight());
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
				trainer.test();
				trainer.setRecursive(true);
			}
			else
			{
				System.out.println(wrongAnswers.size() + " wrong answers. The are listed here:");
				for (Word word : wrongAnswers)
				{
					System.out.println(word.getLeft() + " - " + word.getRight());
				}
			}
		}
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
