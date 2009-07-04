package controller.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFileChooser;

import model.Book;
import model.Word;
import view.TrainerEndView;
import view.TrainerView;
import controller.exporter.Exporter;
import controller.exporter.PlainExporter;


/**
 * The trainer is the main program for voc training
 * @author igor
 * @todo move sysout stuff to a new cli-view
 */
public class TrainerController extends Controller
{
	/**
	 * A book that contains all wrong answers
	 */
	private Book wrongAnswers = new Book();
	
	/**
	 * When set, the questions are randomised (yes, i'm British)
	 */
	private boolean random = true;
	
	private TrainerView view;
	
	private int index = 0;
	
	/**
	 * Set randomisation
	 * @param random value for random
	 */
	public void setRandom(boolean random)
	{
		this.random = random;
	}
	
	/**
	 * Launch the module
	 */
	public void launch(Book book)
	{
		setBook(book);
		if (book.getName().endsWith("_wrong"))
		{
			wrongAnswers.setName(book.getName());
		}
		else
		{
			wrongAnswers.setName(book.getName() + "_wrong");
		}
		
		// reset
		index = 0;
		random = true;
		
		view = new TrainerView();
		view.initGUI();
		
		view.addNextActionListener(new NextListener());
		view.addForeignKeyListener(new NextListener());
		
		// shuffle book
		if (random)
		{
			book.shuffle();
		}
		
		Word word = thisWord();
		view.setHome(word.getHome());
		
		/**
		 * @todo display wrong words
		 */
	}
	
	private Word nextWord()
	{
		if (index + 1 == getBook().size())
		{
			return null;
		}
		
		return getBook().get(++index);
	}
	
	private Word thisWord()
	{
		if (index == getBook().size())
		{
			return null;
		}
		
		return getBook().get(index);
	}
	
	private class NextListener implements ActionListener, KeyListener
	{
		private boolean lastWrong = false;
		
		private Word word;
		
		private TrainerEndView endView;
		
		public void perform()
		{
			String input = view.getForeign();
			word = thisWord();
			
			if (lastWrong)
			{
				lastWrong = false;
				next();
				return;
			}
			
			if (word == null)
			{
				endSession();
				return;
			}
			
			if (!input.trim().equals(word.getForeign()))
			{
				wrongAnswers.addWord(word);
				view.setCorrection(word.getForeign());
				lastWrong = true;
				return;
			}
			
			next();
		}
		
		private void next()
		{
			word = nextWord();
			
			if (word == null)
			{
				endSession();
				return;
			}
			
			view.setHome(word.getHome());
			view.setForeign("");
			view.setCorrection("");
			view.requestForeignFocus();
		}
		
		private void endSession()
		{
			view.setVisible(false);
			
			endView = new TrainerEndView();
			endView.initGUI();
			endView.addRetryActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					TrainerController trainer = new TrainerController();
					trainer.launch(wrongAnswers);
					endView.setVisible(false);
				}
			});
			endView.addSaveActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					JFileChooser chooser = new JFileChooser();
					int returnVal = chooser.showOpenDialog(endView);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						Exporter ex = new PlainExporter();
						ex.setBook(getBook());
						try
						{
							ex.export(chooser.getSelectedFile());
						}
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
					}
					endView.setVisible(false);
				}
			});
			endView.addCancelActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					endView.setVisible(false);
				}
			});
		}
		
		public void actionPerformed(ActionEvent e)
		{
			perform();
		}

		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				perform();
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}
