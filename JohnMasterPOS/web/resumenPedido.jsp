<%-- 
    Document   : resumenPedido
    Created on : 06-12-2015, 21:13:42
    Author     : Jordan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resumen de su Pedido</title>
    </head>
    <body>
    <center><table>
            <tr>
                <td><img src="Imagen/logo.JPG" width="254" height="213" alt="logo"/>
                </td>
                <td></td>
            </tr>
            <tr>
                <td><%@include file="opciones.jsp" %></td>
                <td>
            <center><h3>Muchas Gracias ! <c:out value="${cliente.nombre}" /></br>
                    Tu Número de Pedido es: <c:out value="${pedido.ticket}" /></br>
                    
                    Monto de su pedido: $ <c:out value="${pedido.total}" /></h3></center>
                </td>
            </tr>
            <tr>
                <td></td>
            <center><td>Por favor pase por caja para que luego pueda esperar tranquilamente su pedido</td></center>
            </tr>
        </table></center>
    </body>
</html>
