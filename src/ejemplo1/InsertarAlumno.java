package ejemplo1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

public class InsertarAlumno {

    public static void main(String[] args) {
        String[][] registros = {
            {"Lucho", "Martinez Rodriguez", "dam", "2000-11-14"},
            {"Pablo", "Mar Quiroga", "daw", "2005-10-20"},
            {"Mar", "Lacón González", "dam", "1993-08-18"}
        };
        Connection conexion = ConexionMysql.obtenerConexion();
        if (conexion != null) {
            String query = "CALL insertar_alumnos(?,?,?,?)";
            try {
                for (int i = 0; i < registros.length; i++) {
                    CallableStatement cs = conexion.prepareCall(query);
                    cs.setString("nombre_i", registros[i][0]);
                    cs.setString("apellido_i", registros[i][1]);
                    cs.setString("grupo_i", registros[i][2]);
                    cs.setDate("fecha_nacimiento_i", Date.valueOf(registros[i][3]));
                    cs.execute();
                }
            } catch (SQLException e) {
                System.out.println("ERROR DE QUERY" + e);
            }
        } else {
            System.out.println("ERROR DE CONEXION");
        }

    }
}
