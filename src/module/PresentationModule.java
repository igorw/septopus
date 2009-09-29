package module;

import controller.module.Controller;
import controller.module.PresentationController;

public class PresentationModule extends Module
{
	/**
	 * Constructor
	 */
	public PresentationModule()
	{
		setTitle("Presentation");
		setDescription("Watch your vocab.");
		setPriority(8);
	}

	/**
	 * Get Controller associated with module
	 */
	public Controller getController()
	{
		return new PresentationController();
	}
}
