/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.dominio.Cliente;
import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import cl.dto.PedidoDetalleProductoDTO;
import cl.servicio.JohnMasterService;
import cl.servicio.ServicioException;
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
 * @author Simon
 */
@WebServlet(name = "ControllerAgregarPedido", urlPatterns = {"/ControllerAgregarPedido"})
public class ControllerAgregarPedido extends HttpServlet {

    @Resource(mappedName = "jdbc/johnmaster")
    private DataSource ds;

    
    private Pedido pedido;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try (Connection cnx = ds.getConnection()) {
             
            JohnMasterService service = new JohnMasterService(cnx);
            Producto producto = new Producto();
            String mensaje;
             try {
                     
                  //Init Pedido  
                 pedido.setAgrandaBebidaPapas(Byte.parseByte("0"));
                 pedido.setMedioPago(" ");
                 pedido.setParaLlevar(Byte.parseByte("0"));
                 pedido.setRut(00);
                 pedido.setTotal(0);
                 
                 //Ingreso de Pedido y recarga del mismo desde BD con su ticket
                 service.agregarPedido(pedido);
                 pedido = service.buscarUltimoPedido();
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 } catch (ServicioException ex) {
                    mensaje = ex.getMessage();
                }
            
            
            request.setAttribute("producto", producto);
            request.setAttribute("lsProducto", service.buscarTodosLosProductos());
            

            request.getRequestDispatcher("/pedidosHome.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje;
         try (Connection cnx = ds.getConnection()) {
            Map<String, String> mapMensajes = new HashMap<>();
            JohnMasterService service = new JohnMasterService(cnx);
            
            
            PedidoDetalleProductoDTO detalleProducto = new PedidoDetalleProductoDTO ();
            List<PedidoDetalleProductoDTO> detallesproductos = new ArrayList<PedidoDetalleProductoDTO>();
            
            Producto producto = null;
            
            //verifica si se selecciono algun producto
           String strIdProducto = request.getParameter("lsProducto");
             if (strIdProducto.isEmpty()) {
                mapMensajes.put("lsProducto", "Tiene que seleccionar un producto para vender!!");
            } 
             String cantidad= request.getParameter("cantidad");
            
             
            
             
             if (mapMensajes.isEmpty()) {
                
                
                 PedidoDetalle detalle= new PedidoDetalle();
                 
                 
                 
                     
                 detalle.setTicket(pedido.getTicket());
                 detalle.setIdProducto(Integer.parseInt(strIdProducto));
                 detalle.setCantidad(Integer.parseInt(cantidad));
                 
                 
                 // se ingresa y recupera el detalle con su id
                 service.agregarDetallePedido(detalle);                 
                 detalle= service.buscarUltimoDetalle();
                 
                 // se trae el producto del detalle
                 producto= service.buscarUnProducto(detalle.getIdProducto());
                 
                 
                 detalleProducto= new PedidoDetalleProductoDTO(detalle,producto);
                 
                 
                 
                 // se agrega el detalle junto al producto para luego mostrar en JSP
                 detallesproductos.add(detalleProducto);
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                producto = service.buscarUnProducto(Integer.parseInt(strIdProducto)); 
                
                
                
                
             }
            
            
            
            
            
            request.setAttribute("lsProducto", service.buscarTodosLosProductos());
            request.setAttribute("lstProductoDetalle", service.buscarTodosLosDetallesPedidoProducto());
            request.setAttribute("lsProducto", service.buscarTodosLosProductos());
            

            request.getRequestDispatcher("/pedidosHome.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        
        
        
        
        
        
    }
    

}
