package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    //
    private static Connection conexion = null;

    public static Connection getConexion() {

        if (conexion == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //No se ha creado conexion con la base de datos
                conexion = DriverManager.getConnection("jdbc:mysql://localhost/final_noviembre", "root", "root");
                System.out.println("La conexión a la base de datos fue exitosa");
            } catch (SQLException ex) {
                System.err.println("Se genero una "
                        + "excepcion al conectarse con la base de datos");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                System.err.println("No se encontro el driver MYSQL");
                ex.printStackTrace();
            }
        }
        
        return conexion;
    }

}
