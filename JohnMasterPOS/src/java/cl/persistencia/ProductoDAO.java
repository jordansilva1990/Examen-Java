/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Persistencia;


import cl.dominio.Producto;
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
public class ProductoDAO {
    
    private Connection cnx;
    
    public ProductoDAO (Connection cnx)
    {
        this.cnx=cnx;
    }
    
    
    public void agregar (Producto p)
    {
        String sql = "insert into producto (id_producto,descripcion,valor)values(?,?,?)";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            
            stmt.setInt(1,p.getIdProducto());
            stmt.setString(2, p.getDescripcion());            
            stmt.setInt(3, p.getValor());
            
            int filasAfectadas = stmt.executeUpdate();
        }catch (SQLException ex) {
            throw new RuntimeException("Error al Agregar Producto", ex);
        }
        }
    
    public void eliminar(Producto p) {
        String sql = "delete from producto where id_producto = ?";
        
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdProducto());

            int filasAfectas = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al Eliminar el Producto", ex);
        }

    }
    
    public Producto buscar(int id_producto)
    {
        Producto prod = null;
        String sql = "select * from producto where id_producto  = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, id_producto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    prod= new Producto();
                    prod.setIdProducto(rs.getInt("id_producto"));                   
                    prod.setDescripcion(rs.getString("descripcion"));                    
                    prod.setValor(rs.getInt("valor"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al Buscar el Producto", ex);
        }
        return prod;
        
    }
    
    
    
    
    public List<Producto> buscarTodos()
    {
        List<Producto> lista = new ArrayList();
        String sql = "select * from producto order by id_producto";
          try (PreparedStatement stmt = cnx.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("id_producto"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setValor(rs.getInt("valor"));
                
                lista.add(prod);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al Buscar Todos los Productos", ex);
        }
        return lista;
    }
    
    }

