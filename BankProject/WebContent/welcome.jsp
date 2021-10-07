<%@page import="org.tech.dao.ImgUrlDao"%>
<%@page import="org.tech.dao.BalanceCheckDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv='cache-control' content='no-cache'>
    <meta http-equiv='expires' content='0'>
    <meta http-equiv='pragma' content='no-cache'>
    <title>WelcomePage</title>
    <link rel="stylesheet" href="./css/welcome.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./node_modules/sweetalert2/dist/sweetalert2.css">
</head>
<body>
<%
//response.setHeader("Pragma", "no-cache");
//response.setHeader("Cache-Control", "no-cache");
//response.setDateHeader("Expires", 0);
String user_email=(String)session.getAttribute("email");
int balance = BalanceCheckDao.getBalance(user_email);
if(balance==-1001){
%>
    <script src="./node_modules/sweetalert2/dist/sweetalert2.all.min.js"></script>
   <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
    <script >
    let timerInterval
    Swal.fire({
      title: '<span style="color:red">error in balancecheck dao (welcome page) failed...</span>',
      html: 'try again....',
      timer: 3000,
    })
    </script>
<%
 RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
 rd.include(request, response);
}
String email=(String)session.getAttribute("email");
String ImgUrl=ImgUrlDao.getImgUrl(email);
%>
    <div class="container">
   <nav class="nav-links">
      <h1 id="h1">Hii ${sessionScope.name}</h1>
      <div class="edit-profile">
          <div class="round"><img src="<%=ImgUrl %>" alt="profile"></div>
          <button class="btn-profile"><a href="viewProfile.jsp" style="text-decoration: none; color: rgb(36, 34, 34);">view profile ></a></i></button>
          <!-- <i class="fa fa-gear"></i> -->
      </div>
   </nav>

   <main>
       <h3 id="balH3">Balance:<%=balance %></h3>
       <!-- <h2 id="ac-no">Your Account Number:</h2> -->
       <h2 id="s-center">select the service</h2>
       <div class="buttons">
        <div class="first-button">
            <button class="btn" onclick="Buttonmform1()">Deposit Money</button>
            <div class="mform1">
                <div class="icon">
                    <i class="fa fa-times-circle-o icon-pressed1"  style="font-size:24px"></i>
                </div>
                <form action="TransactionServ" class="mini-form-link " method="post">
                    <input type="number" name="Damount" placeholder="Enter the amount" required>
                    <input type="submit" value="Deposit" class="btn5">
                </form>
            </div>
        </div>
        <div class="second-button">
            <button class="btn" onclick="Buttonmform2()">Withdraw Money</button>
            <div class=" mform2">
                <div class="icon">
                    <i class="fa fa-times-circle-o icon-pressed2" style="font-size:24px"></i>
                </div>
                <form action="TransactionServ" class="mini-form-link" method="post">
                    <input type="number" name="Wamount" placeholder="Enter the amount" required>
                    <input type="submit" value="Withdraw" class="btn5">
                </form>
            </div>
        </div>
        <div class="third-button">
            <button class="btn" onclick="Buttonmform3()">Money Transfer</button>
            <div class="mform3">
                <div class="icon special-icon">
                    <i class="fa fa-times-circle-o icon-pressed3" style="font-size:24px"></i>
                </div>
                <form action="TransactionServ" class="mini-form-link last-mini-form" method="post">
                    <input type="number" name="BaccNO" placeholder="Beneficiary Account No" required>
                    <input type="email" name="Bemail" placeholder="Enter Beneficiary Email" required>
                    <input type="number" name="Bamount" placeholder="Enter Amount">
                    <input type="submit" value="Send Amount" class="btn5">
                </form>
            </div>
        </div>
        
       </div>
   </main>
</div>

<script src="./js/welcome.js"></script>
<script>
//window.onload=function(){
//	  if(localStorage.getItem('login')==='true'){
//		  console.log('redirectin..')
 // 	  window.location.href="/BankProject/";
//    }
//}
   
 //   	localStorage.setItem('login','true')
   
</script>

</body>
</html>

