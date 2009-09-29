package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;

import module.Module;

/**
 * The home window
 */
public class HomeView extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menu = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JMenuItem menuFileOpen = new JMenuItem("Open file");
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem menuHelpAbout = new JMenuItem("About");
	private JList lsModules;
	private JTextArea taModuleDesc = new JTextArea();
	private JButton btStart = new JButton("Start");
	private JLabel lbSelectedValue = new JLabel("");
	
	private ArrayList<Module> modules;
	
	public HomeView(ArrayList<Module> modules)
	{
		this.modules = modules;
	}

	/**
	 * Initialise the GUI
	 */
	public void initGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setTitle("Septopus");
		
		setIconImage(new ImageIcon("septopus.png").getImage());
		
		setSize(400, 275);
		setContentPane(new JPanel());
		setJMenuBar(menu);

		menu.add(menuFile);
		menuFile.add(menuFileOpen);

		menu.add(menuHelp);
		menuHelp.add(menuHelpAbout);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		getContentPane().setLayout(layout);

		JLabel lbSelected = new JLabel("Selected file:");
		lsModules = new JList();
		
		DefaultListModel lm = new DefaultListModel();
		lsModules.setModel(lm);
		for (Module m : modules)
		{
			lm.addElement(m);
		}

		lsModules.setFont(new Font(null, Font.PLAIN, 18));
		lsModules.setMinimumSize(new Dimension(50, 150));
		
		taModuleDesc.setEditable(false);
		taModuleDesc.setFont(new Font(null, Font.PLAIN, 10));
		
		btStart.setFont(new Font(null, Font.BOLD, 12));
		btStart.setEnabled(false);
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lbSelected)
					.addComponent(lsModules)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lbSelectedValue)
					.addComponent(taModuleDesc)
					.addComponent(btStart, GroupLayout.Alignment.TRAILING)
				)
		);
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lbSelected)
					.addComponent(lbSelectedValue)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lsModules)
					.addComponent(taModuleDesc)
				)
				.addComponent(btStart)
		);
		
		setVisible(true);
	}
	
	public void setDesc(String desc)
	{
		taModuleDesc.setText(desc);
	}
	
	public void addStartActionListener(ActionListener l)
	{
		btStart.addActionListener(l);
	}
	
	public void addListListener(ListSelectionListener l)
	{
		lsModules.addListSelectionListener(l);
	}
	
	public Module getSelectedModule()
	{
		return (Module)lsModules.getSelectedValue();
	}
	
	public void addSelectFileActionListener(ActionListener l)
	{
		menuFileOpen.addActionListener(l);
	}
	
	public void setSelectedFile(File selectedFile)
	{
		lbSelectedValue.setText(selectedFile.getName());
		btStart.setEnabled(true);
	}
	
	public void addHelpActionListener(ActionListener l)
	{
		menuHelpAbout.addActionListener(l);
	}
}
