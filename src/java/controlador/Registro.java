package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Conexion;
import modelo.Publicacion;
import modelo.InicioDAO;
import modelo.InscripcionDAO;
import modelo.Usuario;

/**
 *
 * @author lki
 */
@WebServlet(name = "Registro", urlPatterns = {"/registro"})
public class Registro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String edad = request.getParameter("edad");
        String contraseña = request.getParameter("contrasena");
        System.out.println("email "+email+" nombre "+nombre+" apellido "+apellido+" edad "+edad+" pwd "+contraseña);

        if (isDatosCorrectos(email, nombre, apellido, edad,contraseña)) {
            Usuario usuario = new Usuario(email, nombre, apellido, Integer.parseInt(edad),contraseña);
            int resultadosAfectados = new InscripcionDAO().registrarUsuario(usuario);

            if (resultadosAfectados != 0) {
                //Si se pudo registrar al usuario en la base de datos
                List<Publicacion> listaDePublicaciones = new ArrayList<>();
                listaDePublicaciones = new InicioDAO().listarPublicaciones();
                request.setAttribute("listaDePublicaciones", listaDePublicaciones);
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            } else {
                //Mostramos una pagina de error
                response.sendRedirect("error.html");
            }
        } else {
            //Mostramos una pagina de error
            response.sendRedirect("error.html");
        }

    }

    private boolean isDatosCorrectos(String email, String nombre, String apellido, String edad,String contraseña) {
        boolean isEdadCorrecto = true;

        try {
            Integer.parseInt(edad);
        } catch (Exception e) {
            isEdadCorrecto = false;
        }

        return !email.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !contraseña.isEmpty() && isEdadCorrecto;
    }

}
