package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Book;
import model.Word;

public class CliLoader
{
	/**
	 * Select a file
	 * @return selected file
	 */
	public File select()
	{
		System.out.println("Please enter voc file: (leave blank to cancel)");
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		File file = null;
		String input;
		
		try
		{
			do
			{
				if (file != null)
				{
					System.out.println("File doesn't exist. Try again.");
				}
				
				input = br.readLine();
				
				if (input.isEmpty())
				{
					return null;
				}
				
				file = new File(input);
			}
			while (!file.exists() || file.isDirectory());
		}
		catch (IOException e)
		{
		}
		
		return file;
	}
	
	/**
	 * Load words from a file
	 * @param file the file to load from
	 */
	public Book load(File file)
	{
		if (file == null)
		{
			return null;
		}
		
		if (!file.exists())
		{
			System.out.println("File doesn't exist.");
			return null;
		}
		
		if (file.isDirectory())
		{
			System.out.println("File is a directory.");
			return null;
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
