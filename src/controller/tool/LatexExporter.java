package controller.tool;

import java.io.File;

import model.Book;

/**
 * Export as printable cards LaTeX file
 * @author igor
 */
public class LatexExporter extends Exporter {

	public LatexExporter(Book book)
	{
		super(book);
	}

	public void export(File destination) throws Exception
	{
		/**
		 * @todo contents
		 */
	}
}
