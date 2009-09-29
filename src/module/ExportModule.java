package module;

import controller.module.Controller;
import controller.module.ExportController;

/**
 * Module for exporting books
 */
public class ExportModule extends Module
{
	/**
	 * Constructor
	 */
	public ExportModule()
	{
		setTitle("Export");
		setDescription("Export to various formats.");
		setPriority(7);
	}

	/**
	 * Get Controller associated with module
	 */
	public Controller getController()
	{
		return new ExportController();
	}
}
