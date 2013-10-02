package com.bbss.db.connection;

import com.bbss.constants.Constants;

public interface DBConstants {
	// MS SQL SERVER 2008
	String DB_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String DATASOURCE = Constants.USER_DIR + "/src/com/bbss/db/connection/RVB.mdb";
	String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=BusBookingSystem";
	String DB_USERNAME = "sa";
	String DB_PASSSWORD = "password-1"; 
	
	// MS ACCESS
	/*String DB_DRIVER_CLASS = "sun.jdbc.odbc.JdbcOdbcDriver";
	String DATASOURCE = Constants.USER_DIR + "/src/com/bbss/db/connection/RVB.mdb";
	String DB_URL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+DATASOURCE+";DriverID=22;READONLY=true)";
	String DB_USERNAME = "";
	String DB_PASSSWORD = ""; */
}
