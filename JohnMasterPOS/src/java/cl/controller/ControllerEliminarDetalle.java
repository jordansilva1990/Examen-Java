package cl.controller;

import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.dto.PedidoDetalleProductoDTO;
import cl.servicio.JohnMasterService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Jordan
 */
@WebServlet(name = "ControllerEliminarDetalle", urlPatterns = {"/ControllerEliminarDetalle"})
public class ControllerEliminarDetalle extends HttpServlet {

    @Resource(mappedName = "jdbc/johnmaster")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Fuera de Uso
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection cnx = ds.getConnection()) {
            JohnMasterService service = new JohnMasterService(cnx);
            PedidoDetalle detalleProducto = new PedidoDetalle();
            int total = 0;

            String codDetalleDel = request.getParameter("codigoProductoDEL");

            if (codDetalleDel != null) {

                int codigoDet = Integer.parseInt(codDetalleDel);
                detalleProducto = service.buscarUnDetalle(codigoDet);

                service.rebajarCantidadDetalle(detalleProducto);
            }

             // calculo de total en tiempo real
            List<PedidoDetalleProductoDTO> detalles = service.buscarElDetalleDelPedido(service.buscarUltimoPedido());

            if (detalles != null) {
                for (PedidoDetalleProductoDTO x : detalles) {
                    total += x.getPedidoDetalleDTO().getCantidad() * x.getProductoDTO().getValor();
                }
            }

            String[] agranda = request.getParameterValues("agranda_bebida_papas");
            if (agranda != null) {
                String checked = "1";
                service.actualizarAgrandado(detalleProducto.getTicket(), Byte.parseByte(checked));

            }
            Pedido pedidox = service.buscarUnPedido(detalleProducto.getTicket());
            if (pedidox.getAgrandaBebidaPapas() == 1) {
                total += 990;
            }

             service.actualizarTotal(detalleProducto.getTicket(), total);
             
            request.setAttribute("total", total);

            // se repite DoGet de Agregar Pedido para obtener los detalles           
            request.setAttribute("lstProductoDetalle", service.buscarElDetalleDelPedido(service.buscarUltimoPedido()));
            request.setAttribute("lsProducto", service.buscarTodosLosProductos());

            request.getRequestDispatcher("/pedidosHome.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
