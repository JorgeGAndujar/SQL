package ejemplo4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejemplo_Trigger {

    public static void main(String[] args) {
        Connection conexion = ConexionMysqlBDESCUELA.obtenerConexion();
        if (conexion != null) {
            System.out.println("OK: CONEXION");
            if (EjemploTrigger(conexion)) {
                System.out.println("OK: ACTUALIZADO CORRECTAMENTE");
            } else {
                System.out.println("ERROR: NO SE PUDO ACTUALIZAR");
            }
        } else {
            System.out.println("ERROR: CONEXION");
        }
    }

    public static boolean EjemploTrigger(Connection conexion) {
        boolean bandera = true;
        String nombre = "Jorge";
        String n_de_expediente = "1001";
        int DNI = 12333678;
        String query = "UPDATE usuario SET nombre = ?, n_de_expediente = ? WHERE DNI = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, n_de_expediente);
            ps.setInt(3, DNI);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("OK UPDATE");
            } else {
                System.out.println("USUARIO NO EXISTE");
            }

        } catch (SQLException e) {
            bandera = false;
        }
        return bandera;

    }
}
