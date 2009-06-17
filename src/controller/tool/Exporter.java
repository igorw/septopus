package controller.tool;

import java.io.File;

import model.Book;

public abstract class Exporter
{
	private Book book;
	
	public Exporter(Book book)
	{
		this.book = book;
	}
	
	public Book getBook()
	{
		return book;
	}
	
	public abstract void export(File destination) throws Exception;
}
