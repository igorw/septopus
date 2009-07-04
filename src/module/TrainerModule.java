package module;

import controller.module.Controller;
import controller.module.TrainerController;

public class TrainerModule extends Module
{
	public TrainerModule()
	{
		setTitle("Trainer");
		setDescription("Test your vocabulary until you crack.");
		setPriority(9);
	}

	public Controller getController()
	{
		return new TrainerController();
	}
}
