package ejemplo4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejemplo1AfterUpdate {

    public static void main(String[] args) {
        Connection conexion = ConexionMysqlBDTRIGGER.obtenerConexion();
        if (conexion != null) {
            System.out.println("OK CONEXION");
            String query = "UPDATE usuario SET nombre = ?, clave = 'XYZ' WHERE id_usuario = ?;";
            try {
                PreparedStatement ps = conexion.prepareStatement(query);
                ps.setString(1, "Luis");  // Cambiar nombre en el índice 1
                ps.setInt(2, 2);  // Especificar el id_usuario en el índice 2
                int n = ps.executeUpdate(); //INSERT UPDATE DELETE
                if(n > 0){
                    System.out.println("ACTUALIZADO OK");
                }else{
                    System.out.println("NO EXISTE USUARIO");
                }

            } catch (SQLException e) {
                System.out.println("ERROR QUERY: " + e.getMessage());
            }

        } else {
            System.out.println("ERROR CONEXION");
        }
    }
}

