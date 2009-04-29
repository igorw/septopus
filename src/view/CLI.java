package view;

import java.io.File;

import controller.Trainer;


import model.Book;

/**
 * Command line interface, it loads everything else
 * @author igor
 */
public class CLI
{
	public final static int MODE_TRAINER = 0;
	public final static int MODE_PRESENTATION = 1;
	
	/**
	 * The main program with argument parsing
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		int mode = MODE_TRAINER;
		boolean recursive	= false;
		boolean random		= true;
		File file = null;
		
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
				else if (args[i].equals("-m") || args[i].equals("--mode"))
				{
					if (args.length >= i)
					{
						i++;
						
						if (args[i].equals("trainer"))
						{
							mode = MODE_TRAINER;
						}
						else if (args[i].equals("presentation"))
						{
							mode = MODE_PRESENTATION;
						}
					}
				}
				else if (args[i].equals("-h") || args[i].equals("--help"))
				{
					System.out.println("usage: voctrainer [options] [file]");
					System.out.println("Vocabulary trainer.");
					System.out.println("");
					System.out.println("Available options:");
					System.out.println("  -r, --recursive        repeat wrong answers");
					System.out.println("      --no-random        no randomised order");
					System.out.println("  -m, --mode=MODE        set the mode, default is trainer");
					System.out.println("                         possible values: trainer, presentation");
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

		Book book = null;
		CliLoader loader = new CliLoader();
		
		if (file == null)
		{
			file = loader.select();
		}
		
		book = loader.load(file);
		
		if (book == null)
		{
			return;
		}
		
		if (mode == MODE_TRAINER)
		{
			Trainer trainer = new Trainer();
			trainer.setBook(book);
			trainer.setRandom(random);
			trainer.setRecursive(recursive);
			
			trainer.test(true);
		}
		else if (mode == MODE_PRESENTATION)
		{
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
}
