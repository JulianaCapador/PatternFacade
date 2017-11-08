package programa;

import logica.datosPersonales;
import datos.GestorBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletInsertar", urlPatterns = {"/ServletInsertar"})
public class ServletInsertar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        try {
            request.setCharacterEncoding("UTF-8");
            
            //Toma el texto que se ingresa
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String documento = request.getParameter("documento");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            
            datosPersonales nuevoHumano = new datosPersonales();
            
            nuevoHumano.setNombre(nombre);
            nuevoHumano.setApellido(apellido);
            nuevoHumano.setDocumento(documento);
            nuevoHumano.setTelefono(telefono);
            nuevoHumano.setDireccion(direccion);
            
            GestorBD gestor = GestorBD.getHoja();
            gestor.insertarBD(nuevoHumano);
            out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado de la inserción</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>La persona " + nombre + " ha sido ingresado con éxito</h1>");
            out.println("<br>");
            out.println("<a href=\"index.jsp\">Volver a Inicio</a>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        } catch (IOException ioException) {
            System.err.println("Error en la escritura del servlet: "
                    + "\n\t Descripción: " + ioException.getMessage()
                    + "\n\t Localización: " + ioException.getLocalizedMessage());

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

}
