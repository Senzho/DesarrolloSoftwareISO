package LogicaNegocio.Catalogos;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class VentanaCRUAlumnoController implements Initializable {
    @FXML
    private ImageView imagen;
    @FXML
    private TextField nombre;
    @FXML
    private TextField telefono;
    @FXML
    private TextField correo;
    @FXML
    private TextArea direccion;
    @FXML
    private Button registrar;
    
    private Alumno alumno;
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void cargarAlumno(){
        this.nombre.setText(this.alumno.getNombre());
        this.telefono.setText(this.alumno.getTeléfono());
        this.correo.setText(this.alumno.getCorreo());
        this.direccion.setText(this.alumno.getDireccion());
    }
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
        if (alumno != null){
            this.cargarAlumno();
        }
    }
    
    public void registrar_OnClick(){
        if (this.alumno != null){
            this.alumno.setNombre(this.nombre.getText());
            this.alumno.setTeléfono(this.telefono.getText());
            this.alumno.setCorreo(this.correo.getText());
            this.alumno.setDireccion(this.direccion.getText());
            this.alumno.setFecha(new Date());
            this.alumno.setEstado(true);
            this.alumno.editarAlumno();
        }else{
            this.alumno = new Alumno();
            this.alumno.setNombre(this.nombre.getText());
            this.alumno.setTeléfono(this.telefono.getText());
            this.alumno.setCorreo(this.correo.getText());
            this.alumno.setDireccion(this.direccion.getText());
            this.alumno.setFecha(new Date());
            this.alumno.setEstado(true);
            this.alumno.registrarAlumno();
        }
    }
}
