/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import cl.dto.PedidoDetalleProductoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon
 */
public class PedidoDetalleProductoDAO {
    private Connection cnx;

    public PedidoDetalleProductoDAO(Connection cnx) {
        this.cnx = cnx;
    }
    
    public List<PedidoDetalleProductoDTO> buscarTodosLosPedidoDetalleProducto(){
        List<PedidoDetalleProductoDTO> lista = new ArrayList<>();
        String sql = "select * from producto pro join pedido_detalle ped using(id_producto) order by ped.id_pedido_detalle";
        
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
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
                
                lista.add(new PedidoDetalleProductoDTO(pedidoDetalle, producto));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar el detalle del pedido");
        }
        return lista;
    }
}
