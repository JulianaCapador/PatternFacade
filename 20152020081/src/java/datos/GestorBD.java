package datos;

import logica.datosPersonales;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBD {
    
    private static String nombreBD = "Personas";
    private static String nombreTabla = "datos";
    private static String usuario = "root";
    private static String clave = "mysql2017";
    private static String url = "jdbc:mysql://localhost/";
    private static String ubicacionControlador = "com.mysql.jdbc.Driver";
    
    private static Connection conector;
    
    private static GestorBD hoja;

    private GestorBD() {
        inicializarGestion();
    }

    public static GestorBD getHoja() {
        if (hoja == null) {
            hoja = new GestorBD();
        }
        return hoja;
    }
    
    private void inicializarGestion() {
        conectarBD("");
        crearBD(nombreBD);
        conectarBD(nombreBD);
        crearTabla(nombreTabla);
    }
    
    private void conectarBD(String nombreBD) {
        try {
            Class.forName(ubicacionControlador).newInstance();
            conector = DriverManager.getConnection(url + nombreBD, usuario, clave);
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Error al encontrar la clase controladora: "
                    + "\nt\t Descripción: " + classNotFoundException.getMessage() 
                    + "\n\t Localización: " + classNotFoundException.getLocalizedMessage());
        } catch (InstantiationException | IllegalAccessException excepcionDeEjemplificacion) {
            System.err.println("Error al ejemplificarla la clase controladora: "
                    + "\n\t Descripción: " + excepcionDeEjemplificacion.getMessage() 
                    + "\n\t Localización: " + excepcionDeEjemplificacion.getLocalizedMessage());
        } catch (SQLException sqlExcepcion) {
            System.err.println("Error al ejemplificarla la clase controladora: "
                    + "\n\t Descripción: " + sqlExcepcion.getMessage() 
                    + "\n\t Localización: " + sqlExcepcion.getLocalizedMessage() 
                    + "\n\t Resultado SQL: " + sqlExcepcion.getSQLState());
        }
    }
    
    private void crearBD(String nombreBD) {
        try {
            String consulta = "CREATE DATABASE IF NOT EXISTS " + nombreBD + ";";
            Statement declaracion = conector.createStatement();
            declaracion.execute(consulta);
        } catch (SQLException sqlExcepcion) {
            System.err.println("Error al crear la base de datos: "
                    + "\n\t Descripción: " + sqlExcepcion.getMessage() 
                    + "\n\t Localización: " + sqlExcepcion.getLocalizedMessage() 
                    + "\n\t Resultado SQL: " + sqlExcepcion.getSQLState());
        }
    }
    
    private void crearTabla(String nombreTabla) {
        try {
            String consulta = "CREATE TABLE IF NOT EXISTS " + nombreTabla
                    + "("
                    + "ID INT(11) NOT NULL AUTO_INCREMENT, "
                    + "NOMBRE VARCHAR(60) NOT NULL, "
                    + "APELLIDO VARCHAR(60) NOT NULL, "
                    + "DOCUMENTO VARCHAR(30) NOT NULL, "
                    + "TELEFONO VARCHAR(30) NOT NULL, "
                    + "DIRECCION VARCHAR(60) NOT NULL, "
                    + "PRIMARY KEY(ID)"
                    + ");";
            Statement declaracion = conector.createStatement();
            declaracion.execute(consulta);
        } catch (SQLException sqlExcepcion) {
            System.err.println("Error al crear la tabla en la base de datos: "
                    + "\n\t Descripción: " + sqlExcepcion.getMessage() 
                    + "\n\t Localización: " + sqlExcepcion.getLocalizedMessage() 
                    + "\n\t Resultado SQL: " + sqlExcepcion.getSQLState());
        }
    }
    
    public void insertarBD(datosPersonales nuevoRegistro) {
        try {
            String consulta = "INSERT INTO " + nombreTabla
                    + "(NOMBRE, APELLIDO, DOCUMENTO, TELEFONO, DIRECCION) "
                    + "VALUES ("
                    + "\"" + nuevoRegistro.getNombre() + "\", "
                    + "\"" + nuevoRegistro.getApellido() + "\", "
                    + "\"" + nuevoRegistro.getDocumento() + "\", "
                    + "\"" + nuevoRegistro.getTelefono() + "\", "
                    + "\"" + nuevoRegistro.getDireccion() + "\""
                    + ");";
            Statement declaracion = conector.createStatement();
            declaracion.executeUpdate(consulta);
        } catch (SQLException sqlExcepcion) {
            System.err.println("Error al insertar en la tabla en la base de datos: "
                    + "\n\t Descripción: " + sqlExcepcion.getMessage() 
                    + "\n\t Localización: " + sqlExcepcion.getLocalizedMessage() 
                    + "\n\t Resultado SQL: " + sqlExcepcion.getSQLState());
        }
    }
    
    public void actualizarBD(int id, datosPersonales personaModificada) {
        try {
            String consulta = "UPDATE " + nombreTabla + " SET "
                    + "NOMBRE = \"" + personaModificada.getNombre() + "\", "
                    + "APELLIDO = \"" + personaModificada.getApellido() + "\", "
                    + "DOCUMENTO = \"" + personaModificada.getDocumento() + "\", "
                    + "TELEFONO = \"" + personaModificada.getTelefono()+ "\", "
                    + "DIRECCION = \"" + personaModificada.getDireccion() + "\" "
                    + "WHERE ID = " + id + ";";
            Statement declaracion = conector.createStatement();
            declaracion.executeUpdate(consulta);
        } catch (SQLException sqlExcepcion) {
            System.err.println("Error al actualizar la tabla en la base de datos: "
                    + "\n\t Descripción: " + sqlExcepcion.getMessage() 
                    + "\n\t Localización: " + sqlExcepcion.getLocalizedMessage() 
                    + "\n\t Resultado SQL: " + sqlExcepcion.getSQLState());
        }
    }
    
    public ResultSet obtenerRegistros() {
        try {
            String consulta = "SELECT * FROM " + nombreTabla + ";";
            Statement declaracion = conector.createStatement();
            return declaracion.executeQuery(consulta);
        } catch (SQLException sqlExcepcion) {
            System.err.println("Error al obtener los registros desde la tabla en la base de datos: "
                    + "\n\t Descripción: " + sqlExcepcion.getMessage() 
                    + "\n\t Localización: " + sqlExcepcion.getLocalizedMessage() 
                    + "\n\t Resultado SQL: " + sqlExcepcion.getSQLState());
            return null;
        }
    }
    
    public void borrarRegistro(int id) {
        try {
            String consulta = "DELETE FROM " + nombreTabla + " WHERE ID = " + id + ";";
            Statement declaracion = conector.createStatement();
            declaracion.executeUpdate(consulta);
        } catch (SQLException sqlExcepcion) {
            System.err.println("Error al borrar el registro desde la tabla en la base de datos: "
                    + "\n\t Descripción: " + sqlExcepcion.getMessage() 
                    + "\n\t Localización: " + sqlExcepcion.getLocalizedMessage() 
                    + "\n\t Resultado SQL: " + sqlExcepcion.getSQLState());
        }
    }
    
}
