package LogicaNegocio.Sesiones;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.persistence.NoResultException;

public class VentanaInicioSesionController implements Initializable {
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContraseña;
    @FXML
    private Button btnIniciarSesion;
    private Usuario usuario;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void btnIniciarSesion_onClick(){
        this.usuario = new Usuario().buscarUsuarioSesion(this.txtUsuario.getText(), this.txtContraseña.getText());
        if(usuario!= null){
            System.out.println("si existe y entre al usuario");
        }else{
            System.out.println("no conexion o no usuario");
        }
    }
    
}
