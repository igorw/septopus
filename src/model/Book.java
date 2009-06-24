package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * A book contains an array of words.
 * @author igor
 */
public class Book implements Iterable<Word>
{
	/**
	 * The name of the book. Every book has a name.
	 */
	private String name;
	
	/**
	 * The array of words
	 */
	private ArrayList<Word> words = new ArrayList<Word>();
	
	/**
	 * Constructor
	 */
	public Book()
	{
	}
	
	/**
	 * Constructor with name
	 * @param name
	 */
	public Book(String name)
	{
		setName(name);
	}
	
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
	 * Get a specific word
	 * @param index
	 * @return selected word
	 */
	public Word get(int index)
	{
		return words.get(index);
	}
	
	/**
	 * Add an word to the book
	 * @param word
	 */
	public void addWord(Word word)
	{
		words.add(word);
	}
	
	/**
	 * Replace the whole array of words with a new one
	 * @param words array of words
	 */
	public void setWords(ArrayList<Word> words)
	{
		this.words = words;
	}
	
	/**
	 * Read the whole array of words
	 * @return array of words
	 */
	public ArrayList<Word> getWords()
	{
		return words;
	}
	
	/**
	 * Check if the book is empty
	 * @return <code>true</code> if there are more than 0 words
	 */
	public boolean isEmpty()
	{
		return words.size() == 0;
	}
	
	/**
	 * Get ammount of words
	 * @return size of book
	 */
	public int size()
	{
		return words.size();
	}
	
	/**
	 * Shuffle words
	 */
	public void shuffle()
	{
		Collections.shuffle(words);
	}
	
	/**
	 * Make it possible to iterate through words
	 */
	public Iterator<Word> iterator()
	{
		return words.iterator();
	}
}
