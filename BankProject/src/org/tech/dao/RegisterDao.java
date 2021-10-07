package org.tech.dao;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.tech.bean.RegisterBean;

import com.mysql.cj.protocol.Resultset;


public class RegisterDao {
	int count=0;
	int balance_hold=0;
	
	Connection con=null;
	PreparedStatement psmt=null;
	PreparedStatement psmt2=null;
	PreparedStatement psmt4=null;
	ResultSet rs=null;
	Resultset rs2=null;
	String query="insert into vignesh.registerd_user (user_name,email,password,phone,account_number) values(?,?,?,?,?)";
	String query2="select account_no from vignesh.accountnumber";
	String query3="update vignesh.accountnumber set account_no = ? ";
	
//	String query4="select balance from vignesh.registerd_user where email= ?";
	
	public String[] save(RegisterBean bean) {
		
		
		try {
			con=org.tech.dbConnection.DBprovider.DBConnectionProvider();
			
			psmt2=con.prepareStatement(query2);
			rs=psmt2.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
				System.out.println("regDao count before:"+count);
			}else {
				String arr[]= {"fail"};
				return arr;
			}
			psmt2=con.prepareStatement(query3);
			count++;
			psmt2.setInt(1, count);
			System.out.println("regDao count after:"+count);
			int n2=psmt2.executeUpdate();
//			----------------------------------------
//			psmt4=con.prepareStatement(query4);
//			psmt4.setString(1, bean.getEmail());
//			psmt4.execute();
//			rs=psmt4.executeQuery();
//			if(rs.next()) {
//				balance_hold=rs.getInt(1);
//			}
//			
//			String balance=Integer.toString(balance_hold);
			
//			-----------------------------------------
			psmt=con.prepareStatement(query);
			psmt.setString(1, bean.getName());
			psmt.setString(2, bean.getEmail());
			psmt.setString(3, bean.getPass());
			psmt.setString(4, bean.getPhone());
			psmt.setInt(5, count);
			int n=psmt.executeUpdate();
			if(n>0 && n2>0) {
				String arr[]= {"success"};
				return arr;
			}
			else {
				String arr[]= {"fail"};
				return arr;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			String arr[]= {"fail"};
			return arr;
		}
		
	}

}
