package ejemplo5;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class MetodosArchivo {

    public static String leerQueryTrigger() {
        File f;
        FileReader fr;
        BufferedReader br;

        String nra = "data/querys.sql"; // NOMBRE Y RUTA DEL ARCHIVO
        String fila = "";
        StringBuilder cadena = new StringBuilder();
        try {
            f = new File(nra);
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            while ((fila = br.readLine()) != null) {
                //System.out.println(fila);
                cadena.append(fila).append("\n");
            }

        } catch (Exception e) {

        }
        return cadena.toString();

    }
}
