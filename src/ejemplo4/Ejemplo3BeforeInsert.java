
package ejemplo4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejemplo3BeforeInsert {

    public static void main(String[] args) {
        Connection conexion = ConexionMysqlBDTRIGGER.obtenerConexion();
        if (conexion != null) {
            System.out.println("OK CONEXION");
            String query = "INSERT INTO Usuario (nombre, clave) VALUES (?,?);";
            try {
                // Inicializamos una clave para la prueba
                String clave = "12345678"; // clave como cadena de texto
                
                // Validación de la clave
                if (clave.equals("12345678")) {
                    System.out.println("CLAVE NO VALIDA");
                } else {
                    PreparedStatement ps = conexion.prepareStatement(query);
                    ps.setString(1, "Anonimo");  // Cambiar nombre en el índice 1
                    ps.setString(2, clave);  // Asignar la clave en el índice 2
                    int filasAfectadas = ps.executeUpdate(); // Devuelve el número de filas afectadas
                    
                    if (filasAfectadas > 0) {
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
