import java.util.ArrayList;
import java.util.Iterator;

public class Book implements Iterable<Entry>
{
	private String name;
	private ArrayList<Entry> words = new ArrayList<Entry>();
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addEntry(Entry entry)
	{
		words.add(entry);
	}
	
	public void setWords(ArrayList<Entry> words)
	{
		this.words = words;
	}
	
	public ArrayList<Entry> getWords()
	{
		return words;
	}
	
	public boolean isEmpty()
	{
		return words.size() == 0;
	}
	
	public int size()
	{
		return words.size();
	}

	public Iterator<Entry> iterator()
	{
		return words.iterator();
	}
}
