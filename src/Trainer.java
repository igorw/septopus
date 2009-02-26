import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The trainer is the main program for voc training
 * @author igor
 */
public class Trainer
{
	/**
	 * The book used for training
	 */
	private Book book = new Book();
	
	/**
	 * A book that contains all wrong answers
	 */
	private Book wrongAnswers = new Book();
	
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
					System.out.println("File doesn't exist.");
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
	 * Load entries from a file
	 * @param file the file to load from
	 */
	public void load(File file)
	{
		if (file == null)
		{
			return;
		}
		
		if (!file.exists())
		{
			System.out.println("File doesn't exist.");
			return;
		}
		
		if (file.isDirectory())
		{
			System.out.println("File is a directory.");
			return;
		}
		
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
				book.addEntry(new Entry(token[0], token[1]));
			}

			bufRead.close();
		}
		catch (Exception e)
		{
		}
	}
	
	/**
	 * Process a test
	 * @param verbose	if enabled extra info about beginning and ending the test
	 * 					is displayed
	 * @param recurse	if enabled wrong answers are asked repeatedly 
	 */
	public void test(boolean verbose, boolean recurse)
	{
		// don't test empty books
		if (book.isEmpty())
		{
			return;
		}
		
		if (verbose)
		{
			System.out.println("Beginning test: " + book.getName());
		}
		
		for (Entry entry : book)
		{
			System.out.println(entry.getKnown());

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			try
			{
				String input = br.readLine();
				
				if (!input.trim().equals(entry.getUnknown()))
				{
					wrongAnswers.addEntry(entry);
					System.out.println("Wrong. Correct: " + entry.getUnknown());
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if (!wrongAnswers.isEmpty())
		{
			if (recurse)
			{
				System.out.println(wrongAnswers.size() + " wrong answers. Repeating wrong answers.");
				
				Trainer trainer = new Trainer();
				trainer.setBook(wrongAnswers);
				trainer.test(false, true);
			}
			else
			{
				System.out.println(wrongAnswers.size() + " wrong answers. The are listed here:");
				for (Entry entry : wrongAnswers)
				{
					System.out.println(entry.getUnknown() + " - " + entry.getKnown());
				}
			}
		}
		
		if (verbose)
		{
			System.out.println("End.");
		}
	}
	
	/**
	 * default settings of test()
	 */
	public void test()
	{
		test(true, true);
	}
	
	/**
	 * Replace the current book with a new one
	 * @param book the new book
	 */
	public void setBook(Book book)
	{
		this.book = book;
	}

	/**
	 * The main program with argument parsing
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		boolean recursive = false;
		File file = null;
		
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].startsWith("-"))
			{
				if (args[i].equals("-r") || args[i].equals("--recursive"))
				{
					recursive = true;
				}
				else if (args[i].equals("-h") || args[i].equals("--help"))
				{
					System.out.println("usage: voctrainer [options] [file]");
					System.out.println("Vocabulary trainer.");
					System.out.println("");
					System.out.println("Available options:");
					System.out.println("  -r, --recursive        repeat wrong answers");
					System.out.println("  -h, --help             this text");
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
				file = new File(args[i]);
			}
		}
		
		System.out.println("voc trainer (c) 2009 by igor wiedler");
		
		Trainer trainer = new Trainer();
		
		if (file == null)
		{
			file = trainer.select();
		}
		
		trainer.load(file);
		trainer.test(true, recursive);
	}
}
