package controller;

import model.Book;
import model.Word;

public abstract class Presentation
{
	/**
	 * Contains our data
	 */
	private Book book;
	
	/**
	 * Current word
	 */
	protected Word word;
	
	/**
	 * Current index
	 */
	private int i = 0;
	
	/**
	 * Constructor
	 * @param book
	 */
	public Presentation(Book book)
	{
		this.book = book;
	}
	
	/**
	 * Get the next word from the book
	 * @return <code>true</code> for continuing, <code>false</code> for stop
	 */
	public boolean nextWord()
	{
		if (i >= book.size())
		{
			return false;
		}
		
		word = book.get(i++);
		
		return true;
	}
	
	/**
	 * Prepare the presentation
	 */
	public abstract void prepare();
	
	/**
	 * Show the left word
	 */
	public abstract void showLeft();
	
	/**
	 * Show the right word
	 */
	public abstract void showRight();
	
	/**
	 * Hide the words for the next round
	 */
	public abstract void showNone();
	
	/**
	 * Present the presentation
	 * @throws InterruptedException
	 */
	public void present() throws InterruptedException
	{
		prepare();
		
		while (nextWord())
		{
			showRight();
			
			Thread.sleep(2000);

			showLeft();
			
			Thread.sleep(4000);
			
			showNone();
		}
		
		System.exit(0);
	}
}
