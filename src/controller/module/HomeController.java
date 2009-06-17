package controller.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.UIManager;

import model.Book;
import model.Word;
import sun.security.action.GetBooleanAction;
import view.HomeView;

/**
 * The home screen
 * @author igor
 */
public class HomeController
{
	private Book book;
	private ArrayList<ModuleBase> modules = new ArrayList<ModuleBase>();
	
	private HomeView view;
	
	public HomeController()
	{
		// add modules
		modules.add(new TrainerController());
		modules.add(new PresentationController());
		Collections.sort(modules);

		// init book
		book = new Book();
		book.addWord(new Word("fun", "Spass"));
		book.addWord(new Word("car", "Auto"));
		book.addWord(new Word("money", "Geld"));
		
		view = new HomeView(modules);
		view.initGUI();

		view.addStartActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getSelectedModule().launch(getBook());
			}
		});
	}
	
	private Book getBook()
	{
		return book;
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		HomeController home = new HomeController();
	}
}
