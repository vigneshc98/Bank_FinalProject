package org.tech.bankApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
//		PrintWriter out=response.getWriter();
		
//		session = request.getSession(false);
//		if (session != null && session.isNew()) {
//		    response.setHeader("Pragma", "no-cache");
//		    response.setHeader("Cache-Control", "no-cache");
//		    response.setDateHeader("Expires", 0);
//		} else {
//		    response.sendRedirect("index.html");
//		}
		
//		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
//		String user_email=(String)session.getAttribute("email");
		
//		String  session_id=session.getId();
//		String old_session_id=(String)session.getAttribute("session_id");
		
//		out.println("<script>\r\n" + 
//                "console.log('logout triggered');"+
//				"   localStorage.setItem('login','false');\r.\n" + 
//				"</script>");
	    System.out.println("inside logout.java");
		session.invalidate();
		response.sendRedirect("index.html");
//		RequestDispatcher rd=request.getRequestDispatcher("index.html");
//		rd.include(request, response);
//		if(session_id == old_session_id) {
//			session.invalidate();
//			response.sendRedirect("index.html");
//		}


	}

}
