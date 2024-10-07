
package ejemplo6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrincipalRollBack {

    public static void main(String[] args) {
        Connection conexion = ConexionMysqlBDTRANSACCIONES.obtenerConexion();
        PreparedStatement ps = null;
        if (conexion != null) {
            System.out.println("OK: CONEXION");
            //TRANSACCIONES DELETE
            String query = "DELETE FROM Cliente WHERE id_cliente = 1";
            try {
                conexion.setAutoCommit(false);//STAR TRANSACTION
                ps = conexion.prepareStatement(query);

                int filaAfectadas = ps.executeUpdate();
                if (filaAfectadas > 0) {
                    System.out.println("OK: DELETE");
                } else {
                    System.out.println("ERROR: DELETE");
                }
                conexion.rollback();//DESCARTAR CAMBIOS
                System.out.println("CAMBIOS DESCARTADOS: ROLLBACK");

            } catch (SQLException e) {
                System.out.println("ERROR: TRANSACCION");
            }
        } else {
            System.out.println("ERROR: CONEXION");
        }
    }
    
}
