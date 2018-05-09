package LogicaNegocio.Asistencia;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.CopiarArchivo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelAlumnoAsistenciaController implements Initializable {
    @FXML
    private ImageView imagen;
    @FXML
    private Label nombre;
    @FXML
    private CheckBox asistio;
    
    private Alumno alumno;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
        this.nombre.setText(this.alumno.getNombre());
        Image imagenAlumno = CopiarArchivo.obtenerFotoUsuario("alumno", this.alumno.getIdAlumno());
        if (imagenAlumno != null) {
            this.imagen.setImage(imagenAlumno);
        } else {
            this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
    }
    public void marcar(boolean marcado){
        this.asistio.setSelected(marcado);
    }
}
