package controller.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import model.Book;
import model.Word;

public class Loader
{	
	/**
	 * Load words from a file into a book
	 * @param file the file to load from
	 * @throws Exception 
	 */
	public Book load(File file) throws Exception
	{
		if (file == null)
		{
			throw new FileNotFoundException();
		}
		
		if (!file.exists())
		{
			throw new FileNotFoundException(file.toString());
		}
		
		if (file.isDirectory())
		{
			throw new Exception(file.toString() + " is a directory");
		}
		
		Book book = new Book();
		
		book.setName(file.getName());
		
		try
		{
			BufferedReader bufRead = new BufferedReader(new FileReader(file));

			String line;
			String[] token;
			while (null != (line = bufRead.readLine()))
			{
				if (line.charAt(0) == '#')
				{
					continue;
				}
				
				token = line.split("\t");
				book.addWord(new Word(token[0], token[1]));
			}

			bufRead.close();
		}
		catch (Exception e)
		{
		}
		
		return book;
	}
}
