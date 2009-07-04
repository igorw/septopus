package module;

import controller.module.Controller;
import controller.module.PresentationController;

public class PresentationModule extends Module
{
	public PresentationModule()
	{
		setTitle("Presentation");
		setDescription("Watch your vocab.");
		setPriority(8);
	}

	public Controller getController()
	{
		return new PresentationController();
	}
}
