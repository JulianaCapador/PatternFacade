<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel =\"stylesheet\" type=\"text/css\" href=\"WebPages/css/estilosPrincipal.css\" />
        <title>Insertar un Nuevo Producto</title>
    </head>
    <body>
        <h1>Diligencie los siguientes campos</h1>
        <form action="ServletInsertar" method="post">
            <table border="1">
                <thead>
                    <tr>
                        <th>Rótulo</th>
                        <th>Campo</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Nombre:</td>
                        <td><input type="text" name="nombre" required=""></td>
                    </tr>
                    <tr>
                        <td>Apellido:</td>
                        <td><input type="text" name="apellido" required=""></td>
                    </tr>
                    
                    <tr>
                      <td>Documento:</td>
                        <td><input type="text" name="documento" required=""></td>
                    </tr>
                    
                    <tr>
                        <td>Telefono:</td>
                        <td><input type="text" name="telefono" required=""></td>
                    </tr>
                    
                    <tr>
                        <td>Direccion:</td>
                        <td><input type="text" name="direccion" required=""></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit">
        </form>
    </body>
</html>
