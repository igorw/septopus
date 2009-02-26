
public class Entry
{
	private String unknown; // foreign word
	private String known;	// german word
	
	public Entry (String unknown, String known)
	{
		this.unknown	= unknown;
		this.known		= known;
	}
	
	public String getUnknown()
	{
		return unknown;
	}
	
	public String getKnown()
	{
		return known;
	}
}
