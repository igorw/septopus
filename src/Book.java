import java.util.ArrayList;
import java.util.Iterator;

/**
 * A book contains an array of entries.
 * @author igor
 */
public class Book implements Iterable<Entry>
{
	/**
	 * The name of the book. Every book has a name.
	 */
	private String name;
	
	/**
	 * The array of entries
	 */
	private ArrayList<Entry> words = new ArrayList<Entry>();
	
	/**
	 * Setter for the name
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Getter for the name
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Add an entry to the book
	 * @param entry
	 */
	public void addEntry(Entry entry)
	{
		words.add(entry);
	}
	
	/**
	 * Replace the whole array of entries with a new one
	 * @param words array of entries
	 */
	public void setWords(ArrayList<Entry> words)
	{
		this.words = words;
	}
	
	/**
	 * Read the whole array of entries
	 * @return array of entries
	 */
	public ArrayList<Entry> getWords()
	{
		return words;
	}
	
	/**
	 * Check if the book is empty
	 * @return <code>true</code> if there are more than 0 entries
	 */
	public boolean isEmpty()
	{
		return words.size() == 0;
	}
	
	/**
	 * Get ammount of entries
	 * @return size of book
	 */
	public int size()
	{
		return words.size();
	}
	
	/**
	 * Make it possible to iterate through entries
	 */
	public Iterator<Entry> iterator()
	{
		return words.iterator();
	}
}
