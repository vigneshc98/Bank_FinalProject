package org.tech.dao;

import java.sql.*;

import org.tech.dbConnection.DBprovider;

public class ImgUrlDao {
	static Connection con=null;
	static PreparedStatement psmt=null;
	static ResultSet rs=null;
	
	public static String getImgUrl(String email) {
		
		try {
			con=DBprovider.DBConnectionProvider();
			psmt=con.prepareStatement("select image_url from vignesh.registerd_user where email='"+email+"'");
			rs=psmt.executeQuery();
			if(rs.next()) {
				String ImgUrl=rs.getString(1);
				return ImgUrl;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "./img/unf.png";
		
	}

}
