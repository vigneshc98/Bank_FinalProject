package org.tech.bankApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tech.dbConnection.DBprovider;

@WebServlet("/TransactionServ")
public class TransactionServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	class ManualException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		public ManualException() {
		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int Damount=0;
		int Wamount=0;
		int avail_balance=0;
		int Bamount=0;
		
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("email");
		
		
		String Damount_hold=request.getParameter("Damount");
		if(Damount_hold!=null  && Damount_hold.length()<=9) {
			int Damount_hold2=Integer.parseInt(Damount_hold);
			Damount=Math.abs(Damount_hold2);
		}

		
		
		String Wamount_hold=request.getParameter("Wamount");
//		int xxx=Wamount_hold.length();
		System.out.println("WAMOUNT_HOLD::"+Wamount_hold);
		if(Wamount_hold!=null && Wamount_hold.length()<=9) {
			int Wamount_hold2=Integer.parseInt(Wamount_hold);
		    Wamount=Math.abs(Wamount_hold2);
		    System.out.println("withdrawl amount:"+Wamount);
		}
		
		
		String BaccNO=request.getParameter("BaccNO");
		String Bemail=request.getParameter("Bemail");
		String Bamount_hold=request.getParameter("Bamount");
		if(Bamount_hold!=null  && Bamount_hold.length()<=9) {
			int Bamount_hold2=Integer.parseInt(Bamount_hold);
			Bamount=Math.abs(Bamount_hold2);
		}
		
