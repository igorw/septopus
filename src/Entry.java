
/**
 * An entry contains two words. One known and one unknown.
 * @author igor
 */
public class Entry
{
	/**
	 * The unknown word is the foreign word, it is the one the
	 * person must know.
	 */
	private String unknown;
	
	/**
	 * The known word is the word in native language.
	 */
	private String known;
	
	/**
	 * Sole constructor, sets unknown and known words.
	 * @param unknown	the unknown word
	 * @param known		the known word
	 */
	public Entry (String unknown, String known)
	{
		this.unknown	= unknown;
		this.known		= known;
	}
	
	/**
	 * Getter for the unknown word
	 * @return unknown word
	 */
	public String getUnknown()
	{
		return unknown;
	}
	
	/**
	 * Getter for the known word
	 * @return known word
	 */
	public String getKnown()
	{
		return known;
	}
}
