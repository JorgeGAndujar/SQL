/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo4;

import java.sql.*;

public class ConexionMysqlBDESCUELA {
    public static Connection obtenerConexion() {
        String url = "jdbc:mysql://localhost:3307/BDESCUELA";
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
}
