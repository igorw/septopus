package controller.module;

import model.Book;

public abstract class Controller
{
	/**
	 * The book used for training
	 */
	private Book book;
	
	/**
	 * Replace the current book with a new one
	 * @param book the new book
	 */
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	public Book getBook()
	{
		return book;
	}

	public abstract void launch(Book book);
}
