/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import static Common.DatabaseConnect.DatabaseConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author honey
 */
public class DeleteExpertLogic extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
                String n=null;
                 int eid=Integer.parseInt(request.getParameter("expid"));
                
                RequestDispatcher disp=null;
             
              Connection con = DatabaseConnect();
              
              PreparedStatement pst1 = con.prepareStatement("select * from experts where expert_id=?");
              pst1.setInt(1,eid);
              ResultSet rs=pst1.executeQuery();
              if(rs.next())
              {
                  n=rs.getString("username");
              }
                  
              
              
              
              PreparedStatement pst = con.prepareStatement("Delete from experts where expert_id=?");
               
               pst.setInt(1,eid);
               
                 int status=pst.executeUpdate();
   
                 
                 if(status>0)
                 {
                   disp=getServletContext().getRequestDispatcher("/DeleteExpert.jsp");
                   request.setAttribute("NAME",n);
                   disp.forward(request, response);
                 }
                 else
                 {
                  out.println("Record not deleted");
                 }
        }
        catch(Exception e)
        {
          out.println("Some Exception occurs"+e);
        }

        finally
        {            
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
