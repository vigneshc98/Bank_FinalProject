package org.tech.dao;

import java.sql.*;

import org.tech.dbConnection.DBprovider;

public class UserInfoDao {
    static Connection con=null;
	static PreparedStatement psmt=null;
	static ResultSet rs=null;

	
	
	public static String[] getInfo(String email) {
		
		String query="select user_name, email, phone, place from  vignesh.registerd_user where email='"+email+"'";
		String fail[]= {"fail"};
		
		try {
			con =  DBprovider.DBConnectionProvider();
			psmt= con.prepareStatement(query);
			rs=psmt.executeQuery();
			if(rs.next()) {
				String name=rs.getString(1);
				String userEmail=rs.getString(2);
				String phone=rs.getString(3);
				String place=rs.getString(4);
				String hold[]= {"success",name,userEmail,phone,place};
				System.out.println("userinfo dao:"+name+":"+userEmail+":"+phone+":"+place);
				return hold;
			}
			else {
				return fail;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return fail;
		}

		
	}

}
