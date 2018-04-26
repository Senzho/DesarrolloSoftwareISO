package LogicaNegocio.Grupos;

import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Pagos.PanelHistorialPagosAlumnoController;
import LogicaNegocio.Pagos.VentanaRegistrarPagoAlumno;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelAlumnoProfesorController implements Initializable {
    @FXML
    private Label nombre;
    @FXML
    private Label telefono;
    @FXML
    private Label correo;
    @FXML
    private Button pagos;
    @FXML
    private Button registrarPago;
    @FXML
    private ImageView imagen;
    @FXML
    private ImageView cambiarGrupo;
    
    private Alumno alumno;
    private Lanzador lanzador;
    private int idProfesor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarImagen();
        this.cambiarGrupo.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkChangeIcon.png")));
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
    public void cargarImagen(){
        this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
    }
    public void setLanzador(Lanzador lanzador){
        this.lanzador = lanzador;
    }
    public void setIdProfesor(int id){
        this.idProfesor = id;
    }
    
    public void cambiarGrupo_onClick(){
        
    }
    public void pagos_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelHistorialPagosAlumno.fxml");
        PanelHistorialPagosAlumnoController controller = this.lanzador.getCargador().getController();
        controller.setAlumno(this.alumno);
        controller.inicializarPanelPagos();
    }
    public void registrarPago_onClick(){
        new VentanaRegistrarPagoAlumno(this.idProfesor, this.alumno.getNombre());
    }
}
