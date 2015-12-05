<%-- 
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

            <tr>
                <td><c:url value="/ControllerPedidosHome" /></br>
                    <c:url value="/ControllerPedidosTicket" /></td>
                <td>Nombre: <input type="text" name="nombre" value="<c:out value="${cliente.nombre}" />" /> <c:out value="${mapMensajes['nombreCliente']}" /></br>
                    Rut   : <input type="text" name="rut" value="<c:out value="${cliente.rut}" />" /> <c:out value="${mapMensajes['rutCliente']}" /></td>
            </tr>
            
            <tr>
                <td></td>
                <td>Seleccione un producto o un combo, indique su cantidad y agregue al pedido </br>
                    <select class="dropdown-button btn" name="lsProducto" >
                                    <option value="">(Seleccione)</option>
                                    <c:forEach var="p" items="${lsProducto}">
                                        <option value="${p.idProducto}" ${p.idProducto == producto.idProducto ? 'Selected' : ''}>
                                            <c:out value="${p.nombre}"/>
                                        </option>
                                    </c:forEach>
                    </select> <input type="submit" method="POST" value="Agregar" /> <c:out value="${mapMensajes['detallePedidoAgregar']}" />
                </td>
            </tr>
        </table>
    </body>
</html>
