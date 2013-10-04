package com.bbss.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
	private static String info;
	
	public static Connection getConnection() {
		try{
			Class.forName(DBConstants.DB_DRIVER_CLASS);
			return DriverManager.getConnection(DBConstants.DB_URL, DBConstants.DB_USERNAME, DBConstants.DB_PASSSWORD);			
		} catch (ClassNotFoundException cnfex) {
			cnfex.printStackTrace();
			info = info + "Connection unsuccessful\n" + cnfex.toString();
		} catch ( SQLException sqlex ) {
            sqlex.printStackTrace();
            //info=info+"Connection unsuccessful\n" +sqlex.toString();
		} catch ( Exception excp ) {
            excp.printStackTrace();
            info=info+excp.toString();
		}   
		return null; 
	}
}