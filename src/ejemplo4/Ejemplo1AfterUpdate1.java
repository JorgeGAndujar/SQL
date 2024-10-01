
package ejemplo4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejemplo1AfterUpdate1 {

    public static void main(String[] args) {
        Connection conexion = ConexionMysqlBDTRIGGER.obtenerConexion();
        if (conexion != null) {
            System.out.println("OK CONEXION");
            if(lanzarTriggerUpdate(conexion) == true){
                System.out.println("OK: TRIGGER");
            }else{
                System.out.println("ERROR: AFTER UPDATE TRIGGER");
            }

        } else {
            System.out.println("ERROR CONEXION");
        }
    }
    public static boolean lanzarTriggerUpdate(Connection conexion){
        boolean bandera = true;
        String nombre = "Jorge";
        String clave =  "12345abcdef";
        int idUsuario = 1;
        String query = "UPDATE usuario SET nombre = ?, clave = ? WHERE id_usuario = ?";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1,nombre);
            ps.setString(2,clave);
            ps.setInt(3,idUsuario);
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("OK UPDATE");
            }else{
                System.out.println("USUARIO " + idUsuario + " NO EXISTE");
            }
            
        }catch(SQLException e){
           bandera = false; 
        }
         return bandera;
    }  
}


   
    

