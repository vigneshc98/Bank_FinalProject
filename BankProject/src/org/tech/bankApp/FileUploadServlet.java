package org.tech.bankApp; 
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.jws.soap.InitParam;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB


public class FileUploadServlet extends HttpServlet {
 
    private static final long serialVersionUID = 205242440643911308L;

    private static final String UPLOAD_DIR = "stored_profile_images";
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter pw=response.getWriter();
    	
        // gets absolute path of the web application
        String applicationPath = request.getServletContext().getRealPath("");
        String applicationPath2 = "D:\\j2EE_MySQL_WorkBench\\BankProject\\WebContent\\stored_profile_images";
        String uploadFilePath = applicationPath2 + File.separator + UPLOAD_DIR;
        
        //Get all the parts from request and write it to the file on server
        for (Part part : request.getParts()) {
            String fileName = getFileName(part);
            
           if(fileName!="" || fileName!=null) {
        	   System.out.println("---fileName:"+fileName);
               part.write(uploadFilePath + File.separator + fileName);
           }
           else {
        	   RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
        	   rd.include(request, response);
        	
           }
        }
        
        
    }
 
    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}