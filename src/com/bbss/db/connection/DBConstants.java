package com.bbss.db.connection;

import com.bbss.constants.Constants;

public interface DBConstants {
	String DB_DRIVER_CLASS = "sun.jdbc.odbc.JdbcOdbcDriver";
	String DATASOURCE = Constants.USER_DIR + "/src/com/bbss/db/connection/RVB.mdb";
	String DB_URL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+DATASOURCE+";DriverID=22;READONLY=true)";
	String DB_USERNAME = "";
	String DB_PASSSWORD = ""; 
}
