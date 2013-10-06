package com.bbss.payment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.bbss.constants.Constants;


class Receipt extends JFrame {
	private static final long serialVersionUID = 5407324225301082439L;
	public Container content;
	public JPanel reportingPanel;
	public JTabbedPane listsTabs;
	public JPanel chartPanel;
	public JButton hide;
	public JTextArea listPane;
	public JPanel reportPanel;
	public JButton drawGraphButton;
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	public int ID;
	public Color skyblue=new Color(150,190,255);
	public 	final ImageIcon imageIcon = new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/cool.png");
	private JButton print,cancel;
	private JPanel panel;	
	Statement stmt = null;
	public Receipt()
	{

		super("Receipt");

		content=getContentPane();
		content.setBackground(skyblue);

		print=new JButton("PRINT ",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/prints.png"));
		cancel=new JButton("CANCEL",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));
		panel=new JPanel();
		panel.add(print);
		panel.add(cancel);
		reportingPanel=new JPanel();
		reportingPanel.setLayout(new BorderLayout());
		reportingPanel.setBorder(BorderFactory.createEtchedBorder());
		reportingPanel.add(new JLabel("Receipt for Payment"),BorderLayout.NORTH);
		reportingPanel.add(panel,BorderLayout.SOUTH);
		reportPanel=new JPanel();
		reportPanel.setLayout(new GridLayout(1,1));
		reportPanel.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.blue));
		reportPanel.setBackground(Color.white);

		reportingPanel.add(new JScrollPane(reportPanel),BorderLayout.CENTER);

		produceCertificate();
		listPane.setEditable(false);
		listPane.setFont(new Font("Serif", Font.PLAIN, 12));
		listPane.setForeground(Color.black);

		listPane.setLineWrap(true);
		listPane.setWrapStyleWord(true);
		reportPanel.add(listPane);
		setLocation((screen.width-1270)/2,((screen.height-740)/2));
		setResizable(false);

		JPanel dpanel=new JPanel();
		dpanel.setBorder(BorderFactory.createLoweredBevelBorder());
		dpanel.setLayout(new GridLayout(1,1));
		//DateFormat defaultDate=DateFormat.getDateInstance(DateFormat.FULL);
		content.add(reportingPanel,BorderLayout.CENTER);

		setLocation(5,0);
		setSize(780,700);
		setVisible(true);

		cancel.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e){
				dispose();
			}
		});
	}
	@SuppressWarnings("serial")
	public void produceCertificate()
	{
		listPane = new JTextArea() {
			Image image = imageIcon.getImage();
			{
				setOpaque(false);
			} 

			public void paint(Graphics g) {
				g.drawImage(image, 340, 30, this);
				g.setColor(Color.blue);

				g.drawString("Phone: 044245400: Cellphone: 9994265605",385,70);
				g.drawString("Fax: +254 720576879 ",385,90);
				g.drawString("Address: Box 600041, Chennai, Tamilnadu ",385,110);
				g.drawString("Email:kaviayak@gmail.com",385,140);
				g.drawString("Website:www.busbook.com",385,170);
				g.setColor(Color.black);
				g.drawString("PAYMENT RECEIPT",365,200);
				g.drawString("Payment Number      "+new Payment().text1.getText(),80,230);
				g.drawString("Name of Passenger   "+new Payment().combo2.getSelectedItem(),80,260);

				g.drawString("Amount Paid Kshs    "+new Payment().combo8.getSelectedItem(),80,290);
				g.drawString("Pay on              "+new Payment().combo4.getSelectedItem(),80,320);
				g.drawString("Date Paid           "+new Payment().p_date.getText(),620,350);
				g.drawString("Received By         "+new Payment().combo3.getSelectedItem(),80,380);

				g.setColor(Color.red);
				g.drawString("Welcome back!!! Safe Journey!!!",200,410);


				super.paint(g);
			}
		};
	}

/*	public static void main(String [] args)
	{
		new Receipt();

	}
*/


}