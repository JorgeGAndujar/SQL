package ejemplo6;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class PrincipalCommitRollBack {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Connection conexion = ConexionMysqlBDTRANSACCIONES.obtenerConexion();
        if (conexion != null) {
            System.out.println("OK: CONEXION");
            menu(conexion);
        } else {
            System.out.println("ERROR: CONEXION");
        }
    }
    public static void menu(Connection conexion) {
        while (true) {
            cls();
            System.out.println("1. ACTIVAR PUNTO DE RESTAURACIÓN");
            System.out.println("2. REALIZAR TRANSACCIÓN UPDATE");
            System.out.println("3. CONFIRMAR TRANSACCIÓN (COMMIT)");
            System.out.println("4. DESCARTAR TRANSACCIÓN (ROLLBACK)");
            System.out.println("5. MOSTRAR TABLA CLIENTE");
            System.out.println("6. SALIR");

            System.out.print("INGRESE OPCION? ");
            String opcion = sc.next();
            switch (opcion) {
                case "1":
                    cls();
                    activarPuntoRestauracion(conexion);
                    pause();
                    break;
                case "2":
                    cls();
                    realizarTransaccionUpdate(conexion);
                    pause();
                    break;
                case "3":
                    cls();
                    confirmarTransaccion(conexion);
                    pause();
                    break;  
                case "4": 
                    cls();
                    descartarTransaccion(conexion);
                    pause();
                    break;
                case "5": 
                    cls();
                    mostrarTablaCliente(conexion);
                    pause();
                    break;
                case "6": 
                    System.exit(0);
                    break;//el break no tiene porque hacer falta
                    
            }
        }
    }
    public static void mostrarTablaCliente(Connection conexion){
        String query = "SELECT * FROM Cliente";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.printf("%-4s %-10s %-15s\n","ID","NOMBRE","EMAIL");
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                int idCliente = rs.getInt("id_cliente");//igual q la query("id_cliente")
                System.out.printf("%-4d %-10s %-15s\n",idCliente,nombre,email);
            }
        }catch(SQLException e){
            System.out.println("ERROR: SELECT" + e);
        }
    }
    public static void activarPuntoRestauracion(Connection conexion){
        try{
            conexion.setAutoCommit(false);//STAR TRANSACTION   
            
        }catch(SQLException e){
            System.out.println("ERROR AL ACTIVAR PUNTO DE RESTAURACIÓN");
            
        }
    }
    public static void descartarTransaccion(Connection conexion){
        try{
            conexion.rollback();//DESCARTAR CAMBIOS  
            System.out.println("CAMBIOS DESCARTADOS: ROLLBACK");
        }catch(SQLException e){
            System.out.println("ERROR ROLLBACK: CAMBIOS NO DESCARTADOS");
            
        }
    }
    public static void confirmarTransaccion(Connection conexion){
        try{
            conexion.commit();//CONFIRMAR CAMBIOS
            System.out.println("CAMBIOS CONFIRMAR COMMIT");
        }catch(SQLException e){
            System.out.println("ERROR AL CONFIRMAR COMMIT");
            
        }
    }
    public static void realizarTransaccionUpdate(Connection conexion){
        String query = "UPDATE Cliente SET nombre = ?, email = ? WHERE id_cliente = ?";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, nombre());
            ps.setString(2, email());
            ps.setInt(3, idCliente());
            int filaAfectada = ps.executeUpdate();
            if(filaAfectada > 0){
                System.out.println("OK: UPDATE");
            }else {
                System.out.println("ERROR: UPDATE");
            }    
        }catch(SQLException e){
            System.out.println("ERROR AL REALIZAR TRANSACCIÓN UPDATE");    
        }
    }  
    public static String nombre(){
        System.out.print("Ingrese nombre nuevo? ");
        String nombre = sc.next();
        return nombre;    
    }
    public static String email(){
        System.out.print("Ingrese email nuevo? ");
        String email = sc.next();
        return email;  
    }
    public static int idCliente(){
        System.out.print("Ingrese id_cliente? ");
        int idCliente = sc.nextInt();
        return idCliente;   
    } 
    
    public static void pause() {
        try {
            System.out.print("\nPresiona una tecla para continuar...");
            System.in.read();
        } catch (IOException e) {
        }
    }

    public static void cls() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
