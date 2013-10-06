package com.bbss.passenger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.bbss.constants.Constants;
import com.bbss.db.connection.Connect;

public class PASS extends JPanel {
	private static final long serialVersionUID = -1213154184067279240L;
	Dimension screen 		= Toolkit.getDefaultToolkit().getScreenSize();
	JFrame JFParentFrame;
	private static JTable jTable;
	private JScrollPane jScrollPane;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton jButton1,Reload;
	private JButton jButton2;


	private JButton jButton4;
	private SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
	private static int selectedRow;

	public PASS() {
		jTable = new JTable(new AbstractTable());
		for(int i = 0; i < 6; i++) {
			jTable.getColumnModel().getColumn(i);
			if (i==4)
				sdf.format(i);

		}

		jScrollPane = new JScrollPane(jTable);

		jPanel1 = new JPanel(new BorderLayout());
		jPanel1.add(jScrollPane, BorderLayout.CENTER);
		jButton1 = new JButton("ADD NEW",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/add.png"));
		jButton2 = new JButton("UPDATE",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/reset.png"));
		Reload=new JButton("REFRESH",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/refresh.png"));

		jButton4 = new JButton("CLOSE",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));

		jPanel2 = new javax.swing.JPanel(new java.awt.FlowLayout());


		jPanel2.add(jButton1);
		jPanel2.add(jButton2);

		jPanel2.add(Reload);
		jPanel2.add(jButton4);      
		reloaded();

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new Passenger().setVisible(true);
			}
		});

		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				setVisible(false);
			}
		});	
		Reload.addActionListener (new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				reloaded();
				setVisible(true);
			}
		});
		
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new Up_date_Pass (jTable.getValueAt(getSelectedRow(),0).toString(),
						jTable.getValueAt(getSelectedRow(), 1).toString(),
						jTable.getValueAt(getSelectedRow(), 2).toString(),
						jTable.getValueAt(getSelectedRow(), 3).toString(),
						jTable.getValueAt(getSelectedRow(), 4).toString(),
						jTable.getValueAt(getSelectedRow(), 5).toString(),								
						jTable.getValueAt(getSelectedRow(), 6).toString()).setVisible(true);
			}
		});

		jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);
		jPanel1.setPreferredSize(new java.awt.Dimension(1000, 300));
		jPanel1.setBackground(new java.awt.Color(200, 200, 200));
		jPanel1.setBounds(2,200,770,2);
		setSize(1000,400);
		add(jPanel1);
	}

	public static int getSelectedRow() {
		jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		javax.swing.ListSelectionModel rowSel = jTable.getSelectionModel();
		rowSel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) return;

				javax.swing.ListSelectionModel sel = (ListSelectionModel)e.getSource();
				if (!sel.isSelectionEmpty()) {
					selectedRow = sel.getMinSelectionIndex();
				}
			}
		});

		return selectedRow;
	}

	@SuppressWarnings("serial")
	class AbstractTable extends javax.swing.table.AbstractTableModel {
		private String[] columnNames = { "Passenger_No","Passenger_Name","Address", "Telephone_Number" ,
				"Date_of_Travel","From","To"};
		private Object[][] data = new Object[50][50];

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
	}
	public void reloaded(){
		try
		{
			Statement statement =Connect.getConnection().createStatement();
			{
				String temp = ("SELECT * FROM Passenger");
				int  Numrow = 0;
				ResultSet result = statement.executeQuery(temp);
				while (result.next()) {
					jTable.setValueAt(result.getString(1),Numrow,0);
					jTable.setValueAt(result.getString(2),Numrow,1);
					jTable.setValueAt(result.getString(3),Numrow,2);
					jTable.setValueAt(result.getString(4),Numrow,3);
					jTable.setValueAt(result.getDate(5),Numrow,4);
					jTable.setValueAt(result.getString(6),Numrow,5);
					jTable.setValueAt(result.getString(7),Numrow,6);	
					Numrow++;

				}	
			}

		}
		catch ( SQLException sqlex ) {

		}
	}

	/*public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new LoginScreen().setVisible(true);
	}*/
}