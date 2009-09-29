package module;

import controller.module.Controller;


/**
 * A module for the program
 * @author igor
 */
public abstract class Module implements Comparable<Module>
{
	/**
	 * The module title
	 */
	private String title;
	
	/**
	 * The module description
	 */
	private String description;
	
	/**
	 * Priority between 0 (lowest) and 9 (highest)
	 */
	private int priority;
	
	/**
	 * Getter for title
	 * @return title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Setter for title
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Getter for description
	 * @return description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Setter for description
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Getter for priority
	 * @return priority
	 */
	public int getPriority()
	{
		return priority;
	}

	/**
	 * Setter for priority
	 * @param priority
	 */
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	/**
	 * Get string representation
	 */
	public String toString()
	{
		return title;
	}
	
	/**
	 * Compare module to another
	 */
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
	
	/**
	 * Get Controller associated with module
	 * @return controller
	 */
	public abstract Controller getController();
}
