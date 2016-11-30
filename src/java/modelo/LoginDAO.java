/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author lki
 */
public class LoginDAO {

    public Usuario obtenerUsuarioRegistrado(Usuario usuario) {
        Usuario usuarioRegistrado = null;
        String sql = "SELECT * FROM usuarios WHERE  email = ? AND contraseña = ?";
        Connection conexion = Conexion.getConexion();
        try {
            java.sql.PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setString(1, usuario.getEmail());
            pstm.setString(2, usuario.getContraseña());
            //Obtenemos todos los datos que se encuentran en la tabla post
            ResultSet resultado = pstm.executeQuery();
            //Mientras el resultado tenga datos
            while (resultado.next()) {
                usuarioRegistrado = new Usuario();
                usuarioRegistrado.setNombre(resultado.getString("nombre"));
                usuarioRegistrado.setApellido(resultado.getString("apellido"));
                usuarioRegistrado.setEdad(resultado.getInt("edad"));
                usuarioRegistrado.setNombre(resultado.getString("nombre"));
                usuarioRegistrado.setEmail(resultado.getString("email"));
                usuarioRegistrado.setContraseña(resultado.getString("contraseña"));

            }
        } catch (Exception e) {
            System.err.println("Hubo un error validar login usuario" + e);

        }

        return usuarioRegistrado;
    }

    public static void main(String[] ar) {

        LoginDAO loginDAO = new LoginDAO();
        Usuario isUsuarioRegistrado = loginDAO.obtenerUsuarioRegistrado(new Usuario("email@hotmail", null, null, 0, "1234"));
        System.out.println("El usuario esta registrado " + isUsuarioRegistrado != null);
    }

}
