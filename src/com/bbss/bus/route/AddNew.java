package com.bbss.bus.route;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bbss.constants.Constants;
import com.bbss.db.connection.Connect;

public class AddNew extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel routeNo,routeName,From,To,Distance,Amount;

	private JTextField txtRouteNo,
	txtRouteName,txtFrom,txtTo,txtDistance,txtAmount;
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	private JButton AddNew;
	private JButton Cancel;
	private JButton Clear;
	private JPanel jPanel1;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;

	public AddNew(){
		super("Add New Route");

		setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
		setResizable(true);

		routeNo = new JLabel("Route No ");
		routeName = new JLabel("Route Name ");
		From = new JLabel("From");
		To = new JLabel("To");
		Distance = new JLabel("Distance");
		Amount=new JLabel("Fare_Charged");
		txtRouteNo = new JTextField(10);
		txtRouteName = new JTextField(10);
		txtFrom= new JTextField(10);
		txtTo = new JTextField(10);
		txtAmount=new JTextField(10);
		txtDistance = new JTextField(15);
		AddNew = new JButton("ADD NEW",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/addrecord.png"));
		Cancel = new JButton("CANCEL",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));
		Clear=new JButton ("CLEAR",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/delete.png"));

		jPanel1 = new JPanel(new java.awt.GridLayout(6,2));

		jPanel1.add(routeNo);jPanel1.add(txtRouteNo);
		jPanel1.add(routeName);jPanel1.add(txtRouteName);
		jPanel1.add(From);jPanel1.add(txtFrom);
		jPanel1.add(To);jPanel1.add(txtTo);
		jPanel1.add(Distance);jPanel1.add(txtDistance);
		jPanel1.add(Amount);jPanel1.add(txtAmount);
		jPanel3 = new javax.swing.JPanel(new FlowLayout());

		jPanel3.add(jPanel1);


		jPanel4 = new javax.swing.JPanel(new FlowLayout());

		jPanel4.add(AddNew);
		jPanel4.add(Cancel);
		jPanel4.add(Clear);
		setLocation((screen.width - 500)/2,((screen.height-350)/2));	
		setResizable(false);

		generator();
		txtRouteName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c==KeyEvent.VK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {

					getToolkit().beep();
					JOptionPane.showMessageDialog(null,"This field only accept text",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});
		txtTo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c==KeyEvent.VK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {

					getToolkit().beep();
					JOptionPane.showMessageDialog(null,"This field only accept text",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});
		txtFrom.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c==KeyEvent.VK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {

					getToolkit().beep();
					JOptionPane.showMessageDialog(null,"This field only accept text",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});
		txtAmount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c==KeyEvent.VK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {

					getToolkit().beep();
					JOptionPane.showMessageDialog(null,"Amount is in Digit",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});
		txtDistance.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c==KeyEvent.VK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {

					getToolkit().beep();
					JOptionPane.showMessageDialog(null,"Distance in digit",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});


		AddNew.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				if (txtRouteNo.getText() == null ||
						txtRouteNo.getText().equals("")){   
					JOptionPane.showMessageDialog(null,"Enter route number",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					txtRouteNo.requestFocus();
					return;}
				if (txtRouteName.getText() == null ||
						txtRouteName.getText().equals("")){   
					JOptionPane.showMessageDialog(null,"Enter route name",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					txtRouteName.requestFocus();
					return;}
				if (txtFrom.getText() == null ||
						txtFrom.getText().equals("")){   
					JOptionPane.showMessageDialog(null,"Enter From",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					txtFrom.requestFocus();
					return;}
				if (txtTo.getText() == null ||
						txtTo.getText().equals("")){   
					JOptionPane.showMessageDialog(null,"Enter To",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					txtTo.requestFocus();
					return;}
				if (txtDistance.getText() == null ||
						txtDistance.getText().equals("")){   
					JOptionPane.showMessageDialog(null,"Enter Distance",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					txtDistance.requestFocus();
					return;}
				if (txtAmount.getText() == null ||
						txtAmount.getText().equals("")){   
					JOptionPane.showMessageDialog(null,"Enter Distance",Constants.ERROR,
							JOptionPane.ERROR_MESSAGE);
					txtAmount.requestFocus();
					return;}
				try{
					Statement statement = Connect.getConnection().createStatement();
					{
						String temp = "INSERT INTO Route (Route_No, RouteName, Depot, Destination, Distance,Fare_Charged) VALUES ('"+

                                              txtRouteNo.getText() 	     + "', '" +  
                                              txtRouteName.getText()     + "', '" +  
                                              txtFrom.getText()	         + "', '" +  
                                              txtTo.getText()	         + "', '" + 
                                              txtDistance.getText()      +"',  '" +
                                              txtAmount.getText() 	     + "')";

						statement.executeUpdate( temp );
						String ObjButtons[] = {"Yes","No"};
						int PromptResult = JOptionPane.showOptionDialog(null,"Record succesfully added.Do you want to add another?",
								Constants.SUPER_TITLE,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
						if (PromptResult==0){
							//txtRouteNo.setText("");
							generator();
							txtRouteName.setText("");
							txtFrom.setText("");
							txtTo.setText("");
							txtDistance.setText("");


						}
						else{
							setVisible(false);
						}
					}

				}
				catch ( SQLException sqlex )

				{
					sqlex.printStackTrace();

				}
			}
		});

		Cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				setVisible(true);
				dispose();
			}
		});

		Clear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				txtRouteNo.setText ("");
				txtRouteName.setText ("");
				txtFrom.setText ("");
				txtTo.setText ("");
				txtDistance.setText ("");
				txtAmount.setText("");
			}
		});

		jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

		jPanel5.add(jPanel3, BorderLayout.CENTER);
		jPanel5.add(jPanel4, BorderLayout.SOUTH);

		getContentPane().add(jPanel5);

		pack();
		setVisible(true);
	}

/*	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new LoginScreen().setVisible(true);

		new AddNew().setVisible(false);
	}*/
	private void generator()
	{
		try
		{
			ResultSet rst=Connect.getConnection().createStatement(
					/*ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE*/).executeQuery("SELECT Route_No FROM Route");
			while(rst.next())
			{
				String s;
				int number=rst.getInt(1);
				number=number+1;

				s=""+number;
				txtRouteNo.setText(s);

			}	
		}catch(Exception n){
			n.printStackTrace();
		}
	}
}