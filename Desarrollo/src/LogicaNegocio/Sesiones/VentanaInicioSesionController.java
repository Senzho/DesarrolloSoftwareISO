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

public class VentanaInicioSesionController implements Initializable {
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContraseña;
    @FXML
    private Button btnIniciarSesion;
    private Usuario usuario;
    private Profesor profesor;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void btnIniciarSesion_onClick(){
        String nombreUsuario = this.txtUsuario.getText();
        String contraseña = this.txtContraseña.getText();
        this.usuario = new Usuario().buscarUsuarioSesion(nombreUsuario, contraseña);
        if(usuario!= null){
            System.out.println(this.usuario.getTipoUsuario());
            System.out.println(this.usuario.getIdUsuario());
            System.out.println(usuario.getIdTipoUsuario());
            this.profesor = new Usuario().obtenerProfesor(this.usuario.getIdTipoUsuario());//id tipo usuario es id profesor
            if(usuario.getTipoUsuario() == 0){                
                new VentanaPrincipalDirector(usuario, profesor);
            }else{
                new VentanaPrincipalProfesor(usuario, profesor);
            }
        }else{
            MessageFactory.showMessage("Información","Usuario incorrecto","El usuario y/o contraseña son incorrectos", AlertType.INFORMATION);
        }
    }
    
}
