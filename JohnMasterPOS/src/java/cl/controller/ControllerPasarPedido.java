/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.dominio.Cliente;
import cl.dominio.Pedido;
import cl.servicio.JohnMasterService;
import cl.servicio.ServicioException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ControllerPasarPedido", urlPatterns = {"/ControllerPasarPedido"})
public class ControllerPasarPedido extends HttpServlet {

    @Resource(mappedName = "jdbc/johnmaster")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection cnx = ds.getConnection()) {
            JohnMasterService service = new JohnMasterService(cnx);

            Cliente cliente = new Cliente();
            cliente.setNombre("vacio");
            cliente.setRutCliente(1111);
            service.agregarCliente(cliente);

            Pedido pedido = new Pedido();
            pedido.setAgrandaBebidaPapas(Byte.parseByte("0"));
            pedido.setMedioPago(" ");
            pedido.setParaLlevar(Byte.parseByte("0"));
            pedido.setRut(cliente.getRutCliente());
            pedido.setTotal(0);
            service.agregarPedido(pedido);

            request.setAttribute("lsProducto", service.buscarTodosLosProductos());
            request.getRequestDispatcher("/pedidosHome.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServicioException ex) {
            Logger.getLogger(ControllerPasarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection cnx = ds.getConnection()) {
            JohnMasterService service = new JohnMasterService(cnx);
            Pedido pedido = service.buscarUnPedido(service.buscarUltimoPedido());
            Cliente cliente = service.buscarUnCliente(pedido.getRut());
            Map<String, String> mapMensajes = new HashMap<>();

            if (pedido.getTotal() > 0) {
            } else {
                mapMensajes.put("detalles", "Debe ingresar al menos un producto para  continuar");
            }

            if (cliente.getNombre() != null) {

            } else {
                mapMensajes.put("nombre", "Debe ingresar su nombre para  continuar");
            }
            if (mapMensajes.isEmpty()) {
                
                
                request.setAttribute("cliente", cliente);
                request.setAttribute("pedido", pedido);
                request.setAttribute("mapMensajes", mapMensajes);
                request.getRequestDispatcher("/resumenPedido.jsp").forward(request, response);
            }
            request.setAttribute("lsProducto", service.buscarTodosLosProductos());
            request.getRequestDispatcher("/pedidosHome.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
