package org.tech.dao;

import java.sql.*;

import org.tech.bean.LoginBean;
import org.tech.dbConnection.DBprovider;

public class LoginDao {
	Connection con=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	String query="select user_name,email,password,balance from vignesh.registerd_user where email=? and password=?";
	
	
	public String[] log(LoginBean bean) {
		
		try {
			con=DBprovider.DBConnectionProvider();
			
			psmt=con.prepareStatement(query);	
			psmt.setString(1, bean.getEmail());
			psmt.setString(2, bean.getPass());
			psmt.execute();
			
			System.out.println("logDao email:"+bean.getEmail());
			System.out.println("logDao pass:"+bean.getPass());
			
			rs=psmt.executeQuery();
			if(rs.next()) {
				String name=rs.getString(1);
				int balance_hold=rs.getInt(4);
				String balance=Integer.toString(balance_hold);
				System.out.println("bean name and balance:"+name+":"+balance);
				String arr[]= {"success",name,balance};
				con.close();
				psmt.close();
				rs.close();
				return arr;

			}
			else {
				String arr[]= {"fail"};
				return  arr;
			}

			
			
		} catch (SQLException e) {
			e.printStackTrace();
			String arr[]= {"fail"};
			return arr;
		}
		
		
		
	}

}
