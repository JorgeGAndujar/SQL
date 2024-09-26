
package ejemplo3;

import ejemplo1.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysqlBDEnriptar {
       public static Connection obtenerConexion() {
        String url = "jdbc:mysql://localhost:3307/BDEncriptar";
        String usuario = "root";
        String clave = "12345678";
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (SQLException e) {
            conexion = null;
        }
        return conexion;
    }
}
