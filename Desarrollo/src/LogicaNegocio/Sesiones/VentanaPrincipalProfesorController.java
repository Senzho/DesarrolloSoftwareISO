/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Sesiones;

import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Sesiones.Usuario;
import LogicaNegocio.Sesiones.VentanaModificarCuenta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Marioolopez
 */
public class VentanaPrincipalProfesorController implements Initializable {
    private Usuario usuario;
    private Profesor profesor;
    @FXML
    private ImageView imagenCambiarCuenta;
    
    public void setUsuario(Usuario usuario, Profesor profesor){
        this.imagenCambiarCuenta.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
        System.out.println(usuario.getNombre());
        this.usuario = usuario;
        this.profesor = profesor;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {//1 activo 2 baja
        // TODO
    }    
    public void imageView_onClick(){
        
        new VentanaModificarCuenta(this.usuario, this.profesor);
    }
}
