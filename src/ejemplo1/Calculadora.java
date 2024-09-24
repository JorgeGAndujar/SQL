package ejemplo1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Calculadora {

    public static void main(String[] args) {
        Connection conexion = ConexionMysql.obtenerConexion();
        if (conexion != null) {
            String query = "CALL calculadora(?,?,?,?)";
            try {
                CallableStatement cs = conexion.prepareCall(query);
                cs.setInt("n1", 2);
                cs.setInt("n2", 3);
                cs.setString("operacion", "^");
                cs.registerOutParameter("resultado", java.sql.Types.DOUBLE);
                cs.execute();
                double resultado = cs.getDouble("resultado");
                System.out.println("resultado: " + resultado);
            } catch (SQLException e) {
                System.out.println("ERROR DE QUERY" + e);
            }
        } else {
            System.out.println("ERROR DE CONEXION");
        }

    }

}
