package com.bbss.employee;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

import com.bbss.bus.driver.AddNewEntry;
import com.bbss.constants.Constants;
import com.bbss.db.connection.Connect;
import com.bbss.report.employee_report;


public class Employee extends  JPanel{
	private static final long serialVersionUID = -1982372042851578701L;

	Dimension screen 		= 	Toolkit.getDefaultToolkit().getScreenSize();
	private static javax.swing.JTable jTable;
	JFrame JFParentFrame;
	private JScrollPane jScrollPane;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton jButton1,print;
	private JButton jButton2;
	private JButton jButton4;
	private JButton table; 
	private static int selectedRow;
	private static JTextArea txtInfo=new JTextArea( 15, 40 );

	public Employee () {
		jTable = new javax.swing.JTable(new AbstractTable());
		for(int i = 0; i < 8; i++) {
			jTable.getColumnModel().getColumn(i);

		}
		jScrollPane = new javax.swing.JScrollPane(jTable);
		jPanel1 = new javax.swing.JPanel(new java.awt.BorderLayout());
		jPanel1.add(jScrollPane, java.awt.BorderLayout.CENTER);
		jButton1 = new JButton("ADD ENTRY",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/add.png"));
		table=new JButton("REFRESH",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/refresh.png"));
		jButton2 = new JButton("UPDATE",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/reset.png"));
		print=new JButton("PRINT",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/prints.png"));
		jButton4 = new JButton("CLOSE",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));
		jPanel2 = new javax.swing.JPanel(new java.awt.FlowLayout());
		jPanel2.add(jButton1);
		jPanel2.add(jButton2);
		jPanel2.add(table);
		jPanel2.add(print);
		jPanel2.add(jButton4);      
		//		jPanel2.add(searchButton);
		load_jTable();
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new AddNewEntry().setVisible(true);

			}
		});

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new NewEntry(   jTable.getValueAt(getSelectedRow(), 0).toString(),
						jTable.getValueAt(getSelectedRow(), 1).toString(),
						jTable.getValueAt(getSelectedRow(), 2).toString(),
						jTable.getValueAt(getSelectedRow(), 3).toString(),
						jTable.getValueAt(getSelectedRow(), 4).toString(),
						jTable.getValueAt(getSelectedRow(), 5).toString(),
						jTable.getValueAt(getSelectedRow(), 6).toString(),
						jTable.getValueAt(getSelectedRow(), 7).toString(),
						jTable.getValueAt(getSelectedRow(), 8).toString(),
						jTable.getValueAt(getSelectedRow(), 9).toString()).setVisible(true);
			}
		});  


		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				setVisible(false);

			}
		}); 
		print.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				/*PrinterJob pj=PrinterJob.getPrinterJob();
          pj.setPrintable(Main.this);
          pj.printDialog();
          try{ 
            pj.print();
          }catch (Exception PrintException) {}*/
				new employee_report().setVisible(true);
			}
		});   
		table.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				load_jTable();


			}
		});
		//		
		jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);
		jPanel1.setPreferredSize(new java.awt.Dimension(750, 450));
		jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);
		jPanel1.setPreferredSize(new java.awt.Dimension(1000, 300));
		jPanel1.setBackground(new java.awt.Color(200, 200, 200));
		jPanel1.setBounds(2,200,770,2);

		setBorder(javax.swing.BorderFactory.createEtchedBorder());

		setSize(1000,400);

		add(jPanel1);

	}
	public static int getSelectedRow() {
		jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		javax.swing.ListSelectionModel rowSel = jTable.getSelectionModel();
		rowSel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) return;

				javax.swing.ListSelectionModel sel = (javax.swing.ListSelectionModel)e.getSource();
				if (!sel.isSelectionEmpty()) {
					selectedRow = sel.getMinSelectionIndex();
				}
			}
		});

		return selectedRow;
	}



	class AbstractTable extends AbstractTableModel {
		private static final long serialVersionUID = 5652159252347545965L;

		private String[] columnNames = { "Driver Number", "Surname", "FirstName", "Last Name" ,
				"Gender","DOB","Designation","Telephone","E-mail","Address"};
		private Object[][] data = new Object[100][100];

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



	/*public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new LoginScreen().setVisible(true);
	}*/
	public void load_jTable(){
		try
		{
			Statement statement = Connect.getConnection().createStatement();
			{
				String temp = ("SELECT * FROM Emp ORDER BY EmpNo");
				int  Numrow = 0;
				ResultSet result = statement.executeQuery(temp);
				while (result.next()) {
					jTable.setValueAt(result.getString(1),Numrow,0);
					jTable.setValueAt(result.getString(2),Numrow,1);
					jTable.setValueAt(result.getString(3),Numrow,2);
					jTable.setValueAt(result.getString(4),Numrow,3);
					jTable.setValueAt(result.getString(5),Numrow,4);
					jTable.setValueAt(result.getDate(6),Numrow,5);
					jTable.setValueAt(result.getString(7),Numrow,6);
					jTable.setValueAt(result.getString(8),Numrow,7);
					jTable.setValueAt(result.getString(9),Numrow,8);
					jTable.setValueAt(result.getString(10),Numrow,9);
					Numrow++;
				}

			}

		}
		catch ( SQLException sqlex ) {
			txtInfo.append( sqlex.toString() );
		}	
	}

}