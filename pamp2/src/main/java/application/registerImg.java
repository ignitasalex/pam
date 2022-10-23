/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author drago
 */
@WebServlet(name = "registerImg", urlPatterns = {"/registerImg"})
@MultipartConfig(location = "C:\\uploads") //falta limitar tamany del fitxer
public class registerImg extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection connection = null; //sql connection

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registerImg</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registerImg at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
            String query;
            PreparedStatement statement;

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* TODO output your page here. You may use following sample code. */

            // create a database connection
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/imageDatabase;user=p2;password=p2");
            
            System.out.println("REGISTER IMAG________________________________");
            query = "INSERT INTO IMAGE (TITLE, DESCRIPTION, KEYWORDS, AUTHOR, CREATOR, CAPTURE_DATE, STORAGE_DATE, FILENAME, ENCRYPTED) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            System.out.println("Title: "+ request.getParameter("title"));
            System.out.println("descr: "+request.getParameter("description"));
            System.out.println("keywords: "+request.getParameter("keywords"));
            System.out.println("author: "+request.getParameter("author"));
            System.out.println("creator: "+request.getParameter("creator"));
            System.out.println("capDate: "+request.getParameter("capturingDate"));
            
            statement.setString(1, request.getParameter("title"));
            statement.setString(2, request.getParameter("description"));
            statement.setString(3, request.getParameter("keywords"));
            statement.setString(4, request.getParameter("author"));
            statement.setString(5, request.getParameter("creator"));
            //statement.setString(6, request.getParameter("capturingDate"));            
            String storageDate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            System.out.println("Data storage: "+storageDate);            
            System.out.println("filename: "+request.getParameter("fileName"));
            statement.setString(6, request.getParameter("capturingDate"));
            statement.setString(7, storageDate);
            //falta quitar extension del archivo
            statement.setString(8, request.getParameter("fileName"));
            statement.setString(9, "0");
            statement.executeUpdate();

            

            //Saving data into C:\\uploads
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            for (Part part : request.getParts()) {
                part.write(fileName);
            }
            response.getWriter().print("file upload successfully");

            //uploading info into database
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(registerImg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(registerImg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

    public boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] byt = new byte[is.available()];
            is.read();

            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

}
