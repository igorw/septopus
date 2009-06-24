package controller.module;

import model.Book;
import model.Word;
import view.PresentationView;

public class PresentationController extends Module
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

	public void launch(Book book)
	{
		setBook(book);
		
		// reset
		i = 0;
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				view = new PresentationView();
				view.initGUI();
				
				try
				{
					while (nextWord())
					{
						view.showHome();
						
						Thread.sleep(2000);

						view.showForeign();
						
						Thread.sleep(4000);
						
						view.showNone();
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				view.setVisible(false);
			}
		});
		t.start();
		
		/*try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}
}
