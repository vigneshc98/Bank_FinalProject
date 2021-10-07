<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>successpage</title>
<link rel="stylesheet" href="./node_modules/sweetalert2/dist/sweetalert2.css">

</head>
<body><%
      String res=(String)session.getAttribute("re-load");
      if(res=="yes"){
    	session.setAttribute("re-load", null);
      %>
          <script src="./node_modules/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
         <script>
         window.onload = function(){swal({ 
         text:"Transaction Successfull",
         type:"sucess",
         show:"true",
         confirmButtonText:"Ok",
         icon:"success",
       });
       }   
     </script> 
     <%
	   RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
	   rd.include(request, response);
	    
      } else {
    	  response.sendRedirect("welcome.jsp");
      }
	   %>
</body>
</html>