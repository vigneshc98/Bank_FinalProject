<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>authentication</title>
    <link rel="stylesheet" href="./css/authentication.css">
    <link rel="stylesheet" href="./node_modules/sweetalert2/dist/sweetalert2.css">
</head>
<%-- <body onLoad="noBack();" onpageshow="if (event.persisted) noBack();" onUnload=""> --%>
<body>
      <script src="./node_modules/sweetalert2/dist/sweetalert2.all.min.js"></script>
     <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
      <%
      try{
      response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
      
      HttpSession session1=(HttpSession)session.getAttribute("timestamp_session");
      if(session1!=null){
    	  
    	  
          long exp_timestamp=(long)session.getAttribute("timestamp");

    	  long current_timestamp = java.time.Instant.now().getEpochSecond();
    	  
    	  long res_timestamp_hold=exp_timestamp - current_timestamp;
    	  
    	  //converting long to int
    	  Long long_value= new Long(res_timestamp_hold);
    	  int res_timestamp=long_value.intValue();
    	
      %>
            <script src="./node_modules/sweetalert2/dist/sweetalert2.all.min.js"></script>
            <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
      	     <div class="container">
	        <div class="main">
	            <h1>One step away from Registering</h1>
	            <h3>Please enter the Confirmation password sent to your Email</h3>
	            <h3>Time left: <span id="span"></span><span>s</span></h3>
	            <form action="authentication2.jsp">
	                <input type="text" name="EApass" placeholder="Enter Confirmation password">
	                <input type="submit">
	            </form>
	        </div>
	     </div>
	     <script src="./js/authentication.js"></script>

      <%
      if(res_timestamp<1){
    	  session1.invalidate();
      }else{
    	  session1.setMaxInactiveInterval(res_timestamp);
      }
      }else{
	  %>
      <script >
	    //let timerInterval
	    //Swal.fire({
	    //title: '<span style="color:red">Session timed out...</span>',
	    //html: 'try again....',
	    //timer: 4000,
	    })
    </script>
    <jsp:include page="register.html"></jsp:include>
      <%
      }
      %>
  <script type="text/javascript">
    window.history.forward();
    function noBack()
    {
        window.history.forward();
    }
</script>
</body>
</html>
<%
      }catch(Exception e){
    	  e.printStackTrace();
    	  response.sendRedirect("index.html");
      }
%>

