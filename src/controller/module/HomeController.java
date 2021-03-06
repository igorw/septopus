package controller.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Book;
import module.Module;
import view.AboutView;
import view.HomeView;
import controller.tool.Loader;

/**
 * The home screen controller
 * Not really a controller because it's not a module
 * @author igor
 */
public class HomeController
{
	/**
	 * the current book
	 */
	private Book book;
	
	/**
	 * List of used modules
	 */
	private ArrayList<Module> modules = new ArrayList<Module>();
	
	/**
	 * The main window
	 */
	private HomeView view;
	
	/**
	 * Constructor
	 */
	public HomeController()
	{
		// add modules dynamically
		for (String file : new File("bin/module").list())
		{
			String moduleName = file.substring(0, file.indexOf("."));
			if (moduleName.endsWith("Module") && !moduleName.equals("Module"))
			{
				try
				{
					Class c = Class.forName("module." + moduleName);
					modules.add((Module)c.newInstance());
				}
				catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
				catch (InstantiationException e)
				{
					e.printStackTrace();
				}
				catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
			
		}
		
		Collections.sort(modules);
		
		view = new HomeView(modules);
		view.initGUI();

		view.addStartActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// launch module when start is pressed
				view.getSelectedModule().getController().setBook(getBook());
				view.getSelectedModule().getController().launch();
			}
		});
		
		view.addListListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				if (!e.getValueIsAdjusting())
				{
					// update description when list is selected
					view.setDesc(view.getSelectedModule().getDescription());
				}
			}
		});
		
		view.addSelectFileActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(view);
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					Loader loader = new Loader();
					try
					{
						Book b = loader.load(chooser.getSelectedFile());
						setBook(b);
						view.setSelectedFile(chooser.getSelectedFile());
					}
					catch (Exception e)
					{
					}
				}
			}
		});
		
		view.addHelpActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				AboutView view = new AboutView();
				view.initGUI();
			}
		});
	}
	
	/**
	 * set the book
	 * @param book
	 */
	private void setBook(Book book)
	{
		this.book = book;
	}
	
	/**
	 * get the book
	 * @return
	 */
	private Book getBook()
	{
		return book;
	}
	
	/**
	 * The main main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		HomeController home = new HomeController();
		home.getClass(); // dummy
	}
}
