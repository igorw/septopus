package controller.exporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Book;

public abstract class Exporter
{
	private Book book;
	
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	public Book getBook()
	{
		return book;
	}
	
	public abstract void export(File destination) throws Exception;
	
	public void writeFile(File file, String contents) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write(contents);
		out.close();
	}
}
