package LogicaNegocio.Sesiones;

import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Catalogos.VentanaCRUAlumno;
import LogicaNegocio.Catalogos.VentanaCRUCliente;
import LogicaNegocio.Catalogos.VentanaCRUProfesor;
import LogicaNegocio.Egresos.VentanaCRUEgreso;
import LogicaNegocio.Egresos.VentanaCRUGastoPromocional;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VentanaPrincipalDirectorController implements Initializable {
    private Usuario usuario;
    private Profesor profesor;
    @FXML
    private ImageView imagenCambiarCuenta;
    
    public void setUsuario(Usuario usuario, Profesor profesor){
        this.imagenCambiarCuenta.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
        this.usuario = usuario;
        this.profesor = profesor;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {//1 activo 2 baja
        // TODO
    }    
    
    public void imageView_onClick(){
        new VentanaModificarCuenta(this.usuario, this.profesor);
    }
    public void menuRegistrarAlumno_onClick(){
        new VentanaCRUAlumno();
    }
    public void menuRegistrarProfesor_onClick(){
        new VentanaCRUProfesor();
    }
    public void menuRegistrarCliente_onClick(){
        new VentanaCRUCliente();
    }
    public void menuPromocionFacebook_onClick(){
        new VentanaCRUGastoPromocional();
    }
    public void menuEgreso_onClick(){
        new VentanaCRUEgreso();
    }
}
