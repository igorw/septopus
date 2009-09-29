package controller.module;

import model.Word;
import view.PresentationView;

/**
 * Controller for the presentation
 */
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
	
	/**
	 * The window
	 */
	private PresentationView view;
	
	/**
	 * increment word
	 * @return word was updated
	 */
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

	public void launch()
	{
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
