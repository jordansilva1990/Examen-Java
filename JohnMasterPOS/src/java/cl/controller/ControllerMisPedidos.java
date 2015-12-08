
package cl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jordan
 */
@WebServlet(name = "ControllerMisPedidos", urlPatterns = {"/ControllerMisPedidos"})
public class ControllerMisPedidos extends HttpServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> mapMensajes = new HashMap<>();
        
        String rut= request.getParameter("rut");
        if (rut.isEmpty()) {
            mapMensajes.put("rut_cli", "Debe ingresar un Rut para Continuar");
        }
        
        
        
        if (mapMensajes.isEmpty()) {
            
            
            
            
            
        }
        
    }
}