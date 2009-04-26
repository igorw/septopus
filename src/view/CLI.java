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
		int mode = -1;
		boolean recursive	= false;
		boolean random		= true;
		File file = null;
		
		int mainArgs = 0;
		
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].startsWith("-"))
			{
				if (args[i].equals("-r") || args[i].equals("--recursive"))
				{
					recursive = true;
				}
				else if (args[i].equals("no-random"))
				{
					random = false;
				}
				else if (args[i].equals("-h") || args[i].equals("--help"))
				{
					System.out.println("usage: voctrainer [mode] [options] [file]");
					System.out.println("Vocabulary trainer.");
					System.out.println("");
					System.out.println("Available modes:");
					System.out.println("  trainer                normal mode");
					System.out.println("  presentation           view words");
					System.out.println("");
					System.out.println("Available options:");
					System.out.println("  -r, --recursive        repeat wrong answers");
					System.out.println("  -h, --help             this text");
					System.out.println("      --no-random        no randomised order");
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
				switch (mainArgs++)
				{
					case 0:
						if (args[i].equals("trainer"))
						{
							mode = MODE_TRAINER;
						}
						else if (args[i].equals("presentation"))
						{
							mode = MODE_PRESENTATION;
						}
						else
						{
							System.out.println("Invalid mode given.");
							return;
						}
						break;
					case 1:
						file = new File(args[i]);
						break;
				}
			}
		}
		
		if (mainArgs < 1)
		{
			System.out.println("not enough arguments given");
			return;
		}
		
		System.out.println("voc trainer (c) 2009 by igor wiedler");

		Book book = null;
		CliLoader loader = new CliLoader();
		
		if (file == null)
		{
			file = loader.select();
		}
		
		book = loader.load(file);
		
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
			GuiPresentation p = new GuiPresentation();
			p.setTitle("Voc Presentation");
			
			// shuffle entries
			if (random)
			{
				book.shuffle();
			}
			
			p.setBook(book);
			p.present();
		}
	}
}
