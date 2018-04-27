package obtenerguardarfoto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    int idUsuario = 10;
    String nombre = "Jose";
    private Stage stage;
    private String origen;
    private Image foto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void btnGuardar_onClick() {
    }

    public void btnCargarImagen_onClick() {
        this.imagenUsuario.setImage(CopiarArchivo.obtenerFotoUsuario("alumno", idUsuario));
    }

}
