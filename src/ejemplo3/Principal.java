
package ejemplo3;
import java.sql.*;

public class Principal {

    public static void main(String[] args) {
       Connection conexion = ConexionMysqlBDEnriptar.obtenerConexion();
       if(conexion != null){
           String nombre = "Miguel";
           String login = "miguelito";
           String clave = "XYZ12345";
           
           //String queryFuncion ="{ ? = CALL obtener_palabra_sereta() }";
           
           String query = "INSERT INTO Usuario (nombre,login,clave) \n" +
                          "VALUES (?, ?, AES_ENCRYPT(?,CONCAT(obtener_palabra_secreta(),'%')));";
           
           try{
               CallableStatement cs = conexion.prepareCall(query);
               cs.setString(1, nombre);
               cs.setString(2, login);
               cs.setString(3, clave);
               cs.execute();
               System.out.println("OK:INSERT");
                       
           }catch(SQLException e){
               System.out.println("ERROR QUERY" + e);
           }
           
           
          System.out.println("OK CONEXION");
       }else{
          System.out.println("ERROR CONEXION"); 
       }
    }
    
}
