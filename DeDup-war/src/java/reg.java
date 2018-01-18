/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import DeDuplication.EmailBeanLocal;
import DeDuplication.UserLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nisha
 */
@WebServlet(urlPatterns = {"/reg"})
public class reg extends HttpServlet {
    @EJB
    private EmailBeanLocal emailBean;
    @EJB
    private UserLocal user;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet reg</title>");            
            out.println("</head>");
            out.println("<body>");
            String first, last,user_id, email_id, pass;
            first = request.getParameter("first").toString();
            out.println(first);
            last = request.getParameter("last").toString();
            out.println(last);
            user_id = request.getParameter("user_id").toString();
            out.println(user_id);
            email_id = request.getParameter("email_id").toString();
            out.println(email_id);
            pass = request.getParameter("password").toString();
            out.println(pass);
            boolean result = user.register(first, last, user_id, email_id, pass);
            if(result){
                session.setAttribute("u_id", user_id);
                String msg = "Hi "+first+"\n     You have successfully registered to Nimbuz Drive. Do not share your user_id and password to any one. \n Username: "+email_id+"\n Password: "+pass;
                result = emailBean.sendEmail(email_id, "Registration Confirmed", msg);
                if(result){
                    response.sendRedirect("upload.jsp");
                } 
                response.sendRedirect("upload.jsp");
            }
            out.println("<h1>Servlet reg at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
            out.println(e);
        }finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
