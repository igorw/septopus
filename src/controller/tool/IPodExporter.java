package controller.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import model.Book;
import model.Word;

public class IPodExporter extends Exporter
{
	public IPodExporter(Book book)
	{
		super(book);
	}

	public void export(File destination) throws Exception
	{
		BufferedWriter out;
		String contents;
		File file;
		int i, size;
		
		ArrayList<Word> words = getBook().getWords();
		
		for (i = 0, size = words.size(); i < (size + 1); i++)
		{
			if (size == i)
			{
				contents = "the end.\n<br><br>\nhold down the menu button to get out.";
				file = new File(destination.getPath() + "/" + i);
				
				out = new BufferedWriter(new FileWriter(file));
				out.write(contents);
				out.close();
				
				break;
			}
			
			Word word = words.get(i);
			
			contents = word.getHome() + "\n<br><br>\n<a href=\"" + i + "a\">answer</a>";
			file = new File(destination.getPath() + "/" + i);
			
			/*if (!file.exists())
			{
				file.createNewFile();
			}*/
			
			out = new BufferedWriter(new FileWriter(file));
			out.write(contents);
			out.close();
			
			contents = word.getHome() + "\n<br><br>\n" + word.getForeign() + "\n<br><br>\n<a href=\"" + (i + 1) + "\">next</a>";
			file = new File(destination.getPath() + "/" + i + "a");
			
			out = new BufferedWriter(new FileWriter(file));
			out.write(contents);
			out.close();
		}
	}
}
