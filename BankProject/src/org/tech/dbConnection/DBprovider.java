package org.tech.dbConnection;
import org.tech.dbConnection.DBinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DBprovider {
	
	private DBprovider(){
		
	}
	static Connection con=null;
	static ResultSet rs=null;
	static PreparedStatement psmt=null;

	public static Connection DBConnectionProvider() {
		
		try {
			Class.forName(DBinfo.DATABASE_DRIVER);
			con= DriverManager.getConnection(DBinfo.DATABASE_URL,DBinfo.DATABASE_USER,DBinfo.DATABASE_PASS);
//			System.out.println("CONNECTIN CREATED");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void DBcloseConn() {
		if(psmt!=null) {
			try {
				psmt.close();
				System.out.println("psmt closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
				System.out.println("rs closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
				System.out.println("con closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("CONNECTION CLOSED");


	}

}
