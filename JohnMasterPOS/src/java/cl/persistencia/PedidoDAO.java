/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Cliente;
import cl.dominio.Pedido;
import cl.dominio.Producto;
import cl.dto.PedidoClienteDTO;
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
public class PedidoDAO {
    
    private Connection cnx;

    public PedidoDAO(Connection cnx) {
        this.cnx = cnx;
    }
    
    
    
    public void create(Pedido pe)
    {
        
         String sql = "insert into pedido (rut,medio_pago,agranda_bebida_papas,para_llevar,total)"
                 + "values(?,?,?,?,?)";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, pe.getRut());
            stmt.setString(2, pe.getMedioPago());
            stmt.setByte(3, pe.getAgrandaBebidaPapas());
            stmt.setByte(4, pe.getParaLlevar());
            stmt.setInt(5, pe.getTotal());
                    
            int filasAfectadas = stmt.executeUpdate();
        }catch (SQLException ex) {
            throw new RuntimeException("Error al Agregar Cliente", ex);
        }
    }
    
    public Pedido buscar(int ticket)
    {
        String sql="select * from pedido where ticket = ?";
        Pedido pedido = new Pedido();
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, ticket);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pedido.setAgrandaBebidaPapas(rs.getByte("agranda_bedida_papas"));
                pedido.setMedioPago(rs.getString("medio_pago"));
                pedido.setParaLlevar(rs.getByte("para_llevar"));
                pedido.setRut(rs.getInt("rut"));
                pedido.setTicket(rs.getInt("ticket"));
                pedido.setTotal(rs.getInt("total"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("El pedido buscado no existe");
        }
        return pedido;
    }
    
    public List<Pedido> buscarPedidosCliente(int rut)
    {
        List<Pedido> lista = new ArrayList();
        String sql = "select * from pedido where rut=?";
        Pedido pedido=null;
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
          stmt.setInt(1, rut);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pedido.setAgrandaBebidaPapas(rs.getByte("agranda_bedida_papas"));
                pedido.setMedioPago(rs.getString("medio_pago"));
                pedido.setParaLlevar(rs.getByte("para_llevar"));
                pedido.setRut(rs.getInt("rut"));
                pedido.setTicket(rs.getInt("ticket"));
                pedido.setTotal(rs.getInt("total"));
                
                lista.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException("El pedido buscado no existe");
        }
        return lista;
        
        
    }
    
}
