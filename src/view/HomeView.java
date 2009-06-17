package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import model.Book;
import model.Word;
import controller.module.ModuleBase;

public class HomeView extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menu = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JList lsModules;
	private JTextArea taModuleDesc = new JTextArea();
	private JButton btStart = new JButton("Start");
	
	private ArrayList<ModuleBase> modules;
	
	public HomeView(ArrayList<ModuleBase> modules)
	{
		this.modules = modules;
	}
	
	public void initGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setTitle("Septopus");
		
		setIconImage(new ImageIcon("septopus.png").getImage());
		
		setSize(400, 275);
		setContentPane(new JPanel());
		setJMenuBar(menu);

		menuFile.add(new JMenuItem("test"));
		menu.add(menuFile);
		
		GroupLayout layout = new GroupLayout(getContentPane());
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
		getContentPane().setLayout(layout);

		JLabel lbSelected = new JLabel("Selected file:");
		JLabel lbSelectedValue = new JLabel("file");
		lsModules = new JList();
		
		DefaultListModel lm = new DefaultListModel();
		lsModules.setModel(lm);
		for (ModuleBase m : modules)
		{
			lm.addElement(m);
		}
		lsModules.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())
				{
					ModuleBase m = (ModuleBase) ((JList) e.getSource()).getSelectedValue();
					setDesc(m.getDescription());
				}
			}
		});

		lsModules.setFont(new Font(null, Font.PLAIN, 18));
		lsModules.setMinimumSize(new Dimension(50, 150));
		
		taModuleDesc.setEditable(false);
		taModuleDesc.setFont(new Font(null, Font.PLAIN, 10));
		
		btStart.setFont(new Font(null, Font.BOLD, 12));
		
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
	
	public ModuleBase getSelectedModule()
	{
		return (ModuleBase)lsModules.getSelectedValue();
	}
}
