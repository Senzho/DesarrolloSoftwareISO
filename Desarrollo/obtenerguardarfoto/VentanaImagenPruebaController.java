package obtenerguardarfoto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desktop
 */
public class VentanaImagenPruebaController implements Initializable {

    @FXML
    private ImageView imagenUsuario;
    @FXML
    private Button btnCargarImagen;
    @FXML
    private TextField txtTipoUsuario;
    String idUsuario = "10";
    String nombre = "Jose";
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void btnCargarImagen_onClick() {
        FileChooser buscador = new FileChooser();
        buscador.setTitle("imagenes");
        buscador.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        File imagenUno = buscador.showOpenDialog(this.stage);
        if (imagenUno != null) {
            String origen = imagenUno.getAbsolutePath();
            System.out.println(origen);
            String destino = "C:\\Users\\Desktop\\Desktop\\Fotos\\" + idUsuario +".jpg";
            try {
                System.out.println("Proceso de copiar archivo: " + new CopiarArchivo().copiar(origen, destino));
            } catch (IOException ex) {
                Logger.getLogger(VentanaImagenPruebaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image imagen = new Image("file:"+destino);
            this.imagenUsuario.setImage(imagen);
        }
    }

}
