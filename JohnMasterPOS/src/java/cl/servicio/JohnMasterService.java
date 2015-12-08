/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.persistencia.ClienteDAO;
import cl.persistencia.ProductoDAO;
import cl.dominio.Cliente;
import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import cl.dto.PedidoDetalleProductoDTO;
import cl.persistencia.PedidoClienteDAO;
import cl.persistencia.PedidoDAO;
import cl.persistencia.PedidoDetalleDAO;
import cl.persistencia.PedidoDetalleProductoDAO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Simon
 */
public class JohnMasterService {
    
    ClienteDAO clienteDAO;
    PedidoClienteDAO pedidoClienteDAO;
    PedidoDAO pedidoDAO;
    PedidoDetalleDAO pedidoDetalleDAO;
    PedidoDetalleProductoDAO pedidoDetalleProductoDAO;
    ProductoDAO productoDAO;

    public JohnMasterService(Connection cnx) {
        clienteDAO = new ClienteDAO(cnx);
        pedidoClienteDAO = new PedidoClienteDAO(cnx);
        pedidoDAO = new PedidoDAO(cnx);
        pedidoDetalleDAO = new PedidoDetalleDAO(cnx);
        pedidoDetalleProductoDAO = new PedidoDetalleProductoDAO(cnx);
        productoDAO = new ProductoDAO(cnx);
    }
    
    public void agregarCliente(Cliente cli) throws ServicioException{
        Cliente bd = clienteDAO.buscar(cli.getRutCliente());
        if (bd==null) {
            clienteDAO.agregar(cli);
        }
    }
    
    public Cliente buscarUnCliente(int rutCli){
        return clienteDAO.buscar(rutCli);
    }
    
    public List<Cliente> buscarTodosLosClientes(){
        return clienteDAO.buscarTodos();
    }
    
    public void agregarPedido(Pedido ped) throws ServicioException{
        pedidoDAO.create(ped);
    }
    
    public Pedido buscarUnPedido(int ticket){
        return pedidoDAO.buscar(ticket);
    }
    
    public Producto buscarUnProducto(int id_prod){
        return productoDAO.buscar(id_prod);
    }
    
    public List<Producto> buscarTodosLosProductos(){
        return productoDAO.buscarTodos();
    }
    
    public void agregarDetallePedido(PedidoDetalle pedDetalle){
        pedidoDetalleDAO.agregar(pedDetalle);
    }
    
    public void eliminarDetallePedido(int id_pedido_det){
        pedidoDetalleDAO.eliminar(id_pedido_det);
    }
    
    public List<PedidoDetalle> buscarTodosLosDetallesPedido(){
        return pedidoDetalleDAO.buscarTodos();
    }
    
    public List<PedidoDetalleProductoDTO> buscarTodosLosDetallesPedidoProducto(){
        return pedidoDetalleProductoDAO.buscarTodosLosPedidoDetalleProducto();
    }
    
    public int buscarUltimoPedido()
    {
        return pedidoDAO.buscarUltimoTicket();
    }
    
    public PedidoDetalle buscarUltimoDetalle()
    {
       return pedidoDetalleDAO.buscarUltimoDetalle();
    }
    
    public List<PedidoDetalleProductoDTO> buscarElDetalleDelPedido(int ticket){
        return pedidoDetalleProductoDAO.buscarPedidoDetallePorPedido(ticket);
    }
    public PedidoDetalle buscarUnDetalle(int idpedido)
    {
        return pedidoDetalleDAO.buscarUnDetalle(idpedido);
    }
    
    public void rebajarCantidadDetalle(PedidoDetalle pedidoDetalle)
    {
        pedidoDetalleDAO.eliminar2(pedidoDetalle);
    }
}
