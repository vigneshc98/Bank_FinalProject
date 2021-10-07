package org.tech.bankApp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.tech.dbConnection.DBprovider;

@WebServlet("/viewPupdate")

@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
maxFileSize=1024*1024*50,      	// 50 MB
maxRequestSize=1024*1024*100) 

public class viewPupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con= null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	String filePath=null;
	
    private static final String UPLOAD_DIR = "stored_profile_images";
    
    int n0, n1,n2,n3,n4,n5,n6,n7,n8,n9,n10;

    
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                String hold= token.substring(token.indexOf("=") + 2, token.length()-1);
                System.out.println("hold lenght:"+hold.length());
                if(hold.length()<1) {
                	return "";
                }else {
                	return hold;
                }
            }
        }
        return "";
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String CUemail=(String)session.getAttribute("email");
		response.setContentType("text/html");
		
//		-------------------FILE UPLOAD CODE--------------------------
//      String applicationPath = request.getServletContext().getRealPath("");
//	    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
      String applicationPath2 = "D:\\j2EE_MySQL_WorkBench\\BankProject\\WebContent";
      String uploadFilePath = applicationPath2 + File.separator + UPLOAD_DIR;
      System.out.println("till here");
      
      // creates the save directory if it does not exists
//      File fileSaveDir = new File(uploadFilePath);
//      System.out.println("fileSaveDir:"+fileSaveDir);
//      if (!fileSaveDir.exists()) {
//          fileSaveDir.mkdirs();
//      }

      int i=0;
