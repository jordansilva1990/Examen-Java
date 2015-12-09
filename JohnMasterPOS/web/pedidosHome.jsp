3<%-- 
    Document   : index
    Created on : 04-12-2015, 23:13:49
    Author     : Jordan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            table {
                border-collapse: collapse;
            }
            table,th,td {
                border: 1px solid black;
            }
            th,td{
                padding: 5px;
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td>LOGO</td>
                <td>Bienvenidos a John Master, aquí ud. encontrará los mejores productos</br>
                    Indiquenos su Nombre y su Rut para identificarlo en el pedido</td>
                </br>
            </tr>

            <form action="<c:url value="/ControllerAgregarPedido" />" method="post" >
                <tr>
                <td><%@include file="opciones.jsp" %></td>
                <td>Nombre: <input type="text" name="nombre" value="<c:out value="${cliente.nombre}" />" /> <c:out value="${mapMensajes['nombreCliente']}" /></br>
                    Rut   : <input type="text" name="rut" value="<c:out value="${cliente.rut}" />" /> <c:out value="${mapMensajes['rutCliente']}" /></td>
            </tr>
            
            
             <tr>
                <td></td>
                <td>Seleccione un producto o un combo, indique su cantidad y agregue al pedido </br>
                    <select class="dropdown-button btn" name="lsProducto" >
                                    <option value="">(Seleccione)</option>
                                    <c:forEach var="p" items="${lsProducto}">
                                        <option value="${p.idProducto}" ${p.idProducto == productoDTO.idProducto ? 'Selected' : ''}>
                                            <c:out value="${p.descripcion}"/>
                                        </option>
                                    </c:forEach>
                    </select> <input type="submit" method="POST" value="Agregar" /> <c:out value="${mapMensajes['detallePedidoAgregar']}" />
                </td>
            </tr>
            
            <tr>
                <td><input type="checkbox" name="agranda_bebida_papas" value="1" /> Agranda bedida y papas</br>
                    <input type="checkbox" name="pedido_llevar" value="1" /> Pedido para llevar</br></br>
                    Medio de Pago</br>
                    <input type="radio" name="medio_pago"  value="" /> Efectivo</br>
                    <input type="radio" name="medio_pago" value="" /> Tarjeta debito / credito</td>
                <td>            </form>

                    <table>
                        <tr>
                            <th><center>Producto</center></th>
                            <th><center>Cantidad</center></th>
                            <th></th>
                        </tr>
                        <c:forEach var="p" items="${lstProductoDetalle}">

                        <tr>
                            <td><c:out value="${p.productoDTO.descripcion}" /></td>
                            <td><c:out value="${p.pedidoDetalleDTO.cantidad}" /></td>
                            <td>
                                <c:url var="urlEliminar" value="/ControllerEliminarDetalle">
                                    <c:param name="codigoProducto" value="${param.id_pedido_detalle}" />
                                </c:url>
                                <form action="${urlEliminar}" method="post">
                                    <input type="hidden" name="codigoProductoDEL" value="${p.pedidoDetalleDTO.idPedidoDetalle}" />
                                    <input type="submit" value="-" />
                                </form>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
            <tr>
                <form action="<c:url value="/ControllerPasarPedido" />" method="post" >
                <td><h2>Total: $ <c:out value="${total}"/></h2></td>
                <td><input type="submit" value="Enviar Pedido"</td>
            </tr>
            </form>
        </table>
                
    </body>
</html>
