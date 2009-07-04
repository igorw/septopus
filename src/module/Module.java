package module;

import controller.module.Controller;


/**
 * A module for the program
 * @author igor
 */
public abstract class Module implements Comparable<Module>
{
	private String title;
	private String description;
	
	/**
	 * Priority between 0 (lowest) and 9 (highest)
	 */
	private int priority;
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getPriority()
	{
		return priority;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	public String toString()
	{
		return title;
	}
	
	public int compareTo(Module m)
	{
		if (getPriority() < m.getPriority())
		{
			return 1;
		}
		else if (getPriority() > m.getPriority())
		{
			return -1;
		}
		else
		{
			return getTitle().compareTo(m.getTitle());
		}
	}
	
	public abstract Controller getController();
}
