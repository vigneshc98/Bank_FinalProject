<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./node_modules/sweetalert2/dist/sweetalert2.css">
</head>
<body>
<%
String name=(String)session.getAttribute("name");
String email=(String)session.getAttribute("email");
String pass=(String)session.getAttribute("pass");
String phone=(String)session.getAttribute("phone");
%>
   <jsp:useBean id="regbean" class="org.tech.bean.RegisterBean" scope="application"></jsp:useBean>
   <jsp:setProperty property="name" name="regbean" value="<%=name %>"/>
   <jsp:setProperty property="email" name="regbean" value="<%=email %>"/>
   <jsp:setProperty property="pass" name="regbean" value="<%=pass %>"/>
   <jsp:setProperty property="phone" name="regbean" value="<%=phone %>"/>
   <jsp:useBean id="regdao" class="org.tech.dao.RegisterDao"></jsp:useBean>
   
       <script src="./node_modules/sweetalert2/dist/sweetalert2.all.min.js"></script>
   <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
   
   <%
      String msg[]=regdao.save(regbean);
      if(msg[0]=="success"){
     session.setAttribute("isRegisterd", "yes");
     session.setAttribute("count", 0);
     response.sendRedirect("login.jsp");
   
      } else{ 
   %>
    <script>2
    window.onload=function() {
      Swal.fire({
      icon: 'error',
      title: 'Email already exists...',
      text: 'use different email'
       })
    }
    </script>
    <jsp:include page="register.html"></jsp:include>
   <%
      } 
   %>
</body>
</html>