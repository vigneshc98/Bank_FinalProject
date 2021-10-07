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
  //String  session_id=session.getId();
  //session.setAttribute("session_id", session_id);
  
  session.setAttribute("current_session", session);
  
  String yes=(String)session.getAttribute("isRegisterd");
  if(yes=="yes")
  {
	  String name=(String)session.getAttribute("name");
	  String email=(String)session.getAttribute("email");
	  String pass=(String)session.getAttribute("pass");
	  String phone=(String)session.getAttribute("phone");
  %>
  <jsp:useBean id="logbean" class="org.tech.bean.LoginBean" scope="application"></jsp:useBean>
  <jsp:setProperty property="email" name="logbean" value="<%=email%>"/>
  <jsp:setProperty property="pass" name="logbean" value="<%=pass%>"/>
  <jsp:useBean id="logdao" class="org.tech.dao.LoginDao"></jsp:useBean>

 <%
 String msg[]=logdao.log(logbean);
 if(msg[0]=="success"){
	
 %>
    <script type="text/javascript">localStorage.setItem('login','true')</script>
    <script src="./node_modules/sweetalert2/dist/sweetalert2.all.min.js"></script>
   <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
        <script>
        window.onload = function(){
       swal({ 
        text:"succesfully Registerd",
        type:"sucess",
        show:"true",
        confirmButtonText:"Ok",
        icon:"success",
      });
      }   
    </script> 

	<%
    session.setAttribute("name", msg[1]);
	session.setAttribute("email", logbean.getEmail());
	session.setAttribute("pass", logbean.getPass());
	session.setAttribute("balance", msg[2]);
	
	RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
	rd.include(request, response);
 } else{ 
	%>
	    <script >
    let timerInterval
    Swal.fire({
      title: '<span style="color:red">login dao or jsp failed!</span>',
      html: 'try again....',
      timer: 3000,
    })
    </script>
     <jsp:include page="index.html"></jsp:include>
     <%
  }
  }else{
     %>
   <jsp:useBean id="logbean2" class="org.tech.bean.LoginBean" scope="application"></jsp:useBean>
  <jsp:setProperty property="*" name="logbean2"/>
  <jsp:useBean id="logdao2" class="org.tech.dao.LoginDao"></jsp:useBean>
  
    <script src="./node_modules/sweetalert2/dist/sweetalert2.all.min.js"></script>
   <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
  <%
    String msg[]=logdao2.log(logbean2);
    if(msg[0]=="success")
    {
  %>
  <%--
        <script>
        window.onload = function(){swal({ 
        text:"Login Succesfull",
        type:"sucess",
        show:"true",
        confirmButtonText:"Ok",
        icon:"success",
      });
      }   
    </script> 
   --%>

    <%
	
    session.setAttribute("name", msg[1]);
	session.setAttribute("email", logbean2.getEmail());
	session.setAttribute("pass", logbean2.getPass());
	session.setAttribute("balance", msg[2]);
	RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
	rd.include(request, response);
    }else{
    %>
    <script >
    let timerInterval
    Swal.fire({
      title: '<span style="color:red">Invalid Username or Password!</span>',
      html: 'try again....',
      timer: 3000,
    })
    </script>
    <jsp:include page="index.html"></jsp:include>
    <%
    } 
  }
    %>

</body>
</html>