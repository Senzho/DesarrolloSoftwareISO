package LogicaNegocio.Catalogos;

import InterfazGrafica.MessageFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelAlumnoDirectorController implements Initializable {
    @FXML
    private ImageView imagen;
    @FXML
    private Label nombre;
    @FXML
    private Label telefono;
    @FXML
    private Label correo;
    @FXML
    private ImageView baja;
    @FXML
    private ImageView editar;
    
    private Alumno alumno;
    
    private void establecerIconos(){
        if (this.alumno.isEstado()){
            this.baja.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
        }else{
            this.baja.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPlusIcon.png")));
        }
        this.editar.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
        this.cargarAlumno();
        this.establecerIconos();
        this.cargarImagen();
    }
    public void cargarAlumno(){
        this.nombre.setText(this.alumno.getNombre());
        this.telefono.setText(this.alumno.getTeléfono());
        this.correo.setText(this.alumno.getCorreo());
    }
    public void cargarImagen(){
        //Validar si existe una imágen, en caso contrario:
        this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }
    
    public void editar_OnClick(){
        new VentanaCRUAlumno(this.alumno);
    }
    public void baja_OnClick(){
        this.alumno.setEstado(!this.alumno.isEstado());
        if (this.alumno.editarAlumno()){
            this.establecerIconos();
            String estado;
            if (this.alumno.isEstado()){
                estado = "activo";
            }else{
                estado = "inactivo";
            }
            MessageFactory.showMessage("Éxito", "Registro", "El estado del alumno cambió a " +  estado, Alert.AlertType.INFORMATION);
        }else{
            MessageFactory.showMessage("Error", "Registro", "No se pudo cambiar el estado del alumno", Alert.AlertType.ERROR);
        }
    }
}
