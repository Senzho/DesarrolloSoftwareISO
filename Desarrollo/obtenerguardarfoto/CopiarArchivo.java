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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Desktop
 */
public class CopiarArchivo {

    private static String rutaImagen;

    public static Image buscarFoto() {
        File ArchivoImagen = null;
        FileChooser buscador = new FileChooser();
        buscador.setTitle("imagenes");
        buscador.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        ArchivoImagen = buscador.showOpenDialog(new Stage());
        String origen = ArchivoImagen.getAbsolutePath();
        Image imagen = new Image("file:" + origen);
        setRuta(origen);
        return imagen;
    }
    public static Image obtenerFotoUsuario(String tipoUsuario, int idUsuario){
        Image imagen = null;
        if (tipoUsuario.equalsIgnoreCase("alumno")) {
           imagen = new Image("file:"+"C:\\Ared\\Alumnos\\"+idUsuario+".jpg");
        } else if (tipoUsuario.equalsIgnoreCase("profesor")) {
           imagen = new Image("file:"+"C:\\Ared\\Profesores\\"+idUsuario+".jpg");
        } else if (tipoUsuario.equalsIgnoreCase("cliente")) {
           imagen = new Image("file:"+"C:\\Ared\\Clientes\\"+idUsuario+".jpg");
        }
        return imagen;
    }
    private static void setRuta(String ruta) {
        rutaImagen = ruta;
    }

    public static String rutaImagen() {
        return rutaImagen;
    }

    public static boolean guardar(String tipoUsuario, String rutaOrigen, int idUsuario) throws IOException {
        boolean guardado = false;
        File directorio = null;
        File fotos = new File("C:\\Ared");
        if(!fotos.exists()){
            fotos.mkdir();
        }
        if (tipoUsuario.equalsIgnoreCase("alumno")) {
            directorio = new File("C:\\Ared\\Alumnos");
            if (!directorio.exists()) {
                directorio.mkdir();
            }
            guardado = copiar(rutaOrigen,directorio.getAbsolutePath(), idUsuario);
        } else if (tipoUsuario.equalsIgnoreCase("profesor")) {
            directorio = new File("C:\\Ared\\Profesores");
            if (!directorio.exists()) {
                directorio.mkdir();
            }
            guardado = copiar(rutaOrigen,directorio.getAbsolutePath(), idUsuario);

        } else if (tipoUsuario.equalsIgnoreCase("cliente")) {
            directorio = new File("C:\\Ared\\Clientes");
            if (!directorio.exists()) {
                directorio.mkdir();
            }
            guardado = copiar(rutaOrigen,directorio.getAbsolutePath(), idUsuario);
        }
        return guardado;
    }

    private static boolean copiar(String origen, String destino, int idUsuario) throws IOException {
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
                    salida = new FileOutputStream(archivoDestino+"\\"+idUsuario+".jpg");
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
