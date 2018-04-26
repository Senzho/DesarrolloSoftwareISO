/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerguardarfoto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Desktop
 */
public class CopiarArchivo {

    public boolean copiar(String origen, String destino) throws IOException {
        File archivoOrigen;
        File archivoDestino;
        FileInputStream entrada = null;
        FileOutputStream salida = null;
        boolean copiado;
        try {
            archivoOrigen = new File(origen);
            archivoDestino = new File(destino);
            if (copiado = archivoOrigen.exists()) {
                if (copiado = archivoOrigen.canRead()) {
                    entrada = new FileInputStream(archivoOrigen);
                    salida = new FileOutputStream(archivoDestino);
                    int i;
                    while ((i = entrada.read()) != -1) {
                        salida.write(i);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            copiado = false;
        } finally {
            if (entrada != null) {
                entrada.close();
            }
            if (salida != null) {
                salida.close();
            }
        }
        return copiado;
    }
}
