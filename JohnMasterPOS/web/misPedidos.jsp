<%-- 
    Document   : misPedidos
    Created on : 08-12-2015, 17:57:01
    Author     : Jordan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Pedidos</title>
    </head>
    <body>
         <center>
             <form action="<c:url value="/ControllerMisPedidos" />" method="post" >
        <table border="1">
            <tbody>
                <tr>
                    <td><img src="Imagen/logo.JPG" width="254" height="213" alt="logo"/>
                    </td>
                    <td><h3>Busca tus ultimos pedidos y vuelve a solicitarlos nuevamente</h3></td>
                </tr>
                <tr>
                    <td><%@include file="opciones.jsp" %><p>        </p></td></td>
                    <td><input type="text" name="rut" value=""/>  <input type="submit" value="Buscar"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><table border="1">
                        <tr>
                            <th><center>Pedido</center></th>
                            <th><center>Valor</center></th>
                            <th>Acción</th>
                        </tr>
                        
                            <c:forEach var="x" items="${lstdescripciones}">
                        <tr>
                            
                            <td><c:out value="${x}" /></td>
                             </c:forEach>
                            <c:forEach var="p" items="${lstPedidos}">
                            <td><c:out value="${p.total}" /></td>
                            <td>
                                
                            </td>
                        </tr>
                        </c:forEach>
                    </table></td>
                </tr>
            </tbody>
        </table>
            </center>
        </form>
    </body>
</html>
