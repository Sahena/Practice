package com.prac.jsf.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {
	private static final String Jdbc_Driver = "oracle.jdbc.driver.OracleDriver";
	 private static final String DB_URL = "jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=yes)(FAILOVER=ON)(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.72.117)(PORT=1521))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=d2he1)))";
	 private static final String PASS = "oracle";
	 private static final String USER = "I82742";
	 
		public static Connection getConnection() {
			 
			try {
				Class.forName(Jdbc_Driver);
				Connection con =DriverManager.getConnection(DB_URL, USER, PASS);
				return con;
			
			} catch (Exception ex) {
				System.out.println("Database.getConnection() Error -->"
						+ ex.getMessage());
				return null;
			}
		}

		public static void close(Connection con) {
			try {
				con.close();
			} catch (Exception ex) {
			}
		}


}
