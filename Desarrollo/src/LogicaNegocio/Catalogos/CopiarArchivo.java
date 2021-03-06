/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Catalogos;

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
        Image imagen = null;
        File ArchivoImagen = null;
        FileChooser buscador = new FileChooser();
        buscador.setTitle("imagenes");
        buscador.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        ArchivoImagen = buscador.showOpenDialog(new Stage());
        if (ArchivoImagen != null) {
            String origen = ArchivoImagen.getAbsolutePath();
            if (origen != null) {
                imagen = new Image("file:" + origen);
                setRuta(origen);
            }
        }
        return imagen;
    }

    public static Image obtenerFotoUsuario(String tipoUsuario, int idUsuario) {
        Image imagen = null;
        File directorio = null;
        String nombreSistema = System.getProperty("os.name");
        String separador = "";
        String rutaPrincipal = "";
        if(nombreSistema.contains("Linux")){
            rutaPrincipal = System.getProperty("user.home");
            separador = "/";
        }else{
            rutaPrincipal = "C:";
            separador = "\\";
        }
        if (tipoUsuario.equalsIgnoreCase("alumno")) {
            directorio = new File(rutaPrincipal+separador+"Ared"+separador+"Fotos"+separador+"Alumnos"+separador+idUsuario+".jpg");//"C:\\Ared\\Alumnos\\" + idUsuario + ".jpg");
            if (directorio.exists()) {
                imagen = new Image("file:" + directorio.getAbsolutePath());
            }
        } else if (tipoUsuario.equalsIgnoreCase("profesor")) {
            directorio = new File(rutaPrincipal+separador+"Ared"+separador+"Fotos"+separador+"Profesores"+separador+idUsuario+".jpg");//("C:\\Ared\\Profesores\\" + idUsuario + ".jpg");
            if (directorio.exists()) {
                imagen = new Image("file:" + directorio.getAbsolutePath());
            }
        } else if (tipoUsuario.equalsIgnoreCase("cliente")) {
            directorio = new File(rutaPrincipal+separador+"Ared"+separador+"Fotos"+separador+"Clientes"+separador+idUsuario+".jpg");//("C:\\Ared\\Clientes\\" + idUsuario + ".jpg");
            if (directorio.exists()) {
                imagen = new Image("file:" + directorio.getAbsolutePath());
            }
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
        File directorio;
        String nombreSistema = System.getProperty("os.name");
        String separador = "";
        String rutaPrincipal = "";
        if(nombreSistema.contains("Linux")){
            rutaPrincipal = System.getProperty("user.home");
            separador = "/";
        }else{
            rutaPrincipal = "C:";
            separador = "\\";
        }
        String rutaGeneralFotos = rutaPrincipal+separador+"Ared"+separador+"Fotos";
        
        File fotos = new File(rutaGeneralFotos);
        if (!fotos.exists()) {
            fotos.mkdirs();
        }
        if (tipoUsuario.equalsIgnoreCase("alumno")) {
            directorio = new File(rutaGeneralFotos+separador+"Alumnos");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            guardado = copiar(rutaOrigen, directorio.getAbsolutePath(), idUsuario);
        } else if (tipoUsuario.equalsIgnoreCase("profesor")) {
            directorio = new File(rutaGeneralFotos+separador+"Profesores");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            guardado = copiar(rutaOrigen, directorio.getAbsolutePath(), idUsuario);

        } else if (tipoUsuario.equalsIgnoreCase("cliente")) {
            directorio = new File(rutaGeneralFotos+separador+"Clientes");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            guardado = copiar(rutaOrigen, directorio.getAbsolutePath(), idUsuario);
        }
        return guardado;
    }

    private static boolean copiar(String origen, String destino, int idUsuario) throws IOException {
        String nombreSistema = System.getProperty("os.name");
        String separador = "";
        String rutaPrincipal = "";
        if(nombreSistema.contains("Linux")){
            rutaPrincipal = System.getProperty("user.home");
            separador = "/";
        }else{
            rutaPrincipal = "C:";
            separador = "\\";
        }
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
                    salida = new FileOutputStream(archivoDestino + separador + idUsuario + ".jpg");
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
