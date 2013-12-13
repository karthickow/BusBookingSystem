package com.bbss.db.connection;


public interface DBConstants {
	// MS SQL SERVER 2008
	String DB_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";	
	String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=BusBookingSystem";
	String DB_USERNAME = "sa";
	String DB_PASSSWORD = "password-1"; 
	
	// MS ACCESS
	/*String DB_DRIVER_CLASS = "sun.jdbc.odbc.JdbcOdbcDriver";
	String DATASOURCE = Constants.USER_DIR + "/src/com/bbss/db/connection/RVB.mdb";
	String DB_URL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+DATASOURCE+";DriverID=22;READONLY=true)";
	String DB_USERNAME = "";
	String DB_PASSSWORD = ""; */
	
	// Oracle 11g Express
	/*String DB_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	String DB_URL = "jdbc:oracle:thin:@localhost:1521/xe";
	String DB_USERNAME = "BusBookingSystem";
	String DB_PASSSWORD = "BusBookingSystem";*/
}
