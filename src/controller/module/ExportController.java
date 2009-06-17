package controller.module;

import javax.swing.JFileChooser;

import model.Book;
import controller.tool.Exporter;
import controller.tool.LatexExporter;

public class ExportController extends Module
{
	public ExportController()
	{
		setTitle("Export");
		setDescription("Export to various formats.");
		setPriority(7);
	}

	public void launch(Book book)
	{
		setBook(book);
		
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			Exporter ex = new LatexExporter(book);
			try
			{
				ex.export(chooser.getSelectedFile());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
