package com.bbss.bus;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bbss.button.DateButton;
import com.bbss.constants.Constants;
import com.bbss.db.connection.Connect;
public class UpdateEntry extends javax.swing.JFrame {
	private static final long serialVersionUID = -478589738201913143L;

	private JLabel BusNo,RegNo,Model,Capacity,DP,Ins,DI,DE;
	private JTextField txtBusNo,txtRegNo,txtModel,txtCapacity,txtIns;
	private JButton Update,Search,Clear,Delete;
	String busNo, regNo,model, capacity, db,is,ie,id;
	private JButton jButton2;
	private JPanel jPanel1;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private DateButton date_bought;
    private DateButton date_ins;
    private DateButton date_expiry;
    private static JTextArea txtInfo=new JTextArea( 15, 40 );
	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	
	public UpdateEntry(String regNo,String busNo,  String model, String capacity, String db,String is,String ie,String id){
		super("Update Bus Details");

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		BusNo = new JLabel("Bus Number ");
		RegNo = new JLabel("Reg Number ");
		Model = new JLabel("Model.: ");
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
       
		date_bought  =new DateButton();
		date_ins     =new DateButton();
		date_expiry  =new DateButton();
		
		txtBusNo.setText(busNo);
		txtRegNo.setText(regNo);
		txtModel.setText(model);
		txtCapacity.setText(capacity);
        date_bought.setText(db);
        
        txtIns.setText(is);
        date_ins .setText(ie);
        date_expiry.setText(id);
		
		date_ins.setForeground(Color.red);
	    date_bought.setForeground(Color.red);
	    date_expiry.setForeground(Color.red);
	    
		Update = new JButton("Update",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/reset.png"));
		Search=new JButton ("Search",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/search.png"));
		Clear=new JButton ("Clear",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));
		Delete=new JButton("Delete",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/delete.png"));
	    jButton2 = new JButton("Cancel",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));
        
		jPanel1 = new JPanel(new java.awt.GridLayout(8,2));
        jPanel1.setPreferredSize(new Dimension (400,250));
		jPanel1.add(BusNo);jPanel1.add(txtBusNo);
		jPanel1.add(RegNo);jPanel1.add(txtRegNo);
		jPanel1.add(Model);jPanel1.add(txtModel);
		jPanel1.add(Capacity);jPanel1.add(txtCapacity);
        jPanel1.add(DP);jPanel1.add(date_bought);
        jPanel1.add(Ins);jPanel1.add(txtIns);
        jPanel1.add(DI);jPanel1.add(date_ins );
        jPanel1.add(DE);jPanel1.add(date_expiry);
		
		jPanel3 = new javax.swing.JPanel(new java.awt.FlowLayout());
		jPanel3.add(jPanel1);

		jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());
		jPanel4.add(Update);
		jPanel4.add(jButton2);
		jPanel4.add(Search);
		jPanel4.add(Delete);
		jPanel4.add(Clear);		 
		setLocation((screen.width - 500)/2,((screen.height-350)/2));	
		setResizable(false);
		
		txtModel.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(null,"This field only accept text",Constants.SUPER_TITLE, JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});
		
