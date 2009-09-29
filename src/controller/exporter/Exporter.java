package controller.exporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Book;

/**
 * Export books in various formats
 */
public abstract class Exporter
{
	/**
	 * The book to export
	 */
	private Book book;
	
	/**
	 * Setter for book
	 * @param book
	 */
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	/**
	 * Getter for book
	 * @return
	 */
	public Book getBook()
	{
		return book;
	}
	
	/**
	 * Export the book
	 * @param destination
	 * @throws Exception
	 */
	public abstract void export(File destination) throws Exception;
	
	/**
	 * Write contents to a file
	 * @param file
	 * @param contents
	 * @throws IOException
	 */
	public void writeFile(File file, String contents) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write(contents);
		out.close();
	}
}
