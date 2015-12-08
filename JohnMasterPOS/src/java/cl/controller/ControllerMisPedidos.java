
package cl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Jordan
 */
@WebServlet(name = "ControllerMisPedidos", urlPatterns = {"/ControllerMisPedidos"})
public class ControllerMisPedidos extends HttpServlet {

@Resource(mappedName = "jdbc/johnmaster")
    private DataSource ds;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> mapMensajes = new HashMap<>();
        
        String strRut= request.getParameter("rut");
        if (strRut.isEmpty()) {
            mapMensajes.put("rut_cli", "Debe ingresar un Rut para Continuar");
        }else
        {
            int rut=Integer.parseInt(strRut);
        }
        
        
        
        if (mapMensajes.isEmpty()) {
            
           try (Connection cnx = ds.getConnection()) { 
            
            
               
               
               
               
               
               
               
               
               
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            
            
            
            
        }
        
    }
}