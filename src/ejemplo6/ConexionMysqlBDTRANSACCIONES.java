
package ejemplo6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysqlBDTRANSACCIONES {
    public static Connection obtenerConexion() {
        String url = "jdbc:mysql://localhost:3307/BDTRANSACCIONES";
        String usuario = "Jorge.G.Andujar";
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
