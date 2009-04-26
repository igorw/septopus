package model;

/**
 * An word is available in two languages, refered to as left and right
 * @author igor
 */
public class Word
{
	/**
	 * The word on the left side, usually unknown
	 */
	private String left;
	
	/**
	 * The word on the right side, usually unknown
	 */
	private String right;
	
	/**
	 * Sole constructor, sets unknown and known words
	 * @param left	Left word
	 * @param right	Right word
	 */
	public Word (String left, String right)
	{
		this.left	= left;
		this.right	= right;
	}
	
	/**
	 * Getter for the left word
	 * @return unknown word
	 */
	public String getLeft()
	{
		return left;
	}
	
	/**
	 * Getter for the right word
	 * @return known word
	 */
	public String getRight()
	{
		return right;
	}
}
