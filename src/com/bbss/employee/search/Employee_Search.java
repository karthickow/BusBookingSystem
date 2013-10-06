package com.bbss.employee.search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Employee_Search extends JFrame{
	private static final long serialVersionUID = -9023006881058900056L;
	private JPanel panel1,panel2;
	private JLabel label1;
	private JTextField text1;
	private JButton button1,button2;
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	
	public Employee_Search (){
		super("Searching Record");
		label1=new JLabel("ENTER EMPLOYEE_NO" );
		text1=new JTextField(10);
		button1=new JButton("Search");
		button2=new JButton("Cancel");
		label1.setForeground(Color.blue);
		panel1=new JPanel(new GridLayout(2,2));
		panel1.add(label1);panel1.add(text1);
		panel1.add(button1);panel1.add(button2);

		panel2=new JPanel();
		panel2.add(panel1);
		//getContentPane().setLayout(new BorderLayout());
		getContentPane().add( panel2);

		pack();
		setSize(300,100);
		setVisible(true);
		setLocation((screen.width - 500)/2,((screen.height-350)/2));
		button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

			}
		});
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});	

	}
	/*public static void main(String []args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Employee_Search().setVisible(true);
	}*/
}