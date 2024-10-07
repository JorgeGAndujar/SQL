package ejemplo6;

import java.sql.*;

public class PrincipalCommit {

    public static void main(String[] args) {
        Connection conexion = ConexionMysqlBDTRANSACCIONES.obtenerConexion();
        PreparedStatement ps = null;
        if (conexion != null) {
            System.out.println("OK: CONEXION");
            //TRANSACCIONES INSERT
            String query1 = "INSERT INTO Cliente (nombre, email) VALUES('Arturo','arturo@gmail.com');";
            String query2 = "INSERT INTO Cliente (nombre, email) VALUES('Dafne','dafne@gmail.com');";
            try {
                conexion.setAutoCommit(false);//STAR TRANSACTION
                ps = conexion.prepareStatement(query1);

                int filaAfectadas1 = ps.executeUpdate();
                if (filaAfectadas1 > 0) {
                    System.out.println("OK INSERT QUERY1");
                } else {
                    System.out.println("ERROR INSERT QUERY1");
                }
                ps = conexion.prepareStatement(query2);
                int filaAfectadas2 = ps.executeUpdate();
                if (filaAfectadas2 > 0) {
                    System.out.println("OK INSERT QUERY2");
                } else {
                    System.out.println("ERROR INSERT QUERY2");
                }

                conexion.commit();//CONFIRMAR CAMBIOS
                System.out.println("CAMBIOS CONFIRMADOS: COMMIT");

            } catch (SQLException e) {
                System.out.println("ERROR: TRANSACCION");
            }
        } else {
            System.out.println("ERROR: CONEXION");
        }
    }

}
