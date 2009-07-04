package controller.exporter;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import model.Word;

public class IPodExporter extends Exporter
{
	Charset charset;
	CharsetDecoder decoder;
	CharsetEncoder encoder;
	
	public IPodExporter()
	{
		
	}
	
	public void export(File destination) throws Exception
	{
		String contents;
		File file;
		
		if (!destination.exists())
		{
			destination.mkdirs();
		}
		
		int i = 0;
		for (Word word : getBook())
		{
			file = new File(destination.getPath() + "/" + i);
			contents = encode(word.getHome()) + "\n<br><br>\n<br>\n<br><br>\n<a href=\"" + i + "a\">answer</a>";
			
			writeFile(file, contents);

			file = new File(destination.getPath() + "/" + i + "a");
			contents = encode(word.getHome()) + "\n<br><br>\n" + encode(word.getForeign()) + "\n<br><br>\n<a href=\"" + (i + 1) + "\">next</a>";
			
			writeFile(file, contents);
			
			i++;
		}
		
		// the end
		file = new File(destination.getPath() + "/" + i);
		contents = "the end.\n<br><br>\nhold down the menu button to get out.";
		
		writeFile(file, contents);
	}
	
	private String encode(String input)
	{
		// für umwandlung von UTF-8 nach ISO-8859-1
		charset = Charset.forName("ISO-8859-1");
		decoder = charset.newDecoder();
		encoder = charset.newEncoder();
		
		try
		{
			ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(input));
			CharBuffer cbuf = decoder.decode(bbuf);
			return cbuf.toString();
		}
		catch (CharacterCodingException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
