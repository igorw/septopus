package controller.exporter;

import java.io.File;


import model.Word;

/**
 * Export as printable cards LaTeX file
 * Inspired by: http://feferraz.net/en/P/Flashcards_in_LaTeX
 * @author igor
 */
public class FlashcardExporter extends Exporter
{
	public void export(File destination) throws Exception
	{
		StringBuffer contents = new StringBuffer();
		contents.append("\\documentclass[avery5371,frame]{flashcards}\n");
		contents.append("\n");
		contents.append("\\usepackage{ucs}\n");
		contents.append("\\usepackage[utf8x]{inputenc}\n");
		contents.append("\n");
		contents.append("\\renewcommand*\\familydefault{\\sfdefault}\n");
		contents.append("\n");
		contents.append("\\cardfrontstyle[\\Large]{headings}\n");
		contents.append("\\cardbackstyle[\\Large]{plain}\n");
		contents.append("\\cardfrontfoot{" + getBook().getName() + "}\n");
		contents.append("\n");
		contents.append("\\begin{document}\n");
		contents.append("\n");
		
		for (Word word : getBook())
		{
			contents.append("\\begin{flashcard}{" + word.getHome() + "}\n");
			contents.append(word.getForeign() + "\n");
			contents.append("\\end{flashcard}\n");
		}
		
		contents.append("\n");
		contents.append("\\end{document}\n");
		
		writeFile(destination, contents.toString());
	}
}
