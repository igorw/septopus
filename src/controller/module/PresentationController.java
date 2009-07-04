package controller.module;

import model.Book;
import model.Word;
import view.PresentationView;

public class PresentationController extends Controller
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
	
	public boolean nextWord()
	{
		boolean result;
		
		if (i >= getBook().size())
		{
			result = false;
		}
		else
		{
			result = true;
			word = getBook().get(i++);
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
