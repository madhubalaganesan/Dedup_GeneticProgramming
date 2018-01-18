/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nisha
 */
@WebServlet(urlPatterns = {"/DownServ"})
public class DownServ extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
                try{
		 String file = request.getParameter("file").toString();
            out.println("came");
            String filename = file; 
                    String filepath = "C:\\project\\storage\\";
		  //String filepath = "E:\\SLA\\"; 
             response.setContentType("APPLICATION/OCTET-STREAM"); 
             System.err.println("File path "+filepath+filename);
		  response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\""); 

		  java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath + filename);
		  
		  int i; 
		  while ((i=fileInputStream.read()) != -1) {
		    out.write(i); 
		  } 
                
		  fileInputStream.close(); 
                }catch(Exception e){
                    out.println(e);
                }
                  out.close(); 
	}
    
}