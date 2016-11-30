/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.servlet.http.HttpSession;
import modelo.InicioDAO;
import modelo.LoginDAO;
import modelo.Publicacion;
import modelo.Usuario;

/**
 *
 * @author lki
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("usuarioSesion") != null ){
            System.out.println("doGet login");
            request.getRequestDispatcher("inicio.jsp").include(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LoginDAO loginDAO = new LoginDAO();
        String emailUsuario = request.getParameter("email");
        String contraseñaUsuario = request.getParameter("contrasenia");
        if (isUsuarioRegistrado(emailUsuario, contraseñaUsuario)) {
            //Traemos las publicaciones que han echo en el blog para mostrarlas
            //En el inicio del blog
            List<Publicacion> listaDePublicaciones = new ArrayList<>();
            listaDePublicaciones = new InicioDAO().listarPublicaciones();
            
            //Guardamos las publicaciones en la sesion y al usuario logeado
            //para que el usuario no 
            //tenga que volver a logearse
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuarioSesion", loginDAO.obtenerUsuarioRegistrado(new Usuario(emailUsuario, null, null, 0, contraseñaUsuario)));
            sesion.setAttribute("listaDePublicaciones", listaDePublicaciones);
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
            //Compartimos el usuario logeado en la sesion
            
            //Hacemos la redireccion
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("errorLogin.html").include(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean isUsuarioRegistrado(String emailUsuario, String contraseñaUsuario) {
        LoginDAO loginDAO = new LoginDAO();
        return loginDAO.obtenerUsuarioRegistrado(new Usuario(emailUsuario, null, null, 0, contraseñaUsuario)) != null;
    }

}
