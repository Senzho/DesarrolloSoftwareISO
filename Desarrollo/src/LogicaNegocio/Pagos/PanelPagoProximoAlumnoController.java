package LogicaNegocio.Pagos;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.CopiarArchivo;
import LogicaNegocio.Egresos.Dates;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelPagoProximoAlumnoController implements Initializable {
    @FXML
    private Label nombre;
    @FXML
    private Label fecha;
    @FXML
    private ImageView imagenAlumno;
    
    private Alumno alumno;
    private Date fechaPago;
    
    private void cargarAlumno(){
        this.nombre.setText(this.alumno.getNombre());
        this.fecha.setText(Dates.getSentence(this.fechaPago));
        this.cargarImagen();
    }
    private void cargarImagen(){
        Image imagenAlumno = CopiarArchivo.obtenerFotoUsuario("alumno", this.alumno.getIdAlumno());
        if (imagenAlumno != null) {
            this.imagenAlumno.setImage(imagenAlumno);
        } else {
            this.imagenAlumno.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void iniciar(Alumno alumno, Date fecha){
        this.alumno = alumno;
        this.fechaPago = fecha;
        this.cargarAlumno();
    }
}
