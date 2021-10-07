<%@page import="com.sun.net.httpserver.HttpsConfigurator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Authentication</title>
<link rel="stylesheet" href="./node_modules/sweetalert2/dist/sweetalert2.css">
</head>
<body>
 <%
 try{
     response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
     %>
     <script src="./node_modules/sweetalert2/dist/sweetalert2.all.min.js"></script>
     <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
     <%
     HttpSession session1=(HttpSession)session.getAttribute("timestamp_session");
     if(session1==null){
    %>	
     <script >
	    let timerInterval
	    Swal.fire({
	      title: '<span style="color:red">Session timed out...</span>',
	      html: 'try again....',
	      timer: 4000,
	    })
    </script>
    <jsp:include page="register.html"></jsp:include>
    <%
     }
     else{
     try{
    	 int otp=(int)session.getAttribute("otp");
         String Copt=request.getParameter("EApass");
         String Sotp=Integer.toString(otp);
         if(Sotp.equals(Copt)){
        	 response.sendRedirect("register.jsp");
         }else{

     %>
    <script >
	    let timerInterval
	    Swal.fire({
	      title: '<span style="color:red">Wrong Password...</span>',
	      html: 'try again....',
	      timer: 3000,
	    })
    </script>
          <jsp:include page="register.html"></jsp:include>
      <%
     }
     }catch(Exception e){
    %>
    <script >
	    let timerInterval
	    Swal.fire({
	      title: '<span style="color:red">Something went wrong...</span>',
	      html: 'try again....',
	      timer: 3000,
	    })
    </script>
         <jsp:include page="register.html"></jsp:include>
    <%
     }}
      %>

</body>
</html>
<%
      }catch(Exception e){
    	  e.printStackTrace();
    	  response.sendRedirect("index.html");
      }
%>