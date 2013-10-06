package com.bbss.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bbss.constants.Constants;
import com.bbss.db.connection.Connect;
import com.bbss.welcome.MainMenu;

public class LoginScreen extends JFrame{
	private static final long serialVersionUID = 1L;

	private JLabel username,password,cat;
	private JLabel t1;
	public JTextField user;
	private JPasswordField pass;
	private JButton Login,Cancel;
	private JComboBox cboCat;
	private JPanel p1,p2,p3,p4;
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	
	public LoginScreen(){		
		super ("System Login");
		
		t1=new JLabel("Enter your username and password");
		username=new JLabel("Username");
		password=new JLabel("Password");               
		cat=new JLabel("Login As");
		
		user=new JTextField(10);
		pass=new JPasswordField(10);		
		cboCat=new JComboBox();
		
		cboCat.addItem("Manager");
		cboCat.addItem("Supervisor");
		cboCat.addItem("Booking Clerk");
		
		Login=new JButton ("Login", new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/ok.png"));
		Cancel=new JButton("Cancel",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));
		Login.setMnemonic(KeyEvent.VK_L);
		Cancel.setMnemonic(KeyEvent.VK_C);
		
		t1.setFont(new Font("verdana", Font.PLAIN, 12));
		t1.setForeground(Color.red);
		
		username.setFont(new Font("verdana", Font.PLAIN, 12));
		username.setForeground(Color.blue);
		
		password.setFont(new Font("verdana", Font.PLAIN, 12));
		password.setForeground(Color.blue);
		
		cat.setForeground(Color.blue);
		cat.setFont(new Font("verdana", Font.PLAIN, 12));
		cboCat.setFont(new Font("verdana", Font.PLAIN, 12));
		
		p3=new JPanel(new GridLayout(1,0));
		p3.add(t1);
		
		p1=new JPanel(new GridLayout(4,2));
		
		p1.add(username);p1.add(user);
		p1.add(password);p1.add(pass);
		p1.add(cat);p1.add(cboCat);
		p1.add(new JLabel(""));
		p1.add(Login);
		p1.add(Cancel);
        
		p2=new JPanel();
        p2.add(Login);	
        p2.add(Cancel);
       
        p4=new JPanel(new GridLayout(3,2));
        p4.add(p3);
        p4.add(p1);
        p4.add(p2);        

        ButtonListener listener=new ButtonListener();
		Login.addActionListener(listener);
		Cancel.addActionListener(listener);
        
        getContentPane().setLayout(new BorderLayout(0,0));
		getContentPane().add(p4);
		
		setSize(350,250);
		setVisible(true);
		
		setResizable(false);
		setLocation((screen.width - 500)/2,((screen.height-350)/2));	
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==Login){
				
				String password = new String(pass.getPassword());
				
				if (user.getText() == null ||user.getText().equals("")){   
					JOptionPane.showMessageDialog(null, "Enter Username", Constants.WARNING, JOptionPane.WARNING_MESSAGE);
					user.requestFocus();
					return;
				}
            
				if (password == null || password.equals("")){   
					JOptionPane.showMessageDialog(null, "Enter Password", Constants.WARNING, JOptionPane.WARNING_MESSAGE);
					pass.requestFocus();
					return;
				}
				
				login();
			}	
			
			if(e.getSource()==Cancel){
				System.exit(0);
			}	
		}
	};
	
	public void login(){
		
		String us = user.getText();
		String pas= new String(pass.getPassword());

		String SQL = "SELECT * FROM users WHERE username='" + us + "'  AND password='"+pas+"'AND Category='"+cboCat.getSelectedItem()+"'";
		
		try	{
			Statement stmt   = Connect.getConnection().createStatement();
			stmt.execute(SQL);
			ResultSet rs = stmt.getResultSet();
			
			if (rs.next()){
				LoginValidity();			
				dispose();
			}
			else{
				JOptionPane.showMessageDialog(null,"The system could not log you in.\n"+
						" Please make sure your Username and Password are correct.", Constants.WARNING, JOptionPane.WARNING_MESSAGE);
				user.setText("");
				pass.setText("");
			}
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
		}		
	}

	public void LoginValidity(){
		if (cboCat.getSelectedItem().equals("Manager")){
			new MainMenu().enabeManagerPermission();
		}
		else if (cboCat.getSelectedItem().equals("Supervisor")){
			new MainMenu().enabeSupervisorPermission();
		}
		else{
			new MainMenu().enableBookingClerkPermission();
		}
	}

	/*public static void main(String[]args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		new LoginScreen();
	}*/
}

