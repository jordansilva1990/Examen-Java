/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Cliente;
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
public class ClienteDAO {
     private Connection cnx;
    
    
    

    public ClienteDAO(Connection cnx) {

        this.cnx = cnx;

    }
    
    
    
    
     public void agregar(Cliente cli)
     {
         String sql = "insert into cliente (rut,nombre)values(?,?)";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, cli.getRutCliente());
            stmt.setString(2, cli.getNombre());
            
            int filasAfectadas = stmt.executeUpdate();
        }catch (SQLException ex) {
            throw new RuntimeException("Error al Agregar Cliente", ex);
        }
     }
    
    public Cliente buscar(int rutCli)
    {
        Cliente cli=null;
        String sql = "select * from cliente where rut= ?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, rutCli);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cli= new Cliente();
                    cli.setRutCliente(rs.getInt("rut"));
                    cli.setNombre(rs.getString("nombre"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al Buscar el Cliente", ex);
        }
        return cli;
        
    }
     
    
    public List<Cliente> buscarTodos()
    {
        List<Cliente> lista =new ArrayList();
        String sql="select * from cliente order by nombre";
        try (PreparedStatement stmt = cnx.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setRutCliente(rs.getInt("rut"));
                cli.setNombre(rs.getString("nombre"));
                
                
                lista.add(cli);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al Buscar Todos los Clientes", ex);
        }
        return lista;
    }
     
    public void eliminar(int rut){
        /*Se eliminan preventivamente los registro de ventas del cliente para evitar problemas.
            String sql = "delete from venta where rut= ?";
            try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, rut);
            stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error al eliminar las ventas del cliente");
            }
                */
        
            String sql="delete from cliente where rut=?";
            try (PreparedStatement stmt = cnx.prepareStatement(sql)){
                stmt.setInt(1, rut);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException("Error al eliminar al cliente", ex);
            }
    }
}
