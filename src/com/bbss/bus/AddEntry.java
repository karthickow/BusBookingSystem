package com.bbss.bus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

import com.bbss.button.DateButton;
import com.bbss.constants.Constants;
import com.bbss.db.connection.Connect;

public class AddEntry extends JFrame {

    private static final long serialVersionUID = -7463793967860489365L;

	private JLabel BusNo,RegNo,Model,Capacity,DP,Ins,DI,DE;
	
	private JTextField txtBusNo,txtRegNo,txtModel,txtCapacity,txtIns;
	
	private JButton AddNew,Cancel,Clear;
	private JPanel jPanel1;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private DateButton date_bought;
    private DateButton date_ins;
    private DateButton date_expiry;
    Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
    
    public AddEntry(){		
		super("New Bus Entry");
               
		setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		BusNo = new JLabel("Bus Number ");
		RegNo = new JLabel("Reg Number ");
		Model = new JLabel("Model");
		Capacity = new JLabel("Capacity ");
        DP = new JLabel("Date Purchased");
        Ins = new JLabel("Insurance Status");
        DI = new JLabel("Date Insured");
        DE = new JLabel("Insurance Expiry Date");
		
		txtBusNo = new JTextField(10);
		txtRegNo = new JTextField(10);
		txtModel = new JTextField(10);
		txtCapacity = new JTextField(10);
        txtIns = new JTextField(10);

		txtBusNo.setForeground(Color.blue);
		AddNew = new JButton("ADD RECORD",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/add.png"));
		Cancel = new JButton("CANCEL",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));
        Clear=new JButton ("CLEAR",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/delete.png"));
	    date_bought = new DateButton();
	    date_ins=new DateButton();
	    date_expiry=new DateButton();
	    
	    date_ins.setForeground(Color.red);
	    date_bought.setForeground(Color.red);
	    date_expiry.setForeground(Color.red);
		
		jPanel1 = new JPanel(new GridLayout(8,2));
        jPanel1.setPreferredSize(new Dimension(400,250));
		jPanel1.add(BusNo);jPanel1.add(txtBusNo);
		jPanel1.add(RegNo);jPanel1.add(txtRegNo);
		jPanel1.add(Model);jPanel1.add(txtModel);
		jPanel1.add(Capacity);jPanel1.add(txtCapacity);
        jPanel1.add(DP);jPanel1.add(date_bought);
        jPanel1.add(Ins);jPanel1.add(txtIns);
        jPanel1.add(DI);jPanel1.add(date_ins);
        jPanel1.add(DE);jPanel1.add(date_expiry);
       
		jPanel3 = new JPanel(new java.awt.FlowLayout());
		jPanel3.add(jPanel1);
	

		jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());
		jPanel4.add(AddNew);
		jPanel4.add(Cancel);
		jPanel4.add(Clear);
		
		generator();		 
		setLocation((screen.width - 500)/2,((screen.height-350)/2));
		setResizable(false);	
		
		txtModel.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(null,"This field only accept text",Constants.ERROR, JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});
       
		txtIns.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(null,"This field only accept text",Constants.ERROR, JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});
       
       
		txtCapacity.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				JTextField textField =  (JTextField)e.getSource();
				String content = textField.getText();
				
				if (content.length() != 0) {
					try {
						Integer.parseInt(content);
					}catch (NumberFormatException nfe) {
						getToolkit().beep();
						JOptionPane.showMessageDialog(null,Constants.INVALID,Constants.ERROR, JOptionPane.ERROR_MESSAGE);
						textField.requestFocus();
						txtCapacity.setText("");
					}
				}
			}
		});
       
		AddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtBusNo.getText() == null || txtBusNo.getText().equals("")){   
					JOptionPane.showMessageDialog(null,"Enter bus number",Constants.WARNING,JOptionPane.WARNING_MESSAGE);
					txtBusNo.requestFocus();
					return;
				}
      
				if (txtRegNo.getText() == null || txtRegNo.getText().equals("")){   
					JOptionPane.showMessageDialog(null,"Enter Reg Number",Constants.WARNING, JOptionPane.WARNING_MESSAGE);
					txtRegNo.requestFocus();
					return;
				}
				if (txtModel.getText() == null || txtModel.getText().equals("")) {
					JOptionPane.showMessageDialog(null,	"Model Field is required", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
					txtModel.requestFocus();
					return;
				}
				if (txtCapacity.getText() == null || txtCapacity.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter bus capacity", Constants.WARNING, JOptionPane.WARNING_MESSAGE);
					txtCapacity.requestFocus();
					return;
				}

				int caps = Integer.parseInt(txtCapacity.getText());
				if (caps != 45 && caps != 35) {
					JOptionPane.showMessageDialog(null, "Bus Capacity is invalid", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
					txtCapacity.requestFocus();
					return;
				}

				if (txtIns.getText() == null || txtIns.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insurance status entry is required", Constants.ERROR, JOptionPane.ERROR_MESSAGE);
					txtIns.requestFocus();
					return;
				}  
      
				try {
					Statement statement = Connect.getConnection().createStatement();

					String temp = "INSERT INTO Buses (BusNo, Bus_RegNo, Model, Capacity, DateBought,Insurance_Status,Date_Insured,Insurance_Expiry) VALUES ('"
							+

							txtBusNo.getText()
							+ "', '"
							+ txtRegNo.getText()
							+ "', '"
							+ txtModel.getText()
							+ "', '"
							+ txtCapacity.getText()
							+ "', '"
							+ date_bought.getText()
							+ "', '"
							+ txtIns.getText()
							+ "', '"
							+ date_ins.getText()
							+ "', '"
							+ date_expiry.getText() + "')";

					statement.executeUpdate(temp);
					String ObjButtons[] = { "Yes", "No" };
					int PromptResult = JOptionPane.showOptionDialog(null,"Record succesfully added.Do you want to add another?", Constants.SUPER_TITLE, JOptionPane.DEFAULT_OPTION,
									JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
					if (PromptResult == 0) {
						generator();

						txtRegNo.setText("");
						txtModel.setText("");
						txtCapacity.setText("");
						txtIns.setText("");
					}
					else{
						setVisible(false);
					}
				}catch (SQLException sqlex){
					sqlex.printStackTrace();
				}
			}
		});

		Cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				setVisible(true);
				dispose();
				new DateButton().setVisible(true);
			}
		});
		
		Clear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				txtBusNo.setText ("");
				txtRegNo.setText ("");
				txtModel.setText ("");
				txtCapacity.setText ("");
				txtIns.setText ("");
			}
		});

		jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

		jPanel5.add(jPanel3, BorderLayout.CENTER);
		jPanel5.add(jPanel4, BorderLayout.SOUTH);

		getContentPane().add(jPanel5);

		pack();
		setVisible(true);
    }
    
    private void generator(){
    	try{
		    ResultSet rst=Connect.getConnection().createStatement(
			/*ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE*/).executeQuery("select * from Buses where BusNo =(SELECT Max(Buses.BusNo) AS MaxOfBusNo FROM Buses)");
			txtBusNo.setText("1000");
			
			while(rst.next()){
				String s;
				int number=rst.getInt(2);
				number=number+1;
				
				s=""+number;
				txtBusNo.setText(s);
			}	
		}catch(Exception n){
			n.printStackTrace();
		}		
	}

	/*public static void main(String args[]){
		JFrame.setDefaultLookAndFeelDecorated(true);
		new LoginScreen().setVisible(true);
	}*/
}
