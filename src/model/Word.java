package model;

/**
 * An word is available in two languages, refered to as foreign and home
 * @author igor
 */
public class Word
{
	/**
	 * The foreign word, usually unknown
	 */
	private String foreign;
	
	/**
	 * The native word, usually known
	 */
	private String home;
	
	/**
	 * Sole constructor, sets unknown and known words
	 * @param foreign	Left word
	 * @param home	Right word
	 */
	public Word (String left, String right)
	{
		this.foreign	= left;
		this.home		= right;
	}
	
	/**
	 * Getter for the foreign word
	 * @return unknown word
	 */
	public String getForeign()
	{
		return foreign;
	}
	
	/**
	 * Getter for the home word
	 * @return known word
	 */
	public String getHome()
	{
		return home;
	}
}
