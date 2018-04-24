package LogicaNegocio.Sesiones;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Profesor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VentanaModificarCuentaController implements Initializable {
    private Usuario usuario;
    private Profesor profesor;
    @FXML
    private ImageView imagenUsuario;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtContraseña;
    @FXML
    private TextField txtRepetirContraseña;
    @FXML
    private Button btnGuardar;
    @FXML
    private Label lblNombreUsuario;

    public void setUsuario(Usuario usuario, Profesor profesor) {
        this.usuario = usuario;
        this.profesor = profesor;
        this.cargarDatosUsuario();
    }
    public void cargarDatosUsuario() {
        Profesor profesor = new Usuario().obtenerProfesor(this.usuario.getIdTipoUsuario());
        this.imagenUsuario.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        this.lblNombreUsuario.setText(this.profesor.getNombre());
        this.txtNombre.setText(this.usuario.getNombre());
    }
    public boolean validarCampos(){
        boolean valido = true;
        String nombre = this.txtNombre.getText().trim();
        if (nombre.length() > 20 || nombre.equals("") || txtContraseña.getText().trim().equals("") || txtRepetirContraseña.getText().trim().equals("")) {
            valido = false;
        }
        return valido;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void btnGuardar_onClick() {
        if (this.validarCampos()) {
            MessageFactory.showMessage("Información", "Datos invalidos", "Faltan datos del usuario", AlertType.INFORMATION);
        } else {
            if (!txtContraseña.getText().trim().equals(txtRepetirContraseña.getText().trim())) {
                MessageFactory.showMessage("Información", "Datos invalidos", "Las contraseñas no coinciden", AlertType.INFORMATION);
            } else {
                String contraseña = Hasher.hash(this.txtContraseña.getText().trim());
                usuario.setContraseña(contraseña);
                usuario.setNombre(this.txtNombre.getText().trim());
                if (this.usuario.buscarUsuario(this.txtNombre.getText())) {
                    MessageFactory.showMessage("Información", "Datos invalidos", "El usuario seleccionado existe en el sistema", AlertType.ERROR);
                } else {
                    usuario.editarUsuario();
                    MessageFactory.showMessage("Información", "Datos registrados", "Usuario editado con exito", AlertType.CONFIRMATION);
                }
            }
        }
    }
}
