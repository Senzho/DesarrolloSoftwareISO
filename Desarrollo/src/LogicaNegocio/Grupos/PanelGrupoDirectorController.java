package LogicaNegocio.Grupos;

import InterfazGrafica.Grupos.VentanaCRUGrupo;
import LogicaNegocio.Lanzador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelGrupoDirectorController implements Initializable {
    @FXML
    private Label nombreGrupo;
    @FXML
    private Label nombreProfesor;
    @FXML
    private Label horas;
    @FXML
    private ImageView verAlumnos;
    @FXML
    private ImageView editar;
    @FXML
    private ImageView darDeBaja;
    
    private String horaInicio;
    private String horaFin;
    private Grupo grupo;
    private Lanzador lanzador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.verAlumnos.setImage(new Image("/RecursosGraficos/darkGroupIcon.png"));
        this.editar.setImage(new Image("/RecursosGraficos/darkPencilIcon.png"));
        this.darDeBaja.setImage(new Image("/RecursosGraficos/darkCrossIcon.png"));
    }
    
    public void iniciar(String horaInicio, String horaFin, Grupo grupo, Lanzador lanzador){
        this.lanzador = lanzador;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.grupo = grupo;
        this.nombreGrupo.setText(this.grupo.getNombre());
        this.nombreProfesor.setText(this.grupo.getProfesor().getNombre());
        this.horas.setText(this.horaInicio + " - " + this.horaFin);
    }
    
    public void verAlumnos_onClick(){
        this.lanzador.lanzar("/InterfazGrafica/Grupos/PanelAlumnosGrupoDirector.fxml");
        PanelAlumnosGrupoDirectorController controller = this.lanzador.getCargador().getController();
        controller.iniciar(this.grupo);
    }
    public void editar_onClick(){
        new VentanaCRUGrupo(this.grupo);
    }
    public void darDeBaja_onClick(){
        
    }
}
