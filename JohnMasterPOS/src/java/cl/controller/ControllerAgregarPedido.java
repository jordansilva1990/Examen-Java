/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.dominio.Cliente;
import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import cl.dto.PedidoDetalleProductoDTO;
import cl.servicio.JohnMasterService;
import cl.servicio.ServicioException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "ControllerAgregarPedido", urlPatterns = {"/ControllerAgregarPedido"})
public class ControllerAgregarPedido extends HttpServlet {

    @Resource(mappedName = "jdbc/johnmaster")
    private DataSource ds;

    private Pedido pedido;

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
        String mensaje;
        try (Connection cnx = ds.getConnection()) {
            Map<String, String> mapMensajes = new HashMap<>();
            JohnMasterService service = new JohnMasterService(cnx);
            int total = 0;

            PedidoDetalleProductoDTO detalleProducto = new PedidoDetalleProductoDTO();
            List<PedidoDetalleProductoDTO> detallesproductos = new ArrayList<PedidoDetalleProductoDTO>();

            Producto producto = null;

            //verifica si se selecciono algun producto
            String strIdProducto = request.getParameter("lsProducto");
            if (strIdProducto.isEmpty()) {
                mapMensajes.put("lsProducto", "Tiene que seleccionar un producto para vender!!");
            }
            //String cantidad= request.getParameter("cantidad");
            Cliente cliente = new Cliente();
            String nombre = request.getParameter("nombre");
            if (nombre.isEmpty()) {
                mapMensajes.put("nombre_cli", "Debe Ingresar Nombre!!");
            } else {
                cliente.setNombre(nombre);
            }

            String strRut = request.getParameter("rut");
            if (strRut.isEmpty()) {
                mapMensajes.put("rut_cli", "Debe Ingresar Rut");
            } else {
                cliente.setRutCliente(Integer.parseInt(strRut));
            }

            if (mapMensajes.isEmpty()) {

                PedidoDetalle detalle = new PedidoDetalle();

                detalle.setTicket(service.buscarUltimoPedido());
                detalle.setIdProducto(Integer.parseInt(strIdProducto));

                detalle.setCantidad(1);

                // se ingresa y recupera el detalle con su id
                service.agregarDetallePedido(detalle);
                detalle = service.buscarUltimoDetalle();

                // se trae el producto del detalle
                producto = service.buscarUnProducto(detalle.getIdProducto());

                detalleProducto = new PedidoDetalleProductoDTO(detalle, producto);

                // se agrega el detalle junto al producto para luego mostrar en JSP
                detallesproductos.add(detalleProducto);

                producto = service.buscarUnProducto(Integer.parseInt(strIdProducto));

                // calculo de total en tiempo real
                List<PedidoDetalleProductoDTO> detalles = service.buscarElDetalleDelPedido(service.buscarUltimoPedido());

                if (detalles != null) {
                    for (PedidoDetalleProductoDTO x : detalles) {
                        total += x.getPedidoDetalleDTO().getCantidad() * x.getProductoDTO().getValor();
                    }
                }

                int numtcket = detalle.getTicket();

                String[] agranda = request.getParameterValues("agranda_bebida_papas");
                if (agranda != null) {
                    String checked = "1";
                    total += service.actualizarAgrandado(detalle.getTicket(), Byte.parseByte(checked));

                }
                
                String[] llevar = request.getParameterValues("pedido_llevar");
                if (agranda != null) {
                    String checked = "1";
                   service.actualizarParaLlevar(detalle.getTicket(), Byte.parseByte(checked));

                }
                Pedido pedidox = service.buscarUnPedido(detalle.getTicket());
                if (pedidox.getAgrandaBebidaPapas() == 1) {
                    total += 990;
                }

                service.actualizarTotal(numtcket, total);

                service.agregarCliente(cliente);

                pedido = service.buscarUnPedido(service.buscarUltimoPedido());
                pedido.setRut(Integer.parseInt(strRut));
                pedido.setMedioPago("test");
               // pedido.setParaLlevar(Byte.parseByte("1"));

                service.modificarPedido(pedido);

            }
            request.setAttribute("mapMensajes", mapMensajes);
            request.setAttribute("total", total);
            request.setAttribute("cliente", cliente);
            
            //request.setAttribute("lsProducto", service.buscarTodosLosProductos());
            //request.setAttribute("lstProductoDetalle", service.buscarTodosLosDetallesPedidoProducto());
            request.setAttribute("lstProductoDetalle", service.buscarElDetalleDelPedido(service.buscarUltimoPedido()));
            request.setAttribute("lsProducto", service.buscarTodosLosProductos());

            request.getRequestDispatcher("/pedidosHome.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServicioException ex) {
            Logger.getLogger(ControllerPasarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
