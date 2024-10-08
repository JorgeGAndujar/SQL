
package ejemplo6;

import static ejemplo6.CrearBorrarUsuario.clave;
import static ejemplo6.CrearBorrarUsuario.usuario;
import static ejemplo6.CrearBorrarUsuario.usuarioExiste;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Metodos {
    
     public static Connection obtenerConexion() {
        String url = "jdbc:mysql://localhost:3307/BDTRANSACCIONES";
        String usuario = "root";
        String clave = "12345678";
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (SQLException e) {
            conexion = null;
        }
        return conexion;
    }
    
    public static boolean usuarioExiste(Connection conexion, String usuario) {
        String query = "SELECT COUNT(*) FROM mysql.user WHERE user = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("ERROR AL VERIFICAR USUARIO: " + e.getMessage());
        }
        return false;
    }
       public static void crearUsuario(Connection conexion, String usuario, String clave) {
        String query = "CREATE USER ?@'localhost' IDENTIFIED BY ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            int filaAfectada = ps.executeUpdate();
            if(filaAfectada >= 0){
                 JOptionPane.showMessageDialog(null,"Usuario creado correctamente", "Información",JOptionPane.INFORMATION_MESSAGE); 
            }else{
                 JOptionPane.showMessageDialog(null,"Usuario no creado", "Información",JOptionPane.INFORMATION_MESSAGE); 
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"Error inesperado", "Información",JOptionPane.INFORMATION_MESSAGE); 
        }
    }
   
}
