package ejemplo6;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class CrearBorrarUsuario {

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
            System.out.println("2. CREAR USUARIO");
            System.out.println("3. CONFIRMAR TRANSACCIÓN (COMMIT)");
            System.out.println("4. DESCARTAR TRANSACCIÓN (ROLLBACK)");
            System.out.println("5. ELIMINAR USUARIO");
            System.out.println("6. CREAR PRIVILEGIOS AL USUARIO NUEVO");
            System.out.println("7. MOSTRAR LA TABLA DE PRIVILEGIOS(EJ:Cliente)");
            System.out.println("8. SALIR");

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
                    crearUsuario(conexion);
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
                    eliminarUsuario(conexion);
                    pause();
                    break;
                case "6":
                    cls();
                    CrearPrivilegiosUsuarioNuevo(conexion);
                    pause();
                    break;
                case "7":
                    cls();
                    mostrarTablaPrivilegios(conexion);
                    pause();
                    break;
                case "8":
                    System.exit(0);
                    break;//el break no tiene porque hacer falta       
            }
        }
    }

    public static void mostrarTablaPrivilegios(Connection conexion) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese nombre de la tabla de privilegios: ");
        String tabla = sc.next();

        String query = "SELECT * FROM " + tabla + ";";

        try (PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                // Puedes procesar los resultados aquí si es necesario
                System.out.println("La tabla " + tabla + " existe.");
            } else {
                System.out.println("No se encontraron registros en la tabla " + tabla + ".");
            }
        } catch (SQLException e) {
            System.out.println("ERROR AL MOSTRAR LA TABLA: " + e.getMessage());
        }
    }

    public static void crearUsuario(Connection conexion) {
        String usuario = usuario(); // Obtiene el usuario
        if (usuarioExiste(conexion, usuario)) {
            System.out.println("ERROR: El usuario ya existe.");
            return; // Termina el método si el usuario ya existe
        }
        String clave = clave(); // Obtiene la clave
        String query = "CREATE USER '" + usuario + "'@'localhost' IDENTIFIED BY '" + clave + "';";

        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query); // Ejecución del comando
            System.out.println("USUARIO CREADO CORRECTAMENTE");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void activarPuntoRestauracion(Connection conexion) {
        try {
            conexion.setAutoCommit(false);//STAR TRANSACTION   
            System.out.println("PUNTO DE RESTAURACIÓN ACTIVADO");
        } catch (SQLException e) {
            System.out.println("ERROR AL ACTIVAR PUNTO DE RESTAURACIÓN");

        }
    }

    public static void descartarTransaccion(Connection conexion) {
        try {
            conexion.rollback();//DESCARTAR CAMBIOS  
            System.out.println("CAMBIOS DESCARTADOS: ROLLBACK");
        } catch (SQLException e) {
            System.out.println("ERROR ROLLBACK: CAMBIOS NO DESCARTADOS");

        }
    }

    public static void confirmarTransaccion(Connection conexion) {
        try {
            conexion.commit();//CONFIRMAR CAMBIOS
            System.out.println("CAMBIOS CONFIRMAR COMMIT");
        } catch (SQLException e) {
            System.out.println("ERROR AL CONFIRMAR COMMIT");

        }
    }

    public static void eliminarUsuario(Connection conexion) {
        String usuario = usuario(); // Obtener el usuario
        String query = "DROP USER '" + usuario + "'@'localhost';"; // Concatenar el nombre de usuario
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query); // Ejecución del comando
            System.out.println("OK: USUARIO ELIMINADO");
        } catch (SQLException e) {
            System.out.println("ERROR AL BORRAR EL USUARIO: " + e.getMessage());
        }
    }

    public static String usuario() {
        System.out.print("Ingrese usuario nuevo? ");
        String usuario = sc.next();
        return usuario;
    }

    public static String baseDatos() {
        System.out.print("Ingrese baseDatos ? ");
        String baseDatos = sc.next();
        return baseDatos;
    }

    public static String clave() {
        System.out.print("Ingrese clave nueva? ");
        String clave = sc.next();
        return clave;
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

    public static void CrearPrivilegiosUsuarioNuevo(Connection conexion) {
        String usuario = usuario(); // Obtiene el nombre del usuario al que se le otorgarán privilegios
        String baseDatos = baseDatos(); // Obtiene el nombre de la base de datos

        if (!usuarioExiste(conexion, usuario)) {
            System.out.println("ERROR: El usuario " + usuario + " no existe.");
            return; // Termina el método si el usuario no existe
        }

        String query = "GRANT ALL PRIVILEGES ON " + baseDatos + ".* TO '" + usuario + "'@'localhost';"; // Concatenar el nombre de la base de datos
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query); // Ejecución del comando
            System.out.println("OK: SE OBTUVO PRIVILEGIOS AL USUARIO " + usuario);
        } catch (SQLException e) {
            System.out.println("ERROR EN LOS PRIVILEGIOS: " + e.getMessage());
        }
        String query2 = "FLUSH PRIVILEGES";
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query2); // Ejecución del comando
            System.out.println("OK: SE OBTUVO TODOS LOS PRIVILEGIOS AL USUARIO " + usuario);
        } catch (SQLException e) {
            System.out.println("ERROR EN LOS PRIVILEGIOS: " + e.getMessage());
        }
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
}
