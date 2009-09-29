package controller.module;

import model.Book;

/**
 * Abstract Controller
 */
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
	
	/**
	 * Get the book
	 * @return book
	 */
	public Book getBook()
	{
		return book;
	}

	/**
	 * Launch the controller
	 */
	public abstract void launch();
}
