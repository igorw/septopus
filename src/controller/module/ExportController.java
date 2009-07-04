package controller.module;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import model.Book;
import controller.exporter.Exporter;
import controller.exporter.FlashcardExporter;
import controller.exporter.IPodExporter;
import controller.exporter.LatexExporter;

/**
 * Controller for the export module
 */
public class ExportController extends Controller
{
	public void launch(Book book)
	{
		setBook(book);
		
		JFileChooser chooser = new JFileChooser();

		chooser.setSelectedFile(new File(book.getName()));
		chooser.addChoosableFileFilter(new IPodFilter());
		chooser.addChoosableFileFilter(new LatexFilter());
		chooser.addChoosableFileFilter(new FlashcardFilter());
		chooser.setAcceptAllFileFilterUsed(false);
		
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			ExportFilter filter = (ExportFilter) chooser.getFileFilter();
			
			Exporter ex = filter.getExporter();
			ex.setBook(book);
			
			try
			{
				File file = chooser.getSelectedFile();
				String ext = filter.getExtension();
				if (!file.getName().endsWith(ext))
				{
					file = new File(file.getPath() + ext);
				}
				ex.export(file);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private abstract class ExportFilter extends FileFilter
	{
		public abstract String getExtension();
		public abstract Exporter getExporter();
	}
	
	private class LatexFilter extends ExportFilter
	{
		public boolean accept(File f)
		{
			if (f.isDirectory())
			{
				return true;
			}
			
			if (f.getName().endsWith(".tex"))
			{
				return true;
			}
			
			return false;
		}

		public String getDescription()
		{
			return "LaTeX (*.tex)";
		}

		public String getExtension()
		{
			return ".tex";
		}

		public Exporter getExporter()
		{
			return new LatexExporter();
		}
	}
	
	private class FlashcardFilter extends ExportFilter
	{
		public boolean accept(File f)
		{
			if (f.isDirectory())
			{
				return true;
			}
			
			if (f.getName().endsWith(".tex"))
			{
				return true;
			}
			
			return false;
		}

		public String getDescription()
		{
			return "LaTeX Flashcard (*.tex)";
		}

		public String getExtension()
		{
			return ".tex";
		}

		public Exporter getExporter()
		{
			return new FlashcardExporter();
		}
	}
	
	private class IPodFilter extends ExportFilter
	{
		public boolean accept(File f)
		{
			if (f.isDirectory())
			{
				return true;
			}
			
			return false;
		}

		public String getDescription()
		{
			return "iPod";
		}

		public String getExtension()
		{
			return "";
		}

		public Exporter getExporter()
		{
			return new IPodExporter();
		}
	}
}
