package module;

import controller.module.Controller;
import controller.module.ExportController;

public class ExportModule extends Module
{
	public ExportModule()
	{
		setTitle("Export");
		setDescription("Export to various formats.");
		setPriority(7);
	}

	public Controller getController()
	{
		return new ExportController();
	}
}
