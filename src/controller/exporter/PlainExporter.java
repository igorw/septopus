package controller.exporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import model.Word;

public class PlainExporter extends Exporter
{
	public void export(File destination) throws Exception
	{
		StringBuffer contents = new StringBuffer();
		
		for (Word word : getBook())
		{
			contents.append(word.getForeign() + "\t" + word.getHome() + "\n");
		}
		
		BufferedWriter out = new BufferedWriter(new FileWriter(destination));
		out.write(contents.toString());
		out.close();
	}
}
