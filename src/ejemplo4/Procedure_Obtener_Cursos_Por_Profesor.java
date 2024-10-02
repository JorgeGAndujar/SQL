package ejemplo4;

import java.sql.*;

public class Procedure_Obtener_Cursos_Por_Profesor {

    public static void main(String[] args) {
        Connection conexion = ConexionMysqlBDESCUELA.obtenerConexion();
        if (conexion != null) {
            System.out.println("OK: CONEXION");
            if (Obtener_Cursos_Por_Profesor(conexion)) {
                System.out.println("OK: OBTENIDO CURSOS POR PROFESOR");
            } else {
                System.out.println("ERROR: NO SE OBTIENE NINGUN REGISTRO");
            }
        } else {
            System.out.println("ERROR: CONEXION");
        }
    }

    public static boolean Obtener_Cursos_Por_Profesor(Connection conexion) {
        boolean bandera = true;
        int DNI = 12345678;
        String query = "CALL obtener_cursos_por_profesor(?);";
        try {
            // Preparar la consulta
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, DNI);
            
            // Ejecutar la consulta y obtener resultados
            ResultSet rs = ps.executeQuery();

            // Procesar los resultados
            if (!rs.isBeforeFirst()) { // Verificar si hay resultados
                System.out.println("ERROR: NO SE OBTIENE NINGUN REGISTRO");
                bandera = false;
            } else {
                // Mostrar los resultados en la consola
                while (rs.next()) {
                    String profesor = rs.getString("Profesor");
                    String modulo = rs.getString("Modulo");
                    int delegado = rs.getInt("delegado");
                    int grupo_alumnos = rs.getInt("grupo_alumnos");

                    System.out.println("Profesor: " + profesor + 
                                       ", Módulo: " + modulo + 
                                       ", Delegado: " + delegado + 
                                       ", Grupo Alumnos: " + grupo_alumnos);
                }
            }

            // Cerrar ResultSet y PreparedStatement
            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e);
            bandera = false;
        }
        return bandera;
    }
}
