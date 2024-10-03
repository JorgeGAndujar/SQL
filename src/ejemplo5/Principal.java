package ejemplo5;

import java.sql.*;

public class Principal {

    public static void main(String[] args) {
        Connection conexion = ConexionMysqlBDESCUELA.obtenerConexion();
        if (conexion != null) {
            System.out.println("OK: CONEXION");
            if (crearQueryTrigger(conexion)) {
                System.out.println("OK: CREAR TRIGGER");
                if (lanzarTrigger(conexion)) {
                    System.out.println("OK: LANZAR TRIGGER");
                    if (comprobarTrigger(conexion)) {
                        System.out.println("OK: COMPROBAR TRIGGER");
                    } else {
                        System.out.println("ERROR: COMPROBAR TRIGGER");
                    }
                } else {
                    System.out.println("ERROR: LANZAR TRIGGER");
                }
            } else {
                System.out.println("ERROR: TRIGGER");
            }
        } else {
            System.out.println("ERROR: CONEXION");
        }
    }

    public static boolean crearQueryTrigger(Connection conexion) {
        boolean bandera = true;
        PreparedStatement ps = null;
        String query1 = "DROP TRIGGER IF EXISTS trigger_auditoria_usuario;";
        String query2 = MetodosArchivo.leerQueryTrigger();
        try {
            ps = conexion.prepareStatement(query1);
            ps.execute();
            ps = conexion.prepareStatement(query2);
            ps.execute();
        } catch (SQLException e) {
            bandera = false;
        }
        return bandera;
    }

    public static boolean lanzarTrigger(Connection conexion) {
        boolean bandera = true;
        String nombre = "Alejandro";
        int n_de_expediente = 1001;
        int DNI = 12333678;
        String query = "UPDATE usuario SET nombre = ?, n_de_expediente = ? WHERE DNI = ?;";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setInt(2, n_de_expediente);
            ps.setInt(3, DNI);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("OK: FILA ACTUALIZADA");
            } else {
                System.out.println("ERROR: FILA NO ACTUALIZADA");
            }

        } catch (SQLException e) {
            bandera = false;
        }
        return bandera;
    }

    public static boolean comprobarTrigger(Connection conexion) {
        boolean bandera = true;
        String query1 = "SELECT * FROM auditoria_usuario";
        String query2 = "SELECT * FROM Usuario";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(query1);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getInt(6) + "  " + rs.getInt(7) + "  " + rs.getDate(8) + "  " + rs.getString(9));

            }
            ps = conexion.prepareStatement(query2);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getDate(4));

            }
        } catch (SQLException e) {
            bandera = false;

        }
        return bandera;
    }
}
