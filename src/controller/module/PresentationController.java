package controller.module;

import model.Book;
import model.Word;
import view.PresentationView;

public class PresentationController extends ModuleBase
{
	/**
	 * Current index
	 */
	private int i = 0;

	/**
	 * Current word
	 */
	private Word word;
	
	private PresentationView view;
	
	/**
	 * Constructor
	 * @param book
	 */
	public PresentationController()
	{
		setTitle("Presentation");
		setDescription("Watch your vocab.");
		setPriority(8);
	}
	
	public boolean nextWord()
	{
		boolean result;
		
		if (i >= book.size())
		{
			result = false;
		}
		else
		{
			result = true;
			word = book.get(i++);
		}
		
		if (result)
		{
			view.setForeign(word.getForeign());
			view.setHome(word.getHome());
		}
		
		return result;
	}
	
	/**
	 * Present the presentation
	 * @throws InterruptedException
	 */
	public void present() throws InterruptedException
	{
		while (nextWord())
		{
			view.showHome();
			
			Thread.sleep(2000);

			view.showForeign();
			
			Thread.sleep(4000);
			
			view.showNone();
		}
		
		view.setVisible(false);
	}

	public void launch(Book book)
	{
		setBook(book);
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				view = new PresentationView();
				view.initGUI();
				
				try {
					present();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
}
