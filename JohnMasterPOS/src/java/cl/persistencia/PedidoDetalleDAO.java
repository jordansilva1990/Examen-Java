/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.PedidoDetalle;
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
public class PedidoDetalleDAO {
    private Connection cnx;

    public PedidoDetalleDAO(Connection cnx) {
        this.cnx = cnx;
    }
    
    public void agregar(PedidoDetalle pedidoDetalle){
        String sql = "insert into pedido_detalle (ticket, id_producto, cantidad) values(?,?,?)";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, pedidoDetalle.getTicket());
            stmt.setInt(2, pedidoDetalle.getIdProducto());
            stmt.setInt(3, pedidoDetalle.getCantidad());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar el detalle al pedido!!");
        }
    }
    
    public void eliminar(int id_pedido_detalle){
        String sql = "delete from pedido_detalle where id_pedido_detalle = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, id_pedido_detalle);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar este detalle en el pedido!!");
        }
    }
    
    public List<PedidoDetalle> buscarTodos(){
        String sql = "select * from pedido_detalle order by id_pedido_detalle";
        ArrayList<PedidoDetalle> listado  = new ArrayList<>();
        
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                PedidoDetalle pedidoDetalle = new PedidoDetalle();
                pedidoDetalle.setCantidad(rs.getInt("cantidad"));
                pedidoDetalle.setIdPedidoDetalle(rs.getInt("id_pedido_detalle"));
                pedidoDetalle.setIdProducto(rs.getInt("id_producto"));
                
                listado.add(pedidoDetalle);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar los registros del detalle");
        }
        return listado;
    }
}
