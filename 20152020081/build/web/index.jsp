<%@page import="java.sql.ResultSet"%>
<%@page import="datos.GestorBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Base de Datos para registrar los productos</title>
        <link rel="stylesheet" type="text/css" href="/BaseDatos/Web Pages/css/estilosUno.css"/>
    </head>
    <body>
    <center><h1>Personas Registradas</h1>
        <%
            GestorBD gestor = GestorBD.getHoja();
            ResultSet conjuntoRegistros = gestor.obtenerRegistros();
            boolean apuntador = conjuntoRegistros.next();
            if (!apuntador) {
                out.println("<h2>No hay registros</h2>");
            } else {
                %>
                
                <table border = "1">
                    <tr>
                        <td>ID</td>
                        <td>Nombre</td>
                        <td>Apellido</td>
                        <td>Documento</td>
                        <td>Telefono</td>
                        <td>Direccion</td>
                    </tr>
                    <%
                        while(apuntador) {
                            out.println("<tr>");
                            out.println("<td>" + conjuntoRegistros.getString("ID") + "</td>");
                            out.println("<td>" + conjuntoRegistros.getString("NOMBRE") + "</td>");
                            out.println("<td>" + conjuntoRegistros.getString("APELLIDO") + "</td>");
                            out.println("<td>" + conjuntoRegistros.getString("DOCUMENTO") + "</td>");
                            out.println("<td>" + conjuntoRegistros.getString("TELEFONO") + "</td>");
                            out.println("<td>" + conjuntoRegistros.getString("DIRECCION") + "</td>");
                            out.println("</tr>");
                            apuntador = conjuntoRegistros.next();
                        }
                    %>
                </table>
                <%
            }
        %>
        <input type="button" onclick="document.location.reload();" value="Actualizar Tabla">
        <a href="insertar.jsp">Insertar nuevo registro</a>
    </center>
    </body>
</html>
