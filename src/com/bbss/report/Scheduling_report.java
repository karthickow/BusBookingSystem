package com.bbss.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.bbss.constants.Constants;
import com.bbss.db.connection.Connect;

public class Scheduling_report extends JFrame  {
	private static final long serialVersionUID = -6464473330403847964L;
	public Container content;
	public JPanel reportingPanel;
	public JTabbedPane listsTabs;
	public JTextArea listPane;
	public JPanel reportPanel;
	public JPanel statusPanel;
	public JComboBox graphTypesCombo;
	public Color skyblue=new Color(150,190,255);
	public 	final ImageIcon imageIcon = new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/cool.png");
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	Statement stmt = null;
	private JButton print,cancel;
	private JPanel panel;	

	@SuppressWarnings("serial")
	public Scheduling_report()
	{ 
		super("Buses Report");

		content=getContentPane();
		content.setBackground(skyblue);
		listsTabs=new JTabbedPane();
		print=new JButton("PRINT ",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/prints.png"));
		cancel=new JButton("CANCEL",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));
		panel=new JPanel();
		panel.add(print);
		panel.add(cancel);

		reportingPanel=new JPanel();
		reportingPanel.setLayout(new BorderLayout());
		reportingPanel.setBorder(BorderFactory.createEtchedBorder());
		reportingPanel.add(new JLabel("Scheduling Report"),BorderLayout.NORTH);
		reportingPanel.add(panel,BorderLayout.SOUTH);
		reportPanel=new JPanel();
		reportPanel.setLayout(new GridLayout(1,1));
		reportPanel.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.blue));
		reportPanel.setBackground(Color.white);

		reportingPanel.add(new JScrollPane(reportPanel),BorderLayout.CENTER);
		listsTabs.add(reportingPanel);
		setLocation((screen.width-1270)/2,((screen.height-740)/2));
		setResizable(false);
		listPane = new JTextArea() {
			Image image = imageIcon.getImage();
			{
				setOpaque(false);
			} 

			public void paint(Graphics g) {
				g.drawImage(image, 340, 30, this);
				g.setColor(Color.blue);

				g.drawString("Phone: 24540000: Cellphone: 9994265605",385,70);
				g.drawString("Fax: +254 720576879 ",385,90);
				g.drawString("Address: Box 600041, Chennai, Tamilnadu ",385,110);
				g.drawString("Email:kaviyak@gmail.com",385,140);
				g.drawString("Website:www.busbook.com",385,170);
				g.setColor(Color.black);
				super.paint(g);
			}
		};


		listPane.setEditable(false);
		listPane.setFont(new Font("Serif", Font.BOLD, 12));
		listPane.setForeground(Color.black);

		listPane.setLineWrap(true);
		listPane.setWrapStyleWord(true);
		reportPanel.add(listPane);

		printList();

		content.add(listsTabs,BorderLayout.CENTER);

		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();

			}
		});

		setSize(1000,720);
		setVisible(true);

	}

	private void printList() 
	{
		try {

			ResultSet rst=Connect.getConnection().createStatement(
					/*ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE*/).executeQuery("select Bus_No,Route_No,empNo,Trip_No,Date_Scheduled from Schedules");


			listPane.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			listPane.append("Bus_No"+"\t\t"+"Route_No"+"\t\t"+"Employee_No"+"\t\t"+"Trip_No\t\t"+"Date_Scheduled\n");
			while (rst.next())
			{
				listPane.append("       ");
				listPane.append(rst.getString(1).trim());
				listPane.append("\t\t");
				listPane.append(rst.getString(2).trim());
				listPane.append("\t\t");
				listPane.append(rst.getString(3).trim());
				listPane.append("\t\t");
				listPane.append(rst.getString(4).trim());
				listPane.append("\t\t");
				listPane.append(rst.getString(5).trim());

				listPane.append("\n\n");
			}


			if (rst != null)
				rst.close();

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, " No Records found"
					+ sqle.getMessage());
			return;
		}
	}

}
