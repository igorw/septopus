package view;

import java.io.File;

import model.Book;
import controller.Loader;

/**
 * Command line interface for presentation
 * @author igor
 */
public class CliPresentation
{
	/**
	 * The main program with argument parsing
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		boolean random = true;
		File file = null;
		Book book = null;
		
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].startsWith("-"))
			{
				// options
				if (args[i].equals("--no-random"))
				{
					random = false;
				}
				else if (args[i].equals("-h") || args[i].equals("--help"))
				{
					System.out.println("usage: voc-present [options] file");
					System.out.println("vocabulary presentation.");
					System.out.println("");
					System.out.println("Available options:");
					System.out.println("      --no-random        no randomised order");
					System.out.println("  -h, --help             display this text and exit");
					return;
				}
				else
				{
					System.out.println("Invalid option \"" + args[i] + "\"");
					return;
				}
			}
			else
			{
				// main arguments
				// voc file
				file = new File(args[i]);
			}
		}
		
		System.out.println("voc presentation (c) 2009 by igor wiedler");

		Loader loader = new Loader();
		
		try
		{
			book = loader.load(file);
		}
		catch (Exception e)
		{
			System.out.println("Invalid file specified.");
			return;
		}
		
		// shuffle words
		if (random)
		{
			book.shuffle();
		}
		
		GuiPresentation p = new GuiPresentation(book);
		
		try
		{
			p.present();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
