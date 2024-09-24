package ejemplo1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MostrarDescripcionGrupo {

    public static void main(String[] args) {
        Connection conexion = ConexionMysql.obtenerConexion();
        if (conexion != null) {
            String query = "CALL mostrar_grupo_mensaje()";
            try {
                CallableStatement cs = conexion.prepareCall(query);
                boolean hasResultSet = cs.execute();
                if (hasResultSet) {
                    ResultSet rs = cs.getResultSet();
                    while (rs.next()) {
                        String nombre = rs.getString(1);
                        String grupo = rs.getString(2);
                        String descripción = rs.getString(3);
                        System.out.format("%-10s %-20s %-100s\n", nombre, grupo, descripción);
                    }
                    System.out.println("SI HAY RESULTADOS");
                } else {
                    System.out.println("NO HAY RESULTADOS");
                }
            } catch (SQLException e) {
                System.out.println("ERROR DE QUERY" + e);
            }
        } else {
            System.out.println("ERROR DE CONEXION");
        }
    }

}
