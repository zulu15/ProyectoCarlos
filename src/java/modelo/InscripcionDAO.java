package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InscripcionDAO {

    public int registrarUsuario(Usuario usuario) {

        //INSERT INTO usuarios (`email`, `nombre`, `apellido`, `edad`) VALUES (?, ?, ?, ?);
        int registrosAfectados = 0;
        String sql = "INSERT INTO usuarios (`email`, `nombre`, `apellido`, `edad`, `contraseña` ) VALUES ( ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sql);
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellido());
            preparedStatement.setInt(4, usuario.getEdad());
            preparedStatement.setString(5, usuario.getContraseña());

            registrosAfectados = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return registrosAfectados;

    }

}
