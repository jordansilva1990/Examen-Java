
package cl.controller;

import cl.dominio.Pedido;
import cl.dto.PedidoDetalleProductoDTO;
import cl.servicio.JohnMasterService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
      request.getRequestDispatcher("/misPedidos.jsp").forward(request, response);  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> mapMensajes = new HashMap<>();
        int rut=0;
        
        String strRut= request.getParameter("rut");
        if (strRut.isEmpty()) {
            mapMensajes.put("rut_cli", "Debe ingresar un Rut para Consultar");
        }else
        {
             rut=Integer.parseInt(strRut);
        }
        
        
        
        if (mapMensajes.isEmpty()) {
            
           try (Connection cnx = ds.getConnection()) { 
            
             JohnMasterService service = new JohnMasterService(cnx);
             List<String> descripciones =  new ArrayList<String>();
             
            List<Pedido> lstpedido = service.buscarPedidosCliente(rut);
            
             List<String> lstdescripciones = service.obtenerDescripcionPedido(lstpedido);
            
             
               
             
             
             request.setAttribute("lstPedidos",lstpedido);
             request.setAttribute("lstdescripciones", lstdescripciones);
             
            //request.setAttribute("lstPedidos", service.buscarPedidoProductoPedidoDetalle(rut));
             request.getRequestDispatcher("/misPedidos.jsp").forward(request, response);    
               
               
               
               
               
               
               
               
               
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            
            
            
            
        }
        
    }
}