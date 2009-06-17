package controller.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Book;
import view.HomeView;
import controller.tool.Loader;

/**
 * The home screen
 * @author igor
 */
public class HomeController
{
	private Book book;
	private ArrayList<Module> modules = new ArrayList<Module>();
	
	private HomeView view;
	
	public HomeController()
	{
		// add modules
		modules.add(new TrainerController());
		modules.add(new PresentationController());
		modules.add(new ExportController());
		Collections.sort(modules);
		
		view = new HomeView(modules);
		view.initGUI();

		view.addStartActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				view.getSelectedModule().launch(getBook());
			}
		});
		
		view.addListListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				if (!e.getValueIsAdjusting())
				{
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
	}
	
	private void setBook(Book book)
	{
		this.book = book;
	}
	
	private Book getBook()
	{
		return book;
	}
	
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