		txtIns.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(null,"This field only accept text",Constants.SUPER_TITLE,JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});
       
		txtCapacity.addFocusListener(new FocusAdapter() {
	        public void focusLost(FocusEvent e) {
	        	JTextField textField = (JTextField)e.getSource();
	        	String content = textField.getText();
	        	 
	        	if (content.length() != 0) {
	        		try {
	        			 Integer.parseInt(content);
	        		}catch (NumberFormatException nfe) {
	        			getToolkit().beep();
	        			JOptionPane.showMessageDialog(null,Constants.INVALID,Constants.SUPER_TITLE,JOptionPane.ERROR_MESSAGE);
	        			textField.requestFocus();
	        			txtCapacity.setText("");
	        		}
	        	}
	        }
		});
                 
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtBusNo.getText() == null || txtBusNo.getText().equals("")){   
					JOptionPane.showMessageDialog(null,"Error!!! BusNo can't be null",Constants.SUPER_TITLE,JOptionPane.ERROR_MESSAGE);
					txtBusNo.requestFocus();
					return;
				}
				
				if (txtRegNo.getText() == null || txtRegNo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Error!!! RegNo can't be null", Constants.SUPER_TITLE, JOptionPane.ERROR_MESSAGE);
					txtRegNo.requestFocus();
					return;
				}
				
				if (txtModel.getText() == null || txtModel.getText().equals("")) {
					JOptionPane.showMessageDialog(null,	"Error!!! Model Field is required", Constants.SUPER_TITLE,	JOptionPane.ERROR_MESSAGE);
					txtModel.requestFocus();
					return;
				}
				
				if (txtCapacity.getText() == null || txtCapacity.getText().equals("")) {
					JOptionPane.showMessageDialog(null,	"Error!!! Enter bus capacity", Constants.SUPER_TITLE,JOptionPane.ERROR_MESSAGE);
					txtCapacity.requestFocus();
					return;
				}

				if (txtIns.getText() == null || txtIns.getText().equals("")) {
					JOptionPane.showMessageDialog(null,	"Error!!! Insurance status entry is required", Constants.SUPER_TITLE, JOptionPane.ERROR_MESSAGE);
					txtIns.requestFocus();
					return;
				}

				try {
					Statement statement = Connect.getConnection().createStatement();

					String temp = "UPDATE Buses SET "
								+ "  Model              ='"
								+ txtModel.getText()
								+ "',Capacity           ='"
								+ txtCapacity.getText() +

								"',DateBought         ='"
								+ date_bought.getText()
								+ "',Insurance_Status   ='" + txtIns.getText()
								+ "',Date_Insured       ='"
								+ date_ins.getText()
								+ "',Insurance_Expiry   ='"
								+ date_expiry.getText() +

								"' WHERE BusNo LIKE'" + txtBusNo.getText()
								+ "'";
						statement.executeUpdate(temp);
						dispose();
				}catch (SQLException sqlex) {
					sqlex.printStackTrace();
				}
			}
		});

		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		Delete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int DResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete Record?");

				if (DResult == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "Deletion Cancel", "DELETION", JOptionPane.DEFAULT_OPTION);
				}

				if (DResult == JOptionPane.YES_OPTION) {
					try {
						Statement statement = Connect.getConnection().createStatement();
						
						if (!txtBusNo.equals("")) {
							String query = ("DELETE  FROM Buses where BusNo ='"	+ txtBusNo.getText() + "'");
							int result = statement.executeUpdate(query);

							if (result == 0) {
								JOptionPane.showMessageDialog(null,	"Record Deleted", "DELETION", JOptionPane.DEFAULT_OPTION);
							}
							else{
								txtInfo.append("\nDeletion failed\n");
								txtBusNo.setText("");
								txtRegNo.setText("");
								txtModel.setText("");
								txtCapacity.setText("");
								txtIns.setText("");
							}
							statement.close();
						}
					}catch (SQLException sqlex){
						sqlex.printStackTrace();
					}
				}
			}
		});
		
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusNo.setText("");
				txtRegNo.setText("");
				txtModel.setText("");
				txtCapacity.setText("");
				txtIns.setText("");
			}
		});
		
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!txtBusNo.equals("")) {

						Statement statement = Connect.getConnection().createStatement();
						String query = ("SELECT * FROM Buses where BusNo ='" + txtBusNo.getText() + "'");
						ResultSet rs = statement.executeQuery(query);
						display(rs);
						statement.close();
					}
				}catch (SQLException sqlex)	{
					sqlex.printStackTrace();
				}
				setVisible(true);
			}
		});

		jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

		jPanel5.add(jPanel3, BorderLayout.CENTER);
		jPanel5.add(jPanel4, BorderLayout.SOUTH);

		getContentPane().add(jPanel5);

		pack();
		setVisible(true);
	}

	public void display(ResultSet rs) {
		try {
			boolean recordNumber = rs.next();
			if (recordNumber) {
				regNo = rs.getString(1);
				busNo = rs.getString(2);
				model = rs.getString(3);
				capacity = rs.getString(4);
				db = rs.getString(5);
				is = rs.getString(6);
				ie = rs.getString(7);
				id = rs.getString(8);

				txtBusNo.setText(busNo);
				txtRegNo.setText(regNo);
				txtModel.setText(model);
				txtCapacity.setText(capacity);
				date_bought.setText(db);
				txtIns.setText(is);
				date_ins.setText(ie);
				date_expiry.setText(id);
			}
			else {
				JOptionPane.showMessageDialog(null, "Record Not found",	Constants.ERROR, JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException sqlex){
			sqlex.printStackTrace();
		}
	}

	/*public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new LoginScreen().setVisible(true);
		new UpdateEntry("", "", "", "", "", "", "", "").setVisible(false);
	}*/
}