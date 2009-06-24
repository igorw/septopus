package controller.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import model.Book;
import model.Word;

/**
 * Export as printable cards LaTeX file
 * @author igor
 */
public class LatexExporter extends Exporter
{
	public LatexExporter()
	{
		super();
	}
	
	public LatexExporter(Book book)
	{
		super(book);
	}

	public void export(File destination) throws Exception
	{
		StringBuffer contents = new StringBuffer();
		contents.append("\\documentclass[a4paper]{article}\n");
		contents.append("\n");
		contents.append("\\usepackage{ucs}\n");
		contents.append("\\usepackage[utf8x]{inputenc}\n");
		contents.append("\\usepackage{fullpage}\n");
		contents.append("\\usepackage{longtable}\n");
		contents.append("\n");
		contents.append("\\renewcommand*\\familydefault{\\sfdefault}\n");
		contents.append("\n");
		contents.append("\\setcounter{secnumdepth}{0}\n");
		contents.append("\n");
		contents.append("\\title{" + getBook().getName() + "}\n");
		contents.append("\\date{}\n");
		contents.append("\n");
		contents.append("\\begin{document}\n");
		contents.append("\n");
		contents.append("\\maketitle\n");
		contents.append("\n");
		contents.append("\\begin{longtable}{|p{6cm}|p{6cm}|}\n");
		contents.append("	\\hline\n");
		contents.append("	\\textbf{English} & \\textbf{German} \\\\\n");
		contents.append("	\\hline\n");
		contents.append("	\\endhead\n");
		contents.append("	\n");
		contents.append("	\\hline\n");
		contents.append("	\\endfoot\n");
		contents.append("\n");
		contents.append("	\\hline\n");
		contents.append("	\\endlastfoot\n");
		contents.append("\n");
		
		for (Word word : getBook())
		{
			contents.append("	" + word.getForeign() + " & " + word.getHome());
			if (!getBook().get(getBook().size() - 1).equals(word))
			{
				contents.append(" \\\\");
			}
			contents.append("\n");
		}
		
		contents.append("\\end{longtable}\n");
		contents.append("\n");
		contents.append("\\end{document}\n");
		
		BufferedWriter out = new BufferedWriter(new FileWriter(destination));
		out.write(contents.toString());
		out.close();
	}
}
