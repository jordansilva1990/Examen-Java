/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import cl.dto.PedidoDetalleProductoDTO;
import cl.dto.PedidoProductoPedidoDetalleDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jordan
 */
public class PedidoProductoPedidoDetalleDAO {
     private Connection cnx;

    public PedidoProductoPedidoDetalleDAO(Connection cnx) {
        this.cnx = cnx;
    }
     
     
    
    public List<PedidoProductoPedidoDetalleDTO> buscarPedidoProductoPedidoDetalle(int rut){
        List<PedidoProductoPedidoDetalleDTO> lista = new ArrayList<>();
        
        
        
        String sql = "select * from producto pro join pedido_detalle ped  using(id_producto) join pedido pedi on ped.ticket=ped.ticket where pedi.rut = ? order by ped.id_pedido_detalle";
        
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, rut);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Producto producto = new Producto();
                producto.setDescripcion(rs.getString("pro.descripcion"));
                producto.setIdProducto(rs.getInt("pro.id_producto"));
                producto.setValor(rs.getInt("pro.valor"));
                
                PedidoDetalle pedidoDetalle = new PedidoDetalle();
                pedidoDetalle.setCantidad(rs.getInt("ped.cantidad"));
                pedidoDetalle.setIdPedidoDetalle(rs.getInt("ped.id_pedido_detalle"));
                pedidoDetalle.setIdProducto(rs.getInt("pro.id_producto"));
                pedidoDetalle.setTicket(rs.getInt("ped.ticket"));
                
                Pedido pedido= new Pedido();
                pedido.setAgrandaBebidaPapas(rs.getByte("pedi.agranda_bebida_papas"));
                pedido.setMedioPago(rs.getString("pedi.medio_pago"));
                pedido.setParaLlevar(rs.getByte("pedi.para_llevar"));
                pedido.setRut(rs.getInt("pedi.rut"));
                pedido.setTotal(rs.getInt("pedi.total"));
                pedido.setTicket(rs.getInt("pedi.ticket"));
                
                
                lista.add(new PedidoProductoPedidoDetalleDTO(pedidoDetalle,producto,pedido));
                
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar el detalle del pedido ,pedidoDetalle,producto");
        }
        return lista;
             
    }
               
}
