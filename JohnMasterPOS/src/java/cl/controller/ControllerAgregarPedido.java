/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.dominio.Cliente;
import cl.dominio.Producto;
import cl.servicio.JohnMasterService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Simon
 */
@WebServlet(name = "ControllerAgregarPedido", urlPatterns = {"/ControllerAgregarPedido"})
public class ControllerAgregarPedido extends HttpServlet {

    @Resource(mappedName = "jdbc/johnmaster")
    private DataSource ds;

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try (Connection cnx = ds.getConnection()) {
             
            JohnMasterService service = new JohnMasterService(cnx);

           
            request.setAttribute("lsProducto", service.buscarTodosLosProductos());
            

            request.getRequestDispatcher("/pedidosHome.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    

}
