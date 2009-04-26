package controller;

import model.Book;
import model.Word;

public class Presentation
{
	/**
	 * Contains our data
	 */
	private Book book;
	
	private int i = 0;
	
	public Presentation(Book book)
	{
		this.book = book;
	}
	
	public Word nextWord()
	{
		if (i >= book.size())
		{
			return null;
		}
		
		return book.get(i++);
	}
}
