package controller.module;

import model.Book;

/**
 * A module for the program
 * @author igor
 */
public abstract class ModuleBase implements Comparable<ModuleBase>
{
	private String title;
	private String description;
	
	/**
	 * Priority between 0 (lowest) and 9 (highest)
	 */
	private int priority;
	
	/**
	 * The book used for training
	 */
	protected Book book;
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getPriority()
	{
		return priority;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	/**
	 * Replace the current book with a new one
	 * @param book the new book
	 */
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	public String toString()
	{
		return title;
	}

	public abstract void launch(Book book);

	public int compareTo(ModuleBase m) {
		if (getPriority() < m.getPriority())
		{
			return 1;
		}
		else if (getPriority() > m.getPriority())
		{
			return -1;
		}
		else
		{
			return getTitle().compareTo(m.getTitle());
		}
	}
}
