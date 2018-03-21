package LogicaNegocio.Catalogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
        this.cargarAlumno();
    }
    public void cargarAlumno(){
        this.nombre.setText(this.alumno.getNombre());
        this.telefono.setText(this.alumno.getTeléfono());
        this.correo.setText(this.alumno.getCorreo());
    }
}
