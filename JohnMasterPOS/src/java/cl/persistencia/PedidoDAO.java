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
    
    
    
    
    
    
    
    public void buscar()
    {
        String sql="";
    }
    
}
