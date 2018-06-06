package LogicaNegocio.Grupos;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Alumno;
import LogicaNegocio.Catalogos.CopiarArchivo;
import LogicaNegocio.Inscripciones.Inscripcion;
import LogicaNegocio.Inscripciones.VentanaCambiarAlumnoGrupo;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Pagos.PanelHistorialPagosAlumnoController;
import LogicaNegocio.Pagos.VentanaRegistrarPagoAlumno;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    @FXML
    private ImageView desinscribir;
    
    private Alumno alumno;
    private Lanzador lanzador;
    private int idProfesor;
    private int idGrupo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarImagen();
        this.cambiarGrupo.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkChangeIcon.png")));
        this.desinscribir.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkCrossIcon.png")));
    }
    
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
        this.cargarAlumno();
    }
    public void cargarAlumno(){
        this.nombre.setText(this.alumno.getNombre());
        this.telefono.setText(this.alumno.getTeléfono());
        this.correo.setText(this.alumno.getCorreo());
        Image imagenAlumno = CopiarArchivo.obtenerFotoUsuario("alumno", alumno.getIdAlumno());
        if (imagenAlumno != null) {
            this.imagen.setImage(imagenAlumno);
        } else {
            this.imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPersonIcon.png")));
        }
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
    public void setIdGrupo(int idGrupo){
        this.idGrupo = idGrupo;
    }
    
    public void cambiarGrupo_onClick(){
        new VentanaCambiarAlumnoGrupo(this.alumno, this.idProfesor);
    }
    public void pagos_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelHistorialPagosAlumno.fxml");
        PanelHistorialPagosAlumnoController controller = this.lanzador.getCargador().getController();
        controller.setAlumno(this.alumno);
        controller.setIdProfesor(idProfesor);
        controller.inicializarPanelPagos();
    }
    public void registrarPago_onClick(){
        new VentanaRegistrarPagoAlumno(this.idProfesor, this.alumno.getNombre(), this.lanzador);
    }
    public void desinscribir_onClick(){
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación de acción");
        alerta.setHeaderText("Cancelación de inscripción");
        alerta.setContentText("¿Está seguro de cancelar la inscripción del alumno " + this.alumno.getNombre() + "?");
        alerta.showAndWait();
        if (alerta.getResult().getButtonData().isDefaultButton()){
            Inscripcion inscripcion = new Inscripcion();
            if (inscripcion.borrarRegistro(this.alumno.getIdAlumno(), this.idGrupo)){
                MessageFactory.showMessage("Éxito", "Cancelación de inscripción", "La cancelación del alumno fue realizada", AlertType.INFORMATION);
            }else{
                MessageFactory.showMessage("Error", "Cancelación de inscripción", "La cancelación del alumno no fue realizada", AlertType.ERROR);
            }
        }
    }
}
