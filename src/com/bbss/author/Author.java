package com.bbss.author;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.bbss.constants.Constants;

public class Author extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel me;
	private JButton exit;
	private JTextArea about;
	private JPanel pan, pan2;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	public Author() {
		super(Constants.TITLE_AUTHOR);
		me = new JLabel(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/aut.png"));
		//view = new JButton("More Info");
		exit = new JButton("Exit");

		about = new JTextArea(Constants.ABOUT);
		about.setForeground(Color.blue);
		pan2 = new JPanel();
		//pan2.add(view);
		pan2.add(exit);
		pan = new JPanel(new GridLayout(2, 2));
		pan.setPreferredSize(new Dimension(450, 300));

		pan.add(me);
		pan.add(new JScrollPane(about));

		pan.add(pan2);

		getContentPane().add(pan);

		pack();
		setVisible(true);
		setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
		exit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				setVisible(true);
				dispose();
			}
		});
	}

}