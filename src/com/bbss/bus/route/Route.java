package com.bbss.bus.route;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.bbss.constants.Constants;
import com.bbss.db.connection.Connect;

public class Route extends JPanel  {
	private static final long serialVersionUID = -7342580282594979183L;

	private static javax.swing.JTable jTable;
	private JScrollPane jScrollPane;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private static int selectedRow;

	public Route() {
		jTable = new javax.swing.JTable(new AbstractTable());
		javax.swing.table.TableColumn column = null;
		for(int i = 0; i < 6; i++) {
			column = jTable.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(80);
			} else if(i == 1) {
				column.setPreferredWidth(75);
			} else if(i == 3) {
				column.setPreferredWidth(85);
			} else {
				column.setPreferredWidth(50);
			}
		}

		jScrollPane = new JScrollPane(jTable);
		jPanel1 = new JPanel(new java.awt.BorderLayout());
		jPanel1.add(jScrollPane, java.awt.BorderLayout.CENTER);
		jButton1 = new JButton("ADD ENTRY",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/add.png"));
		jButton2 = new JButton("UPDATE",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/reset.png"));
		jButton3 = new JButton("REFRESH",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/reload.png"));
		jButton4 = new JButton("CLOSE",new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));

		jPanel2 = new JPanel(new FlowLayout());
		jPanel2.add(jButton1);
		jPanel2.add(jButton2);
		jPanel2.add(jButton3);
		jPanel2.add(jButton4);
		setSize(800,400);
		load_jTable();

		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddNew().setVisible(true);
			}
		});

		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Updatedata   (  jTable.getValueAt(getSelectedRow(), 0).toString(),
						jTable.getValueAt(getSelectedRow(), 1).toString(),
						jTable.getValueAt(getSelectedRow(), 2).toString(),
						jTable.getValueAt(getSelectedRow(), 3).toString(),
						jTable.getValueAt(getSelectedRow(), 4).toString(),
						jTable.getValueAt(getSelectedRow(), 5).toString()).setVisible(true);
			}
		});

		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				load_jTable();
				setVisible(true);
			}
		});

		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//System.exit(0);
			}
		}); 

		jPanel1.add(jPanel2, BorderLayout.SOUTH);
		jPanel1.setPreferredSize(new Dimension(750, 300));

		add(jPanel1);
	}

	public static int getSelectedRow() {
		jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		javax.swing.ListSelectionModel rowSel = jTable.getSelectionModel();
		rowSel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) return;

				ListSelectionModel sel = (ListSelectionModel)e.getSource();
				if (!sel.isSelectionEmpty()) {
					selectedRow = sel.getMinSelectionIndex();
				}
			}
		});

		return selectedRow;
	}

	@SuppressWarnings("serial")
	class AbstractTable extends javax.swing.table.AbstractTableModel {
		private String[] columnNames = { "RouteNo", "Route Name", "From", "To" ,
				"Distance","Fare_Charged"};
		private Object[][] data = new Object[100][6];

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
			Statement statement =Connect.getConnection().createStatement();
			{
				String temp = ("SELECT * FROM Route ORDER BY Route_No");
				int  Numrow = 0;
				ResultSet result = statement.executeQuery(temp);
				while (result.next()) {
					jTable.setValueAt(result.getString(1),Numrow,0);
					jTable.setValueAt(result.getString(2),Numrow,1);
					jTable.setValueAt(result.getString(3),Numrow,2);
					jTable.setValueAt(result.getString(4),Numrow,3);
					jTable.setValueAt(result.getString(5),Numrow,4);
					jTable.setValueAt(result.getString(6),Numrow,5);
					Numrow++;
				}	

			}	
		}
		catch ( SQLException sqlex ) {
			sqlex.printStackTrace();
		}	
	}
}