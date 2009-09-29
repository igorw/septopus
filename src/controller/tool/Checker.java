package controller.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Check whether a voc file is valid
 * @author igor
 */
public class Checker
{
	/**
	 * Check if a file is valid
	 * @param file the file to check
	 * @return file is valid
	 */
	public boolean check(File file) throws Exception
	{
		if (file == null)
		{
			throw new FileNotFoundException();
		}
		
		if (!file.exists())
		{
			throw new FileNotFoundException(file.toString());
		}
		
		if (file.isDirectory())
		{
			throw new Exception(file.toString() + " is a directory");
		}
		
		try
		{
			BufferedReader bufRead = new BufferedReader(new FileReader(file));

			String line;
			while (null != (line = bufRead.readLine()))
			{
				// kommentar
				if (line.charAt(0) == '#')
				{
					continue;
				}
				
				if (line.split("\t").length != 2)
				{
					return false;
				}
			}

			bufRead.close();
		}
		catch (Exception e)
		{
		}
		
		return true;
	}
}