		String user_availBal="select balance from vignesh.registerd_user where email= '"+email+"'";
		String depoQuery="update vignesh.registerd_user set balance = balance + "+Damount+" where email = '"+email+"'";
		String withQuery="update vignesh.registerd_user set balance = balance - "+Wamount+" where email = '"+email+"'";
		String transQuery1="update vignesh.registerd_user set balance= balance + "+Bamount+" where email = '"+Bemail+"' and account_number ="+BaccNO;     
		String transQuery2="update vignesh.registerd_user set balance= balance - "+Bamount+" where email = '"+email+"'";
		
		
//        String is_reload=(String)session.getAttribute("re-load");
        
//        if(is_reload==null) {
    		try {
    			
    			con=DBprovider.DBConnectionProvider();
    			con.setAutoCommit(false);
    			
    			HttpSession ses=request.getSession();
    			ses.setAttribute("re-load", "yes");
    			
    			
//    			------------------Gettin Current user balance----------------
    			psmt=con.prepareStatement(user_availBal);
    			rs=psmt.executeQuery();
    			if(rs.next()) {
    				avail_balance=rs.getInt(1);
    			}else {
    				response.sendRedirect("TransactionFailed.jsp");
    				
    			}
    			System.out.println("user availalbe amount:"+avail_balance);
//    			------------------------conditions 1 (Deposit)-------------------------	

    			if(Damount_hold!=null && Wamount_hold==null && BaccNO==null && Bemail==null && Bamount_hold==null) {
    				if(Damount==0) {
    					throw new ManualException();
//    					int i=100/0;
    				}
    		        	   psmt=con.prepareStatement(depoQuery);
    		        	   int n=psmt.executeUpdate();
    		        	   if(n>0) {
//    		        		   out.println("<!DOCTYPE html>\r\n" + 
//    		        	      '''''''''''''(success msg was here)'''''''''''''''
//    		        		   		"</body>\r\n" + 
//    		        		   		"</html>");
    		        		   con.commit();
//    		        		   RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
//    		        		   rd.include(request, response);
//    		        		   request.setAttribute("condition1", "success1");
    		        		   response.sendRedirect("Dummy.jsp");
    		        		   
    		        	   } else {
    		        		   response.sendRedirect("TransactionFailed.jsp");
    		        	   }  
    				}
//    			-------------------------condition 2 (Withdrawl)---------------------------------

    			else if(Damount_hold==null && Wamount_hold!=null && BaccNO==null && Bemail==null && Bamount_hold==null) {
    				if(Wamount==0) {
    					throw new ManualException();
//    					int i=100/0;
    				}
    		        	   if(avail_balance>=Wamount) {
    		        		   psmt=con.prepareStatement(withQuery);
    			        	   int n=psmt.executeUpdate();
    			        	   if(n>0) {
//        		        		   out.println("<!DOCTYPE html>\r\n" + 
//    	    		        	      '''''''''''''(success msg was here)'''''''''''''''
//    	    		        		   		"</body>\r\n" + 
//    	    		        		   		"</html>");
    			        		   con.commit();
//    			        		   RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
//    			        		   rd.include(request, response);
//        		        		   request.setAttribute("condition2", "success2");
    			        		   response.sendRedirect("Dummy.jsp");
    			        	   } else {
    			        		   response.sendRedirect("TransactionFailed.jsp");
    			        	   }
    		        	   } else {
      			        		response.sendRedirect("LowAccntBal.jsp");

    		        	   }
    		        	   
    			}
//    			--------------------------condition 3----------------------------


    			else if(Damount_hold==null && Wamount_hold==null && BaccNO!=null && Bemail!=null && Bamount_hold!=null){
        			if(email.equals(Bemail)) {
    					throw new ManualException();
//        				int i=100/0;
        			}
        			
    				if(Bamount==0) {
    					throw new ManualException();
//    					int i=100/0;
    				}
    				if(avail_balance>=Bamount) {
        				psmt=con.prepareStatement(transQuery1);
        				int n1=psmt.executeUpdate();
        				psmt=con.prepareStatement(transQuery2);
        				int n2=psmt.executeUpdate();
        				
        				if(n1>0 && n2>0) {
// 		        		   out.println("<!DOCTYPE html>\r\n" + 
//  		        	      '''''''''''''(success msg was here)'''''''''''''''
//  		        		   		"</body>\r\n" + 
//  		        		   		"</html>");
   			        		   con.commit();
//   			        		   RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
//   			        		   rd.include(request, response);
//    		        		   request.setAttribute("condition3", "success3");
   			        		response.sendRedirect("Dummy.jsp");
        				} else {
        				   	   con.rollback();
			        		   out.println("<!DOCTYPE html>\r\n" + 
   			        		   		"<html>\r\n" + 
   			        		   		"<head>\r\n" + 
   			        		   		"<meta charset=\"ISO-8859-1\">\r\n" + 
   			        		   		"<title>Insert title here</title>\r\n" + 
   			        		   		"<link rel=\"stylesheet\" href=\"./node_modules/sweetalert2/dist/sweetalert2.css\">\r\n" + 
   			        		   		"</head>\r\n" + 
   			        		   		"<body>\r\n" + 
   			        		   		"    <script src=\"./node_modules/sweetalert2/dist/sweetalert2.all.min.js\"></script>\r\n" + 
   			        		   		"   <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script> \r\n" + 
   			        		   		"\r\n" + 
   			        		   		"    <script >\r\n" + 
   			        		   		"    let timerInterval\r\n" + 
   			        		   		"    Swal.fire({\r\n" + 
   			        		   		"      title: '<span style=\"color:red\">Invalid Benificiary Account Number or email ...</span>',\r\n" + 
   			        		   		"      html: 'try again....',\r\n" + 
   			        		   		"      timer: 5000,\r\n" + 
   			        		   		"    })\r\n" + 
   			        		   		"    </script>\r\n" + 
   			        		   		"</body>\r\n" + 
   			        		   		"</html>");
   			        		   RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
   			        		   rd.include(request, response);
        				}

    				}else {
			        		response.sendRedirect("LowAccntBal.jsp");

    				}
    			}
    			else {
    				response.sendRedirect("TransactionFailed.jsp");
    			}

    		} catch (Exception e) {
    			e.printStackTrace();
    			response.sendRedirect("TransactionFailed.jsp");
    		}

	}

}
