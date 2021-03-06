package LogicaNegocio.Sesiones;

import InterfazGrafica.Inscripciones.VentanaInscribirAlumno;
import LogicaNegocio.Asistencia.PanelAsistenciaController;
import LogicaNegocio.Catalogos.Profesor;
import LogicaNegocio.Grupos.PanelGruposProfesorController;
import LogicaNegocio.Lanzador;
import LogicaNegocio.Pagos.PanelProximosPagosAlumnoController;
import LogicaNegocio.Pagos.VentanaRegistrarPagoAlumno;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class VentanaPrincipalProfesorController extends VentanaPrincipal implements Initializable {
    private Usuario usuario;
    private Profesor profesor;
    private Lanzador lanzador;
    @FXML
    private MenuItem menuRegistrarPagos;
    
    @FXML
    private ImageView imagenCambiarCuenta;
    @FXML
    private BorderPane panelPrincipal;
    
    public void setUsuario(Usuario usuario, Profesor profesor){
        this.imagenCambiarCuenta.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPencilIcon.png")));
        System.out.println(usuario.getNombre());
        this.usuario = usuario;
        this.profesor = profesor;
        this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelGruposProfesor.fxml");
        PanelGruposProfesorController controller = this.lanzador.getCargador().getController();
        controller.iniciar(this.profesor.getIdProfesor(), this.lanzador, this.lanzador.getPanelActual());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {//1 activo 2 baja
        this.lanzador = new Lanzador(this.panelPrincipal, this);
        
    }  
    
    public void imageView_onClick(){
        new VentanaModificarCuenta(this.usuario, this.profesor);
    }
    public void menuMisGrupos_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelGruposProfesor.fxml");
        PanelGruposProfesorController controller = this.lanzador.getCargador().getController();
        controller.iniciar(this.profesor.getIdProfesor(), this.lanzador, this.lanzador.getPanelActual());
    }
    public void menuRegistrarPago_onClick(){
        new VentanaRegistrarPagoAlumno(this.profesor.getIdProfesor(), this.lanzador);
    }
    public void menuInscripcion_onClick(){
        new VentanaInscribirAlumno(this.profesor.getIdProfesor());
    }
    public void menuAistencia_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Asistencia/PanelAsistencia.fxml");
        PanelAsistenciaController controller = this.lanzador.getCargador().getController();
        controller.setProfesor(this.profesor.getIdProfesor());
    }
    public void menuProximosAlumno_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Pagos/PanelProximosPagosAlumno.fxml");
        PanelProximosPagosAlumnoController controller = this.lanzador.getCargador().getController();
        controller.iniciar(this.profesor.getIdProfesor());
    }
}
