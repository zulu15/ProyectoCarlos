/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lki
 */
public class InicioDAO {

    public List<Publicacion> listarPublicaciones() {
        List<Publicacion> listaDePublicaciones = new ArrayList<>();
        String sql = "SELECT * FROM post";
        Connection conexion = Conexion.getConexion();
        try {
            java.sql.PreparedStatement pstm = conexion.prepareStatement(sql);
            //Obtenemos todos los datos que se encuentran en la tabla post
            ResultSet resultado = pstm.executeQuery();
            //Mientras el resultado tenga datos
            while (resultado.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setId(resultado.getInt("id"));
                publicacion.setDescripcion(resultado.getString("descripcion"));
                publicacion.setTitulo(resultado.getString("titulo"));
                publicacion.setFecha(resultado.getDate("fecha"));
                //agregamos a la lista la publicacion
                listaDePublicaciones.add(publicacion);

            }
        } catch (Exception e) {
            System.err.println("Hubo un error al listar los post " + e);

        }

        return listaDePublicaciones;
    }

    
    // Testeo del metodo listarPublicaciones
    public static void main(String[] arg) {
        List<Publicacion> listaDePublicaciones = new ArrayList<>();
        listaDePublicaciones = new InicioDAO().listarPublicaciones();
        for(Publicacion publicacion:listaDePublicaciones){
            System.out.println(publicacion);
        }
        
    }

}
