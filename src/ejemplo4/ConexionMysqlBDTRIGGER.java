
package ejemplo4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysqlBDTRIGGER {
          public static Connection obtenerConexion() throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3307/BDTRIGGER";
        String usuario = "root";
        String clave = "12345678";
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (SQLException e) {
            conexion = null;
        }
        return conexion;
    }
}
