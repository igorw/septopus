package module;

import controller.module.Controller;
import controller.module.TrainerController;

public class TrainerModule extends Module
{
	/**
	 * Constructor
	 */
	public TrainerModule()
	{
		setTitle("Trainer");
		setDescription("Test your vocabulary until you crack.");
		setPriority(9);
	}

	/**
	 * Get Controller associated with module
	 */
	public Controller getController()
	{
		return new TrainerController();
	}
}
