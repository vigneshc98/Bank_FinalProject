<%@page import="org.tech.dao.ImgUrlDao"%>
<%@page import="org.tech.dao.UserInfoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv='cache-control' content='no-cache'>
    <meta http-equiv='expires' content='0'>
    <meta http-equiv='pragma' content='no-cache'>
    
    <title>viewProfile</title>
    <link rel="stylesheet" href="./css/viewProfile.css">

</head>
<body>
<%
//try{
//response.setHeader("Pragma", "no-cache");
//response.setHeader("Cache-Control", "no-cache");
//response.setDateHeader("Expires", 0);

String email=(String)session.getAttribute("email");
String msg[]= UserInfoDao.getInfo(email);

String ImgUrl=ImgUrlDao.getImgUrl(email);

%>
    <div class="container">
         <div class="logout">
            <img src="<%=ImgUrl %>" alt="">
            <a href="Logout"><img src="./img/logoutButton.jpg" alt="" id="logoutbtn"></a>
        </div>
        <form action="viewPupdate" class="form-link" onsubmit="return ProfileUpdateSubmit()" onreset="clearError()" method="post" enctype="multipart/form-data">
            <div class="form-main-child">
                <div class="form-child"  id="fileId"><label for="file">change profile</label><input type="file" name="Ufile" id="file" style="display: none;"></div>
                <div class="form-child1" >
                    <div class="formDesign" id="Uname">
                        Name:<input type="text" name="Uname" placeholder="<%=msg[1] %>" readonly="readonly" class="sub-form-child" id="ip1">
                        <div class="sp"><b><span id="formError"></span></b></div>
                    </div>
                </div>

                <div class="form-child1">
                    <div class="formDesign" id="Uphone">
                        Contact No: <input type="number" name="Uphone" placeholder="<%=msg[3] %>" readonly="readonly" class="sub-form-child" id="ip3">
                        <div class="sp"><b><span id="formError"></span></b></div>
                    </div>
                </div>
                <div class="form-child1">
                    <div class="formDesign" id="Uplace">
                        Place: <input type="text" name="Uplace" placeholder="<%=msg[4] %>" readonly="readonly" class="sub-form-child" id="ip4">
                        <div class="sp"><b><span id="formError"></span></b></div>
                    </div>
                </div>
            </div>
            
            <div class="special-submit">
                <input type="submit" value="Save" class="btn-save1 btn-save-c">
                <input type="reset" value="cancel" class="btn-save2 btn-save-c">
                <button id="nav-back-btn"><a href="welcome.jsp" style="text-decoration: none; color: rgb(36, 34, 34);">< Home Page</a></i></button>
            </div>
            
        </form>
        <div class="special-button-div"><button class="btn-edit1">Edit-Profile</button></div>
    </div>
    <script src="./js/viewProfile.js"></script>
     <script>
//      window.onload=function(){
//    	  if(localStorage.getItem('login')==='true'){
//   		  console.log('redirectin..')
//        	  window.location.href="/BankProject/";
//          }
//      }

//let logout_timer=localStorage.getItem("logout_timer");
//console.log(logout_timer);

//if(logout_timer==0){
  //     localStorage.setItem("logout_timer",1);    
    //   console.log("set to 1 inside");
      // window.location.reload();
//}

    </script>
</body>
</html>
//<%
//}catch(Exception e){
//	response.sendRedirect("index.html");
//}
//%>
