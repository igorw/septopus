package view;

import java.io.File;

import model.Book;
import controller.Loader;
import controller.Trainer;

/**
 * Command line interface for trainer
 * @author igor
 */
public class CliTrainer
{
	/**
	 * The main program with argument parsing
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		boolean recursive	= false;
		boolean random		= true;
		File file = null;
		Book book = null;
		
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].startsWith("-"))
			{
				// options
				if (args[i].equals("-r") || args[i].equals("--recursive"))
				{
					recursive = true;
				}
				else if (args[i].equals("--no-random"))
				{
					random = false;
				}
				else if (args[i].equals("-h") || args[i].equals("--help"))
				{
					System.out.println("usage: voc-train [options] file");
					System.out.println("vocabulary trainer.");
					System.out.println("");
					System.out.println("Available options:");
					System.out.println("  -r, --recursive        repeat wrong answers");
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
		
		System.out.println("voc trainer (c) 2009 by igor wiedler");

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
		
		// load trainer
		Trainer trainer = new Trainer();
		trainer.setBook(book);
		trainer.setRandom(random);
		trainer.setRecursive(recursive);
		
		trainer.test();
	}
}
