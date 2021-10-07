package org.tech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.tech.dbConnection.DBprovider;

public class BalanceCheckDao {
	
	public static int getBalance(String user_email) {
		int balance;
		try {
			Connection con=DBprovider.DBConnectionProvider();
			PreparedStatement psmt=con.prepareStatement("select balance from vignesh.registerd_user where email = '"+user_email+"'");
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) {
			   balance=rs.getInt(1);
			   return balance;
			}
			else {
				return  -1001;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1001;
		}
		
	}

}
