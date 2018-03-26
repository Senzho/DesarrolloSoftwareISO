/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * FXML Controller class
 *
 * @author Marioolopez
 */
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
    
    public void setUsuario(Usuario usuario, Profesor profesor){
        this.usuario = usuario;
        this.profesor = profesor;
        this.cargarDatosUsuario();
    }
    public void cargarDatosUsuario(){
        Profesor profesor = new Usuario().obtenerProfesor(this.usuario.getIdTipoUsuario());
        this.imagenUsuario.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        this.lblNombreUsuario.setText(this.profesor.getNombre());
        this.txtNombre.setText(this.usuario.getNombre());
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void btnGuardar_onClick(){
        if(txtNombre.getText().equals("") || txtContraseña.getText().equals("") || txtRepetirContraseña.getText().equals("")){
            MessageFactory.showMessage("Información","Datos invalidos","Faltan datos del usuario", AlertType.INFORMATION);
        }else{
            if(!txtContraseña.getText().equals(txtRepetirContraseña.getText())){
                 MessageFactory.showMessage("Información","Datos invalidos","Las contraseñas no coinciden", AlertType.INFORMATION);
            }else{
                String contraseña = Hasher.hash(this.txtContraseña.getText());
                usuario.setContraseña(contraseña);
                usuario.setNombre(this.txtNombre.getText());
                usuario.editarUsuario();
                 MessageFactory.showMessage("Información","Datos registrados","Usuario editado con exito", AlertType.CONFIRMATION);
            }
        }
        
    }
    
}
