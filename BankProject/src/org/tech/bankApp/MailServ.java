package org.tech.bankApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MailServ
 */
@WebServlet("/MailServ")
public class MailServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		
//		random nos
		int min=1000;
		int max=10000;
		int otp=(int) Math.floor(Math.random()*(max-min+1)+min);
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("pass");
		String phone=request.getParameter("phone");
		
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		session.setAttribute("pass", password);
		session.setAttribute("phone", phone);
		session.setAttribute("otp",otp );
		

		try {
			Properties p= new Properties();
			p.put("mail.smtp.host","smtp.gmail.com");
			p.put("mail.smtp.auth","true");
			p.put("mail.debug", "true");
			p.put("mail.smtp.port","465");
			p.put("mail.smtp.socketFactory.port","465");
			p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");
			
			Session ses= Session.getDefaultInstance(p, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication ("--Your Mail Here--","--password here--");
				}
			});
			//System.out.println(ses);
			ses.setDebug(true);
			Message msg=new MimeMessage(ses);
			InternetAddress addressFrom = new  InternetAddress("--Your Mail Here--");
			msg.setFrom(addressFrom);
			InternetAddress addressTo = new  InternetAddress(email);
			System.out.println("Email in Mail serv:"+email);
			msg.addRecipient(Message.RecipientType.TO, addressTo);
			
			msg.setSubject("STAR Bank");
			msg.setContent("your one time password is"+otp, "text/html");
			Transport.send(msg);
			
//			timestamp code----------
			long timestamp_hold = java.time.Instant.now().getEpochSecond();
			long exp_timestamp=timestamp_hold+120;
			System.out.println("Time stamp::"+exp_timestamp);
	        session.setAttribute("timestamp", exp_timestamp);
	        session.setAttribute("timestamp_session",session);
	        
	       
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("eeerrrrrroooorrrrr..........");
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
					"      title: '<span style=\"color:red\">Oops..Something went wrong! try again...</span>',\r\n" + 
					"      html: 'try again....',\r\n" + 
					"      timer: 3000,\r\n" + 
					"    })\r\n" + 
					"    </script>\r\n" + 
					"    <jsp:include page=\"index.html\"><jsp:param value=\"\" name=\"\"/></jsp:include>\r\n" + 
					"</body>\r\n" + 
					"</html>");
			RequestDispatcher rd=request.getRequestDispatcher("register.html");
			rd.include(request, response);
			
		}

		response.sendRedirect("authentication.jsp");

	}

}
