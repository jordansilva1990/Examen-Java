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

    public void create(Pedido pe) {

        String sql = "insert into pedido (rut,medio_pago,agranda_bebida_papas,para_llevar,total)"
                + "values(?,?,?,?,?)";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, pe.getRut());
            stmt.setString(2, pe.getMedioPago());
            stmt.setInt(3, pe.getAgrandaBebidaPapas());
            stmt.setInt(4, pe.getParaLlevar());
            stmt.setInt(5, pe.getTotal());

            int filasAfectadas = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al Agregar Cliente", ex);
        }
    }

    //busca pedidos por su ticket
    public Pedido buscar(int ticket) {
        String sql = "select * from pedido where ticket = ?";
        Pedido pedido = new Pedido();
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, ticket);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pedido.setRut(rs.getInt("rut"));
                pedido.setTicket(rs.getInt("ticket"));
                pedido.setTotal(rs.getInt("total"));
                pedido.setAgrandaBebidaPapas(rs.getByte("agranda_bebida_papas"));
                pedido.setMedioPago(rs.getString("medio_pago"));
                pedido.setParaLlevar(rs.getByte("para_llevar"));
                
                
            }
        } catch (SQLException e) {
            throw new RuntimeException("El pedido buscado no existe");
        }
        return pedido;
    }

    //busca todos los pedido correspondientes al rut ingresado
    public List<Pedido> buscarPedidosCliente(int rut) {
        List<Pedido> lista = new ArrayList();
        String sql = "select * from pedido where rut=?";
        Pedido pedido = null;
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
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

    //busca ultimo pedido ingresado buscando el ticket mas alto 
    public int buscarUltimoTicket() {
        int max = 0;
        String sql = "select max(ticket) from pedido";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    max = rs.getInt("max(ticket)");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al Buscar el maximo numero de Ticket", ex);
        }

        //Pedido pedido = new Pedido();
        //String sql2 = "select * from pedido where ticket  = ?";
        //try (PreparedStatement stmt = cnx.prepareStatement(sql2)) {
        //    stmt.setInt(1, max);
        //    try (ResultSet rs = stmt.executeQuery()) {
        //        if (rs.next()) {
        //        pedido.setAgrandaBebidaPapas(rs.getByte("agranda_bedida_papas"));
        //        pedido.setMedioPago(rs.getString("medio_pago"));
        //        pedido.setParaLlevar(rs.getByte("para_llevar"));
        //        pedido.setRut(rs.getInt("rut"));
        //        pedido.setTicket(rs.getInt("ticket"));
        //        pedido.setTotal(rs.getInt("total"));
        //        }
        //    }
        //} catch (SQLException ex) {
        //    throw new RuntimeException("Error al Buscar  la ultima venta", ex);
        //}
        return max;

    }

    public int actualizarAgrandado(int ticket, byte agrandado) {
        Pedido pedido = buscar(ticket);
        int recargo=0;

        if (agrandado == 1) {
            if (pedido.getAgrandaBebidaPapas() == agrandado) {
                //Si son iguales no tiene q hacer nada.
            }else{
                String sql = "update pedido set agranda_bebida_papas = ? where ticket = ?";
                try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                    stmt.setByte(1, agrandado);
                    stmt.setInt(2, ticket);

                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Error al actualizar + Agrandado");
                }
            }    
        }
       
        if (agrandado == 0) {
            if (pedido.getAgrandaBebidaPapas() == agrandado) {
                //Si son iguales no tiene q hacer nada.
            }else{
                String sql = "update pedido set agranda_bebida_papas = ? where ticket = ?";
                try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                    stmt.setByte(1, agrandado);
                    stmt.setInt(2, ticket);

                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Error al actualizar - Agrandado");
                }
            }
        }

        Pedido pedi = buscar(ticket);
        
        if (pedi.getAgrandaBebidaPapas()==1) {
            recargo=990;
        }
        
       return recargo; 
    }
}
