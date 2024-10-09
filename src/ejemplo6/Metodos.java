package ejemplo6;

import java.sql.*;
import javax.swing.*;

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
            if (filaAfectada >= 0) {
                JOptionPane.showMessageDialog(null, "Usuario creado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no creado", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error inesperado", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    // 1. showUsers

    public static void showUsers(Connection connection, DefaultListModel dlm) {
        String query = "SELECT user FROM mysql.user";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String user = rs.getString("user");
                dlm.addElement(user);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error recuperar usuario", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 3. deleteUser
    public static void deleteUser(Connection connection, String usuario, DefaultListModel dlm, JList lstMostrar) {

        String query = "DROP USER ?@'localhost'";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, usuario);
            ps.executeUpdate();
            int i = lstMostrar.getSelectedIndex();
            dlm.remove(i);

            JOptionPane.showMessageDialog(null, "Usuario " + usuario + "Usuario Eliminado", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar Usuario", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void CrearPrivilegiosUsuarioNuevo(Connection conexion, String usuario, String[] privilegios) {
        String query = "";
        PreparedStatement ps = null;
        String s = "";
        for (String privilegio : privilegios) {
            s = s + privilegio + ","; // s = Insert,Select,
        }
        s = s.substring(0, s.length() - 1);
        System.out.println("Privilegios: " + s);
        query = "GRANT " + s + " ON BDTRANSACCIONES.* TO '" + usuario + "'@'localhost';";
        try {
            ps = conexion.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "OTORGAR PRIVILEGIOS OK", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL OTORGAR PRIVILEGIOS", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        String query2 = "FLUSH PRIVILEGES";
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query2); // Ejecución del comando
            System.out.println("OK: PRIVILEGIOS ACTUALIZADOS " + usuario);
        } catch (SQLException e) {
            System.out.println("ERROR EN ACTUALIZAR LOS PRIVILEGIOS: " + e.getMessage());
        }
    }

    public static void mostrarPrivilegios(Connection conexion, DefaultListModel dlm2) {
        String[] privilegios = {"ALL", "SELECT", "INSERT", "UPDATE", "DELETE", "CREATE", "DROP"};
        // Agregar los privilegios a dlm2
        for (String privilegio : privilegios) {
            dlm2.addElement(privilegio); // Suponiendo que dlm2 es un DefaultListModel
        }
    }

    public static void revocarPrvilegios(Connection conexion, String usuario, String[] privilegios) {
        String query = "";
        PreparedStatement ps = null;
        String s = "";
        for (String privilegio : privilegios) {
            s = s + privilegio + ","; // s = Insert,Select,
        }
        s = s.substring(0, s.length() - 1);
        System.out.println("Privilegios: " + s);
        query = "REVOKE " + s + " ON BDTRANSACCIONES.* FROM '" + usuario + "'@'localhost';";
        try {
            ps = conexion.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "OTORGAR PRIVILEGIOS OK", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL OTORGAR PRIVILEGIOS", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        String query2 = "FLUSH PRIVILEGES";
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query2); // Ejecución del comando
            System.out.println("OK: PRIVILEGIOS QUITADO " + usuario);
        } catch (SQLException e) {
            System.out.println("ERROR AL QUITAR LOS PRIVILEGIOS: " + e.getMessage());
        }
    }
}


