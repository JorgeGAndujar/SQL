package ejemplo4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejemplo2AfterInsert {

    public static void main(String[] args) {
       Connection conexion = ConexionMysqlBDTRIGGER.obtenerConexion();
        if (conexion != null) {
            System.out.println("OK CONEXION");
            String query = "INSERT INTO Empleado (nombre, salario) VALUES (?,?);";
            try {
                // Validación del salario antes de ejecutar la consulta
                int salario = -2000;
                if (salario < 0) {
                    System.out.println("SALARIO NO VALIDO");
                } else {
                    PreparedStatement ps = conexion.prepareStatement(query);
                    ps.setString(1, "Anonimo");  // Cambiar nombre en el índice 1
                    ps.setInt(2, salario);  // Especificar el SALARIO en el índice 2
                    int filasAfectadas = ps.executeUpdate(); // Devuelve el número de filas afectadas
                    
                    if(filasAfectadas > 0){
                        System.out.println("INSERCIÓN OK");
                    } else {
                        System.out.println("INSERCIÓN FALLIDA");
                    }
                }

            } catch (SQLException e) {
                System.out.println("ERROR QUERY: " + e.getMessage());
            }

        } else {
            System.out.println("ERROR CONEXION");
        }
    }
}