//      System.out.println("request-getparts(): "+request.getParts()); //always return the true, bcz it return object
          for (Part part : request.getParts()) {
          	i++;
          	if(i<2) {
                  String fileName = getFileName(part);
                  if(fileName!="") {
               	   System.out.println("---fileName:"+fileName);
                      part.write(uploadFilePath + File.separator + fileName);
                      
                      filePath=("./"+UPLOAD_DIR + "/" + fileName);
                      System.out.println("FILE PATH:"+filePath);
                      out.println("successfull");
                  }
                  else {
                   
                   try {
                  	con=DBprovider.DBConnectionProvider();
						psmt=con.prepareStatement("select image_url from vignesh.registerd_user where email='"+CUemail+"'");
						rs=psmt.executeQuery();
						if(rs.next()) {
						  filePath=rs.getString(1);
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
						
					}
                  }
          	}else {
          		break;
          	}

          }
		
//		--------------------------------------------
          
  		
  		String name=request.getParameter("Uname");
  		String phone=request.getParameter("Uphone");
  		String place=request.getParameter("Uplace");
		System.out.println(name.length()+":::"+phone.length()+":::"+place.length());	
		if(name.length()==0 ) {
			System.out.println("NAME IS NULL");
		}
		if(phone.length()==0) {
			System.out.println("PHONE NULL");
		}
		if(place.length()==0) {
			System.out.println("PLACE NULL");
		}
  		
  		try {
			con=DBprovider.DBConnectionProvider();
			if(name.length()==0 && phone.length()==0 && place.length()==0 ) {
				System.out.println(name+":-::"+phone+":::"+place);
  				psmt=con.prepareStatement("update vignesh.registerd_user set image_url='"+filePath+"' where email ='"+CUemail+"'");
  			    n0=psmt.executeUpdate();
			}
			else if(name.length()!=0 && phone.length()!=0 && place.length()!=0 ) {
				System.out.println(name+"::-:"+phone+":::"+place);	
  				psmt=con.prepareStatement("update vignesh.registerd_user set user_name= '"+name+"' , phone= "+phone+", place='"+place+"', image_url='"+filePath+"' where email ='"+CUemail+"'");
  			    n1=psmt.executeUpdate();
  			}
//  			--
  			else if(name.length()!=0 && phone.length()==0 && place.length()==0 ) {
  				System.out.println(name+":::-"+phone+":::"+place);	
  				psmt=con.prepareStatement("update vignesh.registerd_user set user_name= '"+name+"', image_url='"+filePath+"' where email ='"+CUemail+"'");
  				n2=psmt.executeUpdate();
  			}
  			else if(name.length()!=0 && phone.length()!=0 && place.length()==0) {
  				psmt=con.prepareStatement("update vignesh.registerd_user set user_name= '"+name+"', phone= "+phone+", image_url='"+filePath+"' where email ='"+CUemail+"'");
  				n3=psmt.executeUpdate();
  			}
  			else if(name.length()!=0 && phone.length()==0 && place.length()!=0) {
  				psmt=con.prepareStatement("update vignesh.registerd_user set user_name= '"+name+"', place='"+place+"', image_url='"+filePath+"' where email ='"+CUemail+"'");
  				n4=psmt.executeUpdate();
  			}
  			else if(name.length()==0 && phone.length()!=0 && place.length()==0) {
  				psmt=con.prepareStatement("update vignesh.registerd_user set  phone= "+phone+", image_url='"+filePath+"' where email ='"+CUemail+"'");
  			    n5=psmt.executeUpdate();
  			}
  			else if(name.length()!=0 && phone.length()!=0 && place.length()==0) {
  				psmt=con.prepareStatement("update vignesh.registerd_user set user_name= '"+name+"' , phone= "+phone+", image_url='"+filePath+"' where email ='"+CUemail+"'");
  			    n6=psmt.executeUpdate();
  			}
  			else if(name.length()==0 && phone.length()!=0 && place.length()!=0) {
  				psmt=con.prepareStatement("update vignesh.registerd_user set  phone= "+phone+", place='"+place+"', image_url='"+filePath+"' where email ='"+CUemail+"'");
  			    n7=psmt.executeUpdate();
  			}
//  			--
  			else if(name.length()==0 && phone.length()==0 && place.length()!=0) {
  				psmt=con.prepareStatement("update vignesh.registerd_user set place='"+place+"', image_url='"+filePath+"' where email ='"+CUemail+"'");
  			    n8=psmt.executeUpdate();
  			}
  			else if(name.length()!=0 && phone.length()==0 && place.length()!=0) {
  				psmt=con.prepareStatement("update vignesh.registerd_user set user_name= '"+name+"', place='"+place+"', image_url='"+filePath+"' where email ='"+CUemail+"'");
  			    n9=psmt.executeUpdate();
  			}
  			else if(name.length()==0 && phone.length()!=0 && place.length()!=0) {
  				psmt=con.prepareStatement("update vignesh.registerd_user set  phone= "+phone+", place='"+place+"', image_url='"+filePath+"' where email ='"+CUemail+"'");
  			    n10=psmt.executeUpdate();
  			}
			response.sendRedirect("viewProfile.jsp");
//  			---


  		}catch(Exception e) {
  			e.printStackTrace();
			out.println("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<title>error</title>\r\n" + 
					"<link rel=\"stylesheet\" href=\"./node_modules/sweetalert2/dist/sweetalert2.css\">\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"    <script src=\"./node_modules/sweetalert2/dist/sweetalert2.all.min.js\"></script>\r\n" + 
					"   <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script> \r\n" + 
					"\r\n" + 
					"    <script >\r\n" + 
					"    let timerInterval\r\n" + 
					"    Swal.fire({\r\n" + 
					"      title: '<span style=\"color:red\">Something went wrong...</span>',\r\n" + 
					"      html: 'try again....',\r\n" + 
					"      timer: 3000,\r\n" + 
					"    })\r\n" + 
					"    </script>\r\n" + 
					"</body>\r\n" + 
					"</html>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"");
			RequestDispatcher rd=request.getRequestDispatcher("viewProfile.jsp");
			rd.include(request, response);
  		}
  		finally {
  			System.out.println("iam finally executed");
  			try {
  				if(con!=null) {
  					con.close();
  				}
  				if(psmt!=null) {
  					psmt.close();
  				}
  				if(rs!=null) {
  					rs.close();
  				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
			
	}

}
