package LogicaNegocio.Sesiones;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Profesor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class VentanaInicioSesionController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContraseña;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private ImageView imagenAred;
    
    private Usuario usuario;
    private Profesor profesor;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.txtUsuario.setStyle("-fx-accent: #FA58F4");
        this.imagenAred.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/AredImagen.png")));
    }
    public void iniciarSesion(String nombreUsuario, String contraseña){
        this.usuario = new Usuario().buscarUsuarioSesion(nombreUsuario, contraseña);
        if (usuario != null) {
            this.profesor = new Usuario().obtenerProfesor(this.usuario.getIdTipoUsuario());
            if (usuario.getTipoUsuario() == 0) {
                new VentanaPrincipalDirector(usuario, profesor);
                this.stage.close();
            } else {
                new VentanaPrincipalProfesor(usuario, profesor);
                this.stage.close();
            }
        } else {
            MessageFactory.showMessage("Información", "Usuario incorrecto", "El usuario y/o contraseña son incorrectos", AlertType.INFORMATION);
        }
    }

    public void btnIniciarSesion_onClick() {
        String nombreUsuario = this.txtUsuario.getText().trim();
        String contrasena = this.txtContraseña.getText().trim();
        if (nombreUsuario.length() == 0){
            MessageFactory.showMessage("Información", "Datos", "Ingresa tu nombre de usuario", AlertType.INFORMATION);
        }else if (contrasena.length() == 0){
            MessageFactory.showMessage("Información", "Datos", "Ingresa tu contraseña", AlertType.INFORMATION);
        }else{
            this.iniciarSesion(nombreUsuario, Hasher.hash(contrasena));
        }
    }
}
